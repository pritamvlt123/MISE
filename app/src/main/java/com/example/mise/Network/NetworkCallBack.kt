package com.example.miseexam

import android.app.Application
import android.util.Log
import com.example.mise.Network.ERROR_CODE_OTHER
import com.example.mise.Network.ERROR_CODE_TIME_OUT
import com.example.mise.Network.ERROR_CODE_UNKNOWN_HOST
import com.example.mise.R
import com.example.mise.Utils.MiseApplication
import com.example.miseexam.utility.NetworkResponse
import retrofit2.Call
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by mymacbookpro on 2020-04-26
 * TODO: Add a class header comment!
 */
abstract class NetworkCallBack<T> : INetworkCallBack<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        Log.e("test","URL=>"+response.raw().request().url())
        if(response.isSuccessful){
            onSuccessNetwork(call, NetworkResponse(response.code(), response.body(), response.headers()))
        } else{
            onFailureNetwork(call, NetworkError(response.code(), response.message()))
        }
    }

    final override fun onFailure(call: Call<T>, t: Throwable) {
        var networkError = when(t){
            is UnknownHostException -> NetworkError(
                ERROR_CODE_UNKNOWN_HOST,
                MiseApplication.getAppContext()?.getString(R.string.generic_no_internet) ?: "")
            is SocketTimeoutException -> NetworkError(
                ERROR_CODE_TIME_OUT,
                MiseApplication.getAppContext()?.getString(R.string.request_timeout) ?: "")
            else -> if(t.message != null) NetworkError(ERROR_CODE_OTHER, t.message) else NetworkError(
                ERROR_CODE_OTHER,
                MiseApplication.getAppContext()?.getString(R.string.generic_no_internet) ?: "")
        }
        onFailureNetwork(call, networkError)
    }

}