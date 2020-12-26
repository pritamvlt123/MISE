package com.example.mise.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.*
import com.kaopiz.kprogresshud.KProgressHUD
import com.royrodriguez.transitionbutton.TransitionButton
import com.royrodriguez.transitionbutton.TransitionButton.OnAnimationStopEndListener
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Callback


class LoginActivity : AppCompatActivity() {

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
    var sharedPrefFile = "kotlinsharedpreference"
    var passwrd = ""
    var srId = ""
    var SR_Password = ""
    var SR_Source = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val register = findViewById<TransitionButton>(R.id.signup)
        val forgot = findViewById<TextView>(R.id.forgotPass)
        val transition_button = findViewById<TransitionButton>(R.id.login)

        register.setOnClickListener {
            register.startAnimation();
            val handler = Handler()
            Handler(Looper.getMainLooper()).postDelayed({
                /* Create an Intent that will start the Menu-Activity. */
                register.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, OnAnimationStopEndListener {
                    startActivity(Intent(this,RegisterActivity::class.java))
                })

            }, 1000)



        }
        forgot.setOnClickListener {
            forgotApi()
        }
        transition_button.setOnClickListener {
                    login()

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

    }
    fun login(){
        val pass = findViewById<EditText>(R.id.password)
        if (username.text.toString()?.trim() == ""){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
        }
        else if (pass.text.toString()?.trim() == ""){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
        }
        else{
            val param = loginRequestParams(
                username.text.toString().trim(),
                pass.text.toString().trim()


            )
            showHud()
            //Toast.makeText(this, "param"+param, Toast.LENGTH_SHORT).show()
            passwrd = pass.text.toString()?.trim()
            val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
            val responseCall = apiInterface.createLogin(param)
            responseCall.enqueue(loginResponse as Callback<loginResponse>)

        }
    }



    private val loginResponse = object : NetworkCallBack<loginResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<loginResponse>
        ) {
            response.data?.status?.let { status ->
                if (status == 1) {

                    Toast.makeText(
                        this@LoginActivity,
                        "Loggedin successfully" /*+ response.data.toString()*/,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    hide()

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
                    srId = response.data.userDetails?.SR_ID.toString()
                    SR_Password = response.data.userDetails?.SR_Password.toString()
                    SR_Source = response.data.userDetails?.SR_Source.toString()

                    updateUI()
                }
                else{
                    Toast.makeText(this@LoginActivity, "Loggedin Error!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            hide()
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
        editor.putString("password",passwrd)
        editor.putString("SR_id",srId)
        editor.putString("SR_password",SR_Password)
        editor.putString("SR_Source",SR_Source)
        editor.apply()
        editor.commit()
        startActivity(Intent(this,DashboardActivity::class.java))
        finish()

    }

    fun forgotApi(){


        if (username.text.toString()?.trim() == ""){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
        }
        else{
            val param = forgotPassRequestParams(
                username.text.toString().trim()


            )

            val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
            val responseCall = apiInterface.forgotPassword(param)
            responseCall.enqueue(forgotPassResponse as Callback<forgotPassResponse>)

        }
    }
    private val forgotPassResponse = object : NetworkCallBack<forgotPassResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<forgotPassResponse>
        ) {
            response.data?.status?.let { status ->
                if (status == 1) {

                    Toast.makeText(
                        this@LoginActivity,
                        response.data.respMessage+" to your email",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
                else{
                    Toast.makeText(this@LoginActivity, "Error!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
            hide()
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        while (keyCode==KeyEvent.KEYCODE_BACK){
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
            return true

        }
        return super.onKeyDown(keyCode, event)
    }

}