package com.example.mise.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.NetworkResponse
import com.example.miseexam.utility.QuickRegisterRequestParams
import com.example.miseexam.utility.QuickRegisterResponse
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    var clasid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val fullname = findViewById<EditText>(R.id.fullname)
        val email = findViewById<EditText>(R.id.email)
        val mobile = findViewById<EditText>(R.id.mobile)
        val password = findViewById<EditText>(R.id.password)
        val confirmpassword = findViewById<EditText>(R.id.confirmpassword)
        val medium = findViewById<Spinner>(R.id.mediumList)
        val classl = findViewById<Spinner>(R.id.classList)


        val login = findViewById<Button>(R.id.sigin)
        login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        val register = findViewById<Button>(R.id.login)
        register.setOnClickListener {
            register()
        }
        val languages = resources.getStringArray(R.array.class_List)

        // access the spinner

        if (classList != null) {
            val adapter = this?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item, languages)
            }
            classList.adapter = adapter

            classList.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    //Toast.makeText(this@RegisterActivity, ""+position, Toast.LENGTH_SHORT).show()

                    clasid = position

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



    }

    var hud: KProgressHUD? = null
    fun  showHud(){
        if (hud!=null){

            hud!!.show()
        }
    }

    fun hide(){
        hud?.dismiss()
        startActivity(Intent(this,LoginActivity::class.java))
    }
    fun register(){
        if (fullname.text.toString()?.trim() == ""){
            Toast.makeText(this@RegisterActivity, "Please enter fullname", Toast.LENGTH_SHORT).show()
        }
        else if (email.text.toString()?.trim() == ""){
            Toast.makeText(this@RegisterActivity, "Please enter email", Toast.LENGTH_SHORT).show()
        }
        else if (mobile.text.toString()?.trim() == ""){
            Toast.makeText(this@RegisterActivity, "Please enter mobile", Toast.LENGTH_SHORT).show()
        }
        else if (mediumList.selectedItem.toString() == "--Select Medium--"){
            Toast.makeText(this@RegisterActivity, "Please select medium", Toast.LENGTH_SHORT).show()
        }
        else if (classList.selectedItem.toString() == "--Select Class--"){
            Toast.makeText(this@RegisterActivity, "Please select class", Toast.LENGTH_SHORT).show()
        }
        else if (password.text.toString()?.trim() == ""){
            Toast.makeText(this@RegisterActivity, "Please enter password", Toast.LENGTH_SHORT).show()
        }
        else if (confirmpassword.text.toString()?.trim() == ""){
            Toast.makeText(this@RegisterActivity, "Please enter confirm password", Toast.LENGTH_SHORT).show()
        }
        else if (confirmpassword.text.toString()?.trim() != password.text.toString()?.trim()){
            Toast.makeText(this@RegisterActivity, "password not matched", Toast.LENGTH_SHORT).show()
        }
        else {
            val param = QuickRegisterRequestParams(
                fullname.text.toString().trim(),
                email.text.toString().trim(),
                mobile.text.toString().trim(),
                "INDIA",
                clasid,
                mediumList.selectedItem.toString(),
                password.text.toString().trim()


            )
            showHud()
            //Toast.makeText(this@RegisterActivity, "param"+param, Toast.LENGTH_SHORT).show()

            val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
            val responseCall = apiInterface.createQuickRegister(param)
            responseCall.enqueue(QuickRegisterResponse as Callback<QuickRegisterResponse>)
        }
    }
    private val QuickRegisterResponse = object : NetworkCallBack<QuickRegisterResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<QuickRegisterResponse>
        ) {
            response.data?.respMessage?.let { status ->
                Toast.makeText(this@RegisterActivity, "User registered successfully", Toast.LENGTH_SHORT)
                    .show()
                hide()


            }

        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            hide()
        }

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