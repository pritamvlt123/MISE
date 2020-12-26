package com.example.mise.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.R

class SplashActivity : AppCompatActivity() {
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val imageView= findViewById<ImageView>(R.id.imageView);
        val textView= findViewById<TextView>(R.id.txtView);
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedidValue = sharedPreferences.getString("id_key","true")
        //Toast.makeText(this, "param"+sharedidValue.toString(), Toast.LENGTH_SHORT).show()
        val animationFromTop = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation)
        imageView.startAnimation(animationFromTop)

        val animationFromBottom = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation_bottom)
        textView.startAnimation(animationFromBottom)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            if(sharedidValue == "true"){
                startActivity(Intent(this,SignupActivity::class.java))


            }
            else{
                startActivity(Intent(this,DashboardActivity::class.java))
            }


            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}