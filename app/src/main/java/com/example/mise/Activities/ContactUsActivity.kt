package com.example.mise.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_contact_us.*


class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbarLayout.title = "Contact Us"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        val callNo = findViewById<Button>(R.id.callbtn)
        val emailto = findViewById<Button>(R.id.emailbtn)
        val whatsappNo = findViewById<Button>(R.id.whatsappbtn)
        callNo.setOnClickListener {
            callUser()
        }
        whatsappNo.setOnClickListener {
            openWhatsAppAndSendMessage()
        }
        emailto.setOnClickListener {
            opengmail()
        }
        faqbtn.setOnClickListener {
            startActivity(Intent(this,FaqActivity::class.java))

        }
    }
        private fun callUser(){
            val u = Uri.parse("tel:" + callbtn.text.toString())

            val i = Intent(Intent.ACTION_DIAL, u)

            try {
                // Launch the Phone app's dialer with a phone
                // number to dial a call.
                startActivity(i)
            } catch (s: SecurityException) {
                // show() method display the toast with
                // exception message.
                Log.e("Error::","error while opening call");
            }

        }

    fun openWhatsAppAndSendMessage() {

        val sendIntent = Intent("android.intent.action.MAIN")
        sendIntent.action = Intent.ACTION_VIEW
        sendIntent.setPackage("com.whatsapp")
        val url =
            "https://api.whatsapp.com/send?phone=" + "917602325813" + "&text=" + "MISE Exam"
        sendIntent.data = Uri.parse(url)
        startActivity(sendIntent)

    }
    fun opengmail(){
        val email = Intent(Intent.ACTION_VIEW)
                email.setType("message/rfc822")
            .setData(Uri.parse("mailto:"+emailbtn.text.toString()))
            .putExtra(Intent.EXTRA_EMAIL, emailbtn.text.toString())
            .putExtra(Intent.EXTRA_SUBJECT, "MISE Exam")
            .putExtra(Intent.EXTRA_TEXT, "My Email message")
            .setPackage("com.google.android.gm")
        startActivity(email)
    }


}