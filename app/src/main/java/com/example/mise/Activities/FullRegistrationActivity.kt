package com.example.mise.Activities

import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_full_registration.*
import retrofit2.Callback
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class FullRegistrationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener  {
    private val sharedPrefFile = "kotlinsharedpreference"
    var srctxt = "NA"
    var sharedmedValue = ""
    var sharedclassValue = ""
    var password = ""
    var sharedemailValue = ""
    var idtx = ""
    var nametx = ""
    var emailtx = ""
    var mobiletx = ""
    var packagetx = ""
    var gendertx = ""
    var dobtx = ""
    var gurdiantx = ""
    var reltx = ""
    var classtx = ""
    var mediumtx = ""
    var srId = ""
    var shSR_password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_registration)

        val view1 = findViewById<RelativeLayout>(R.id.vw1)
        //val view2 = findViewById<LinearLayout>(R.id.vw2)
        val next = findViewById<Button>(R.id.next)
        //val submit = findViewById<Button>(R.id.submit)

        lateinit var radioButton: RadioButton
       // val radioGrp = findViewById<RadioGroup>(R.id.radioGroup)
        val radioGrp2 = findViewById<RadioGroup>(R.id.radioGroup11)
        //view1.visibility = View.VISIBLE
        //radioGrp.clearCheck()

        next.setOnClickListener {
            //view1.visibility = View.GONE
            //view2.visibility = View.VISIBLE

            fullRegister()
        }
