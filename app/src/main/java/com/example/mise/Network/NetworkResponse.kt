package com.example.miseexam.utility

import okhttp3.Headers

/**
 * Created by mymacbookpro on 2020-04-26
 * Base Network response
 */
class NetworkResponse<T>(val code:Int?, val data:T?, headers: Headers)