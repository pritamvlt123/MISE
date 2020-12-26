package com.example.mise.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mise.R
import kotlinx.android.synthetic.main.activity_faq.*

class FaqActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        title = "FAQ"
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.allowFileAccess = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        // webSettings.setAppCachePath(filesDir.absolutePath +"/cache")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //view.loadUrl(url)
                return false
            }
        }
        webView.loadUrl("file:///android_asset/index.html")

    }
}