//        submit.setOnClickListener {
//            fullRegister()
//        }
//        radioGrp.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
//            radioButton = findViewById(radioGrp.checkedRadioButtonId)
//            Toast.makeText(applicationContext, "Radio Button: ${radioButton.text}", Toast.LENGTH_LONG).show()
//        })


        radioGrp2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            radioButton = findViewById(radioGrp2.checkedRadioButtonId)
            //Toast.makeText(applicationContext, "Radio Button: ${radioButton.text}", Toast.LENGTH_LONG).show()
            if (radioButton.text == "Offline"){
                rl_sourcespin.visibility = View.VISIBLE
            }
            else{
                rl_sourcespin.visibility = View.GONE
                srctxt = "NA"
            }
        })

        val languages = resources.getStringArray(R.array.source)

        // access the spinner

        if (sourcespin != null) {
            val adapter = this?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item, languages)
            }
            sourcespin.adapter = adapter

            sourcespin.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    //Toast.makeText(applicationContext, ""+position, Toast.LENGTH_SHORT).show()

                    srctxt = languages[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        et_end_date.setOnClickListener {
            showDatePickerDialog()
        }
        
    }



    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val datePickerDialog =  DatePickerDialog(this, this, year, month, dayOfMonth);
        //following line to restrict future date selection
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis())
        datePickerDialog.show();
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val tempDate: Date = DateUtility.getDateFromYearMonthDay(year, month, dayOfMonth)
        val subDateString: String =
            DateUtility.getStringDateFromTimestamp((tempDate.time), DateUtility.dd_MM_yy)
        et_end_date.setText(subDateString)
    }

    fun fullRegister(){
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedidValue = sharedPreferences.getString("id_key","true")
        val sharednameValue = sharedPreferences.getString("name_key","")
         sharedemailValue = sharedPreferences.getString("email_key","").toString()
        val sharedmobileValue = sharedPreferences.getString("mobile_key","")
        sharedclassValue = sharedPreferences.getString("class_key","").toString()
        sharedmedValue = sharedPreferences.getString("medium_key","").toString()
        password = sharedPreferences.getString("password","").toString()
        shSR_password = sharedPreferences.getString("SR_password","").toString();
        val inpFormat = SimpleDateFormat(DateUtility.dd_MM_yy, Locale.US);
        val outputformat = SimpleDateFormat("yyyy-MM-dd", Locale.US);
        val date =
            parseDate(et_end_date.text.toString(), inpFormat, outputformat)
        //Toast.makeText(applicationContext, "Full registration done successfully"+sharedidValue, Toast.LENGTH_SHORT)
            //.show()
        //return
        val param = fullRegisterRequestParams(
            sharedidValue.toString(),
            sharednameValue.toString(),
            gurdianName.text.toString(),
            date.toString(),
            relList.selectedItem.toString(),
            sharedclassValue,
            sharedemailValue.toString(),
            sharedmobileValue.toString(),
            schoolName.text.toString(),
            schoolCity.text.toString(),
            "0",
            "INDIA",
            shSR_password,
            gender.selectedItem.toString(),
            resaddress.text.toString(),
            state.text.toString(),
            district.text.toString(),
            city.text.toString(),
            pin.text.toString(),
            sharedmedValue,
            srctxt,
            "",
            examCntry.text.toString(),
            examcity.text.toString(),
            examstate.text.toString()



        )

        //Toast.makeText(this, "param"+param, Toast.LENGTH_SHORT).show()

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.createfullRegistration(param)
        responseCall.enqueue(fullRegisterResponse as Callback<fullRegisterResponse>)
    }
    private val fullRegisterResponse = object : NetworkCallBack<fullRegisterResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<fullRegisterResponse>
        ) {
            response.data?.status?.let { status ->
                Toast.makeText(applicationContext, "Full registration done successfully", Toast.LENGTH_SHORT)
                    .show()
                srId = response.data.SR_ID.toString()
                updateNav(sharedclassValue,srctxt,sharedmedValue)
                loginUpdate()
            }

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {

        }

    }
    fun parseDate(
        inputDateString: String?,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDateString
    }
    fun updateNav(classdata:String,sourc:String,medium:String){
        val intent = Intent(this, PackageActivity::class.java)
        intent.putExtra("class", classdata)
        intent.putExtra("source", sourc)
        intent.putExtra("medium", medium)
        startActivity(intent)
    }
    fun loginUpdate(){
        val param = loginRequestParams(
            sharedemailValue,
            password


        )

        //Toast.makeText(this, "param"+param, Toast.LENGTH_SHORT).show()

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.createLogin(param)
        responseCall.enqueue(loginResponse as Callback<loginResponse>)
    }
    private val loginResponse = object : NetworkCallBack<loginResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<loginResponse>
        ) {
            response.data?.status?.let { status ->
                idtx = response.data.userDetails?.POR_ID.toString()
                nametx = response.data.userDetails?.SR_Name.toString()
                emailtx = response.data.userDetails?.SR_Email.toString()
                mobiletx = response.data.userDetails?.SR_Phone.toString()
                packagetx = response.data.userDetails?.SR_Package.toString()
                classtx = response.data.userDetails?.SR_Class.toString()
                dobtx = response.data.userDetails?.SR_DOB.toString()
                gurdiantx = response.data.userDetails?.SR_Guardian_Name.toString()
                gendertx = response.data.userDetails?.SR_Gender.toString()
                reltx = response.data.userDetails?.SR_Guardian_Rel.toString()
                mediumtx = response.data.userDetails?.SR_Medium.toString()
                srId = response.data.userDetails?.SR_ID.toString();
                updateUI()
            }

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {

        }

    }
    fun updateUI(){
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("id_key",idtx)
        editor.putString("name_key",nametx)
        editor.putString("email_key",emailtx)
        editor.putString("mobile_key",mobiletx)
        editor.putString("pkgType_key",packagetx)
        editor.putString("gender_key",gendertx)
        editor.putString("dob_key",dobtx)
        editor.putString("rel_key",reltx)
        editor.putString("class_key",classtx)
        editor.putString("guardian_key",gurdiantx)
        editor.putString("medium_key",mediumtx)
        editor.putString("SR_id",srId)
        editor.apply()
        editor.commit()


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        while (keyCode== KeyEvent.KEYCODE_BACK){
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
            return true

        }
        return super.onKeyDown(keyCode, event)
    }

}