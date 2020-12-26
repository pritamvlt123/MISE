package com.example.mise.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.*
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_package.*
import org.json.JSONObject
import retrofit2.Callback
import java.lang.Exception


class PackageActivity : AppCompatActivity() , PaymentResultListener {
    val TAG:String = PackageActivity::class.toString()
    var med = ""
    var source = ""
    var classtext = ""
    var savdEmail = ""
    var razorPmtId = ""
    var srId = ""
     lateinit var lay11: LinearLayout
    lateinit var lay: LinearLayout
    var chaptersList:  List<PackageDetails> = emptyList()
    var subjectList:  List<Subjects> = emptyList()
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: CustomAdapter
    private lateinit var adapter2: SubjectCustomAdapter
    private val sharedPrefFile = "kotlinsharedpreference"

    companion object {
        var subjectCount = ""
        var packageId = ""
        var totalPackageAmt = ""
        var seletedItemsChecked = HashSet<Subjects>()
        val numbers: ArrayList<String> = ArrayList()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbarLayout.title = "Plan and Package"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        Checkout.preload(applicationContext)
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        //source = sharedPreferences.getString("pkgType_key","").toString()
        source = sharedPreferences.getString("SR_Source","").toString()
        med = sharedPreferences.getString("medium_key","").toString()
        classtext = sharedPreferences.getString("class_key","").toString()
        savdEmail = sharedPreferences.getString("email_key","").toString()
        srId = sharedPreferences.getString("SR_id","").toString()

         lay11  = findViewById<LinearLayout>(R.id.subjLay11)
         lay = findViewById<LinearLayout>(R.id.subjLay)
        
        packagelist.visibility = View.GONE
        callApiList()
        val languages = resources.getStringArray(R.array.examType)

        // access the spinner

        if (examTypeSpin != null) {
            val adapter = this?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item, languages)
            }
            examTypeSpin.adapter = adapter

            examTypeSpin.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    //Toast.makeText(applicationContext, ""+position, Toast.LENGTH_SHORT).show()

