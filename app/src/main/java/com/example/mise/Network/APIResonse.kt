package com.example.miseexam.utility

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseModel {

}
data class  QuickRegisterRequestParams(
    @SerializedName("SR_Name") var SR_Name: String,
    @SerializedName("SR_Email") var SR_Email: String,
    @SerializedName("SR_Phone") var SR_Phone: String,
    @SerializedName("SR_Country_Name") var SR_Country_Name: String,
    @SerializedName("SR_Class") var SR_Class: Int,
    @SerializedName("SR_Medium") var SR_Medium: String,
    @SerializedName("SR_Password") var SR_Password: String
): BaseModel()



data class QuickRegisterResponse(
    val status: Int?,
    val respMessage: String,
    val POR_ID: Int
)

data class  loginRequestParams(
    @SerializedName("SR_Email") var SR_Email: String,
    @SerializedName("SR_Password") var SR_Password: String
): BaseModel()



data class loginResponse(
    val status: Int?,
    @SerializedName("userDetails")
    val userDetails: UserDetail?
):Serializable


data class UserDetail(
    @SerializedName("POR_ID")
    val POR_ID: String?,
    @SerializedName("SR_ID")
    val SR_ID: String?,
    @SerializedName("SR_Name")
    val SR_Name: String?,
    @SerializedName("SR_Email")
    val SR_Email: String?,
    @SerializedName("SR_Medium")
    val SR_Medium: String?,
    @SerializedName("SR_Class")
    val SR_Class: String?,
    @SerializedName("SR_Password")
    val SR_Password: String?,
    @SerializedName("SR_Country_Name")
    val SR_Country_Name: String?,
    @SerializedName("SR_Phone")
    val SR_Phone: String?,
    @SerializedName("SR_Source")
    val SR_Source: String?,
    @SerializedName("Create_Date")
    val Create_Date: String?,
    @SerializedName("Modified_Date")
    val Modified_Date: String?,
    @SerializedName("SR_Guardian_Name")
    val SR_Guardian_Name: String?,
    @SerializedName("SR_DOB")
    val SR_DOB: String?,
    @SerializedName("SR_School_Name")
    val SR_School_Name: String?,
    @SerializedName("SR_School_City")
    val SR_School_City: String?,
    @SerializedName("SR_Package")
    val SR_Package: String?,
    @SerializedName("SR_Gender")
    val SR_Gender: String?,
    @SerializedName("SR_Res_Address")
    val SR_Res_Address: String?,
    @SerializedName("SR_State")
    val SR_State: String?,
    @SerializedName("SR_District")
    val SR_District: String?,
    @SerializedName("SR_City")
    val SR_City: String?,
    @SerializedName("SR_PIN")
    val SR_PIN: String?,
    @SerializedName("SR_Coaching_Centre")
    val SR_Coaching_Centre: String?,
    @SerializedName("Exam_Center_Country")
    val Exam_Center_Country: String?,
    @SerializedName("Exam_Center_State")
    val Exam_Center_State: String?,
    @SerializedName("SR_Guardian_Rel")
    val SR_Guardian_Rel: String?



):  Serializable

data class  updatePasswordRequestParams(
    @SerializedName("SR_Email") var SR_Email: String,
    @SerializedName("SR_Password") var SR_Password: String
): BaseModel()



data class updatePasswordResponse(
    val status: Int?,
    val respMessage: String,
    val SR_Email: String
):Serializable

