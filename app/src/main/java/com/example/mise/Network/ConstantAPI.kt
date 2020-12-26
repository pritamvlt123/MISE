package com.example.mise.Network

import java.io.File

/**
 * Created by mymacbookpro on 2020-04-30
 * TODO: Add a class header comment!
 */
object ConstantAPI {
    val prod:String get() = ""

    val test:String get() = "https://reqres.in"

    val dev:String get() = "https://www.misexam.com/API/"
}

///////////////////////////////////////////////////////////////////////

const val CONNECT_TIME_OUT:Long = 1
const val READ_TIME_OUT:Long = 15
const val WRITE_TIME_OUT:Long = 15

const val ERROR_CODE_UNKNOWN_HOST = 604
const val ERROR_CODE_TIME_OUT = 408
const val ERROR_CODE_OTHER = 600

///////////////////////////////////////////////////////////////////////

const val APP_API_NAME = "https://www.misexam.com/API/"

const val Quick_Register = APP_API_NAME+"QuickRegister"
const val Login = APP_API_NAME+"Login"
const val Update_Password = APP_API_NAME+"Update_Password"
const val FullRegister = APP_API_NAME+"FullRegister"
const val Plan_Package = APP_API_NAME+"Plan_Package"
const val Forgot_Password = APP_API_NAME+"Forgot_Password"
const val Add_Selected_Subjects_After_Payment = APP_API_NAME+"Add_Selected_Subjects_After_Payment"
const val Capture_Payment = APP_API_NAME+"Capture_Payment"
const val Get_Result = APP_API_NAME+"Get_Result"
const val Get_Student_Image = APP_API_NAME+"Get_Student_Image"
const val Upload_Profile_Image = APP_API_NAME+"Upload_Profile_Image"
const val Get_PracticeZone_Questions = APP_API_NAME+"Get_PracticeZone_Questions"
const val Get_Subjects = APP_API_NAME+"Get_Subjects"