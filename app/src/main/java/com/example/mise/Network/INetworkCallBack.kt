package com.example.miseexam

import com.example.miseexam.utility.NetworkResponse
import retrofit2.Callback

/**
 * Created by mymacbookpro on 2020-04-26
 * TODO: Add a class header comment!
 */
interface INetworkCallBack<T> : Callback<T> {
    fun onSuccessNetwork(data:Any?, response: NetworkResponse<T>)
    fun onFailureNetwork(data:Any?, error: NetworkError)
}