data class  fullRegisterRequestParams(
    @SerializedName("POR_ID") var POR_ID: String,
    @SerializedName("SR_Name") var SR_Name: String,
    @SerializedName("SR_Guardian_Name") var SR_Guardian_Name: String,
    @SerializedName("SR_DOB") var SR_DOB: String,
    @SerializedName("SR_Guardian_Rel") var SR_Guardian_Rel: String,
    @SerializedName("SR_Class") var SR_Class: String,
    @SerializedName("SR_Email") var SR_Email: String,
    @SerializedName("SR_Phone") var SR_Phone: String,
    @SerializedName("SR_School_Name") var SR_School_Name: String,
    @SerializedName("SR_School_City") var SR_School_City: String,
    @SerializedName("SR_Package") var SR_Package: String,
    @SerializedName("SR_Country_Name") var SR_Country_Name: String,
    @SerializedName("SR_Password") var SR_Password: String,
    @SerializedName("SR_Gender") var SR_Gender: String,
    @SerializedName("SR_Res_Address") var SR_Res_Address: String,
    @SerializedName("SR_State") var SR_State: String,
    @SerializedName("SR_District") var SR_District: String,
    @SerializedName("SR_City") var SR_City: String,
    @SerializedName("SR_PIN") var SR_PIN: String,
    @SerializedName("SR_Medium") var SR_Medium: String,
    @SerializedName("SR_Source") var SR_Source: String,
    @SerializedName("SR_Coaching_Centre") var SR_Coaching_Centre: String,
    @SerializedName("Exam_Center_Country") var Exam_Center_Country: String,
    @SerializedName("Exam_Center_City") var Exam_Center_City: String,
    @SerializedName("Exam_Center_State") var Exam_Center_State: String
): BaseModel()


data class fullRegisterResponse(
    val status: Int?,
    val respMessage:String?,
    val SR_ID:String?

)

data class  planPackageRequestParams(
    @SerializedName("SR_Class") var SR_Class: String,
    @SerializedName("SR_Source") var SR_Source: String,
    @SerializedName("SR_Medium") var SR_Medium: String
): BaseModel()


data class planPackageResponse(
    val count: Int?,
    @SerializedName("PackageDetails")
    val packageDetails: List<PackageDetails>?,
    @SerializedName("Subjects")
    val subjects: List<Subjects>?

):Serializable

data class PackageDetails(
    val packageID:String?,
    val packageName:String?,
    val packageCostID:String?,
    val classID:String?,
    val className:String?,
    val packagePrice:String?,
    val PM_No_Of_Subject:String?,
    val Type:String?

):Serializable
data class Subjects(
    val SM_ID:String?,
    val SM_CM_ID:String?,
    val SM_Subject_Name:String?,
    val ON_OFF:String?

):Serializable

data class  forgotPassRequestParams(
    @SerializedName("SR_Email") var SR_Email: String
): BaseModel()



data class forgotPassResponse(
    val status: Int?,
    val respMessage: String
):Serializable

data class  addSubAfterPaymentRequestParams(
    @SerializedName("SR_ID") var SR_ID: String,
    @SerializedName("user_selected_subjects_id") var user_selected_subjects_id: ArrayList<String>
): BaseModel()


data class addSubAfterPaymentResponse(
    val status: Int?,
    val respMessage: String
):Serializable

data class  capturePaymentRequestParams(
    @SerializedName("SR_ID") var SR_ID: String,
    @SerializedName("razorpay_payment_id") var razorpay_payment_id: String,
    @SerializedName("PM_ID") var PM_ID: String,
    @SerializedName("totalAmount") var totalAmount: String,
    @SerializedName("SR_Email") var SR_Email: String
): BaseModel()


data class capturePaymentResponse(
    val status: Int?,
    val respMessage: String
):Serializable

data class  resultRequestParams(
    @SerializedName("type") var type: String,
    @SerializedName("Roll") var Roll: String,
    @SerializedName("No") var No: String
): BaseModel()

data class resultResponse(
    val count: Int?,
    @SerializedName("Result")
    val Result: List<Result>?

):Serializable

data class Result(
    val SL_NO:String?,
    val ROLL:String?,
    val NO:String?,
    val NAME:String?,
    val FATHER_NAME:String?,
    val CLASS:String?,
    val MATH_SCI:String?,
    val ENGLISH_GK:String?,
    val ART_PAINTING:String?,
    val MARKS_OBTAINED:String?,
    val FULL_MARKS:String?,
    val PER_OBTAINED:String?,
    val GRADE:String?,
    val REMARKS:String?,
    val TOTAL_SUBJECT:String?,
    val School_Name:String?

):Serializable

data class getStudentImageResponse(
    val status: Int?,
    val count: Int?,
    @SerializedName("Details")
    val Details: List<imageDetails>?

):Serializable

data class  getStudentImageRequestParams(
    @SerializedName("SR_Email") var SR_Email: String
): BaseModel()

data class imageDetails(
    val SR_Email:String?,
    val path:String?
):Serializable