package com.example.mise.Utils

import android.app.Application
import android.content.Context

class MiseApplication : Application(){

    init{
        instance = this
    }
    companion object {
        var instance:MiseApplication? = null
        var globalRole = ""
        var fromBeat = ""
        var filterFrom = ""
        var fromProductList = ""
        @JvmStatic
        fun getAppContext(): Context?{
            return instance?.applicationContext
        }
    }
}