                    if (languages[position] == "Instant Exam"){
                        packagelist.visibility = View.VISIBLE
                        setUpRecyclerView2()
                        setUpRecyclerView4()
                    }
                    else if (languages[position] == "Scheduled Exam"){
                        setUpRecyclerView3()
                        setUpRecyclerView4()
                        packagelist.visibility = View.VISIBLE
                    }
                    else{
                        setUpRecyclerView()
                        setUpRecyclerView4()
                        packagelist.visibility = View.VISIBLE
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val pay = findViewById<Button>(R.id.paypack)
        pay.setOnClickListener {
            if (seletedItemsChecked.count() == 0){
                Toast.makeText(applicationContext, "Please select subject", Toast.LENGTH_SHORT).show()
            }
             else if (subjectCount.toInt() == 1 && seletedItemsChecked.count() < 1 ){
                Toast.makeText(applicationContext, "Please select one subject", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 1 && seletedItemsChecked.count() > 1){
                Toast.makeText(applicationContext, "Please select one subject", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 3 && seletedItemsChecked.count() < 3 ){
                Toast.makeText(applicationContext, "Please select three subjects", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 3 && seletedItemsChecked.count() > 3 ){
                Toast.makeText(applicationContext, "Please select three subjects", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 5 && seletedItemsChecked.count() < 5 ){
                Toast.makeText(applicationContext, "Please select five subjects", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 5 && seletedItemsChecked.count() > 5 ){
                Toast.makeText(applicationContext, "Please select five subjects", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 7 && seletedItemsChecked.count() < 7 ){
                Toast.makeText(applicationContext, "Please select seven subjects", Toast.LENGTH_SHORT).show()
            }
            else if (subjectCount.toInt() == 7 && seletedItemsChecked.count() > 7 ){
                Toast.makeText(applicationContext, "Please select seven subjects", Toast.LENGTH_SHORT).show()
            }

            else {
                callPaymentApi()
                startPayment()
                //Toast.makeText(applicationContext, ">>>>>>>>>>>>"+seletedItemsChecked.count(), Toast.LENGTH_SHORT).show()
            }

        }
        val closebtn = findViewById<ImageView>(R.id.closeBtn)
        closebtn.setOnClickListener {
            lay11.visibility =View.GONE
            seletedItemsChecked.clear()
        }
    }

    fun callApiList(){

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.createplanPackage(
            planPackageRequestParams(classtext,source,med)
        )
        responseCall.enqueue(planPackageResponse as Callback<planPackageResponse>)
    }

    private val planPackageResponse = object : NetworkCallBack<planPackageResponse>(){
        override fun onSuccessNetwork(data: Any?, response: NetworkResponse<planPackageResponse>) {

            //response.data?.status?.let { status ->
           // Toast.makeText(applicationContext, "param"+response.data.toString(), Toast.LENGTH_SHORT).show()

                if (response.data?.count!! > 0){
                    chaptersList = response.data.packageDetails!!.toMutableList()
                    subjectList = response.data.subjects!!.toMutableList()

                    setUpRecyclerView()
                    setUpRecyclerView4()
                }
                else{

                }

            //}

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            // hide()
        }

    }
     fun setUpRecyclerView() {
         val recyclerView = findViewById<RecyclerView>(R.id.packagelist)
         //recyclerView.adapter = CustomAdapter(this, chaptersList)
         layoutManager = LinearLayoutManager(this)
         recyclerView.layoutManager = layoutManager
         recyclerView.adapter = CustomAdapter(this, chaptersList)


    }

    fun setUpRecyclerView2() {
        val recyclerView = findViewById<RecyclerView>(R.id.packagelist)
        //recyclerView.adapter = CustomAdapter(this, chaptersList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val invitedPeople: List<PackageDetails> = chaptersList.filter { it.Type == "Instant" }

        recyclerView.adapter = CustomAdapter(this, invitedPeople)


    }
    fun setUpRecyclerView3() {
        val recyclerView = findViewById<RecyclerView>(R.id.packagelist)
        //recyclerView.adapter = CustomAdapter(this, chaptersList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val invitedPeople: List<PackageDetails> = chaptersList.filter { it.Type == "Scheduled" }

        recyclerView.adapter = CustomAdapter(this, invitedPeople)


    }
    fun setUpRecyclerView4() {
        val recyclerView11 = findViewById<RecyclerView>(R.id.subjlist)
        //recyclerView.adapter = CustomAdapter(this, chaptersList)
        layoutManager = LinearLayoutManager(this)
        recyclerView11.layoutManager = layoutManager
        recyclerView11.adapter = SubjectCustomAdapter(this, subjectList)
        //(recyclerView11.adapter as SubjectCustomAdapter).notifyDataSetChanged()

    }
    fun callPaymentApi(){

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.addPaymentAfterSubject(
            addSubAfterPaymentRequestParams(srId, numbers)
        )
        responseCall.enqueue(addSubAfterPaymentResponse as Callback<addSubAfterPaymentResponse>)
    }

    private val addSubAfterPaymentResponse = object : NetworkCallBack<addSubAfterPaymentResponse>(){
        override fun onSuccessNetwork(data: Any?, response: NetworkResponse<addSubAfterPaymentResponse>) {

            response.data?.status?.let { status ->
             //Toast.makeText(applicationContext, "Payment done successfully", Toast.LENGTH_SHORT).show()
                //lay11.visibility = View.GONE
                //updateUI()

            }

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            // hide()
        }

    }

    fun callCapturePaymentApi(){

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.capturePayment(
            capturePaymentRequestParams(srId, razorPmtId, packageId, totalPackageAmt,savdEmail)
        )
        responseCall.enqueue(capturePaymentResponse as Callback<capturePaymentResponse>)
    }

    private val capturePaymentResponse = object : NetworkCallBack<capturePaymentResponse>(){
        override fun onSuccessNetwork(data: Any?, response: NetworkResponse<capturePaymentResponse>) {

            response.data?.status?.let { status ->
                Toast.makeText(applicationContext, "Payment done successfully", Toast.LENGTH_SHORT).show()
                //lay11.visibility = View.GONE
                updateUI()

            }

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            // hide()
        }

    }

    fun updateUI(){
        startActivity(Intent(this,GameActivity::class.java))
    }

    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency","INR")
            options.put("amount","100")

            val prefill = JSONObject()
            prefill.put("email","test@razorpay.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        try{
            Toast.makeText(this,"Payment failed $errorCode \n $response",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        try{
            Toast.makeText(this,"Payment Successful $razorpayPaymentId",Toast.LENGTH_LONG).show()
            razorPmtId = razorpayPaymentId.toString()
            callCapturePaymentApi()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }
}