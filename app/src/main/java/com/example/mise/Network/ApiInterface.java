package com.example.mise.Network;

import com.example.mise.Model.FreePackageSubjects;
import com.example.mise.Model.PlanPackageFetch;
import com.example.mise.Model.PlanPackageStatus;
import com.example.mise.Model.QuestionDetail;
import com.example.mise.Model.QuestionFetch;
import com.example.mise.Model.SubjectsFetch;
import com.example.mise.Model.QuestionStatus;
import com.example.miseexam.utility.QuickRegisterRequestParams;import com.example.miseexam.utility.QuickRegisterResponse;import com.example.miseexam.utility.addSubAfterPaymentRequestParams;import com.example.miseexam.utility.addSubAfterPaymentResponse;import com.example.miseexam.utility.capturePaymentRequestParams;import com.example.miseexam.utility.capturePaymentResponse;import com.example.miseexam.utility.forgotPassRequestParams;import com.example.miseexam.utility.forgotPassResponse;import com.example.miseexam.utility.fullRegisterRequestParams;import com.example.miseexam.utility.fullRegisterResponse;import com.example.miseexam.utility.getStudentImageRequestParams;import com.example.miseexam.utility.getStudentImageResponse;import com.example.miseexam.utility.loginRequestParams;import com.example.miseexam.utility.loginResponse;import com.example.miseexam.utility.planPackageRequestParams;import com.example.miseexam.utility.planPackageResponse;import com.example.miseexam.utility.resultRequestParams;import com.example.miseexam.utility.resultResponse;import com.example.miseexam.utility.updatePasswordRequestParams;import com.example.miseexam.utility.updatePasswordResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.example.mise.Network.ConstantAPIKt.Add_Selected_Subjects_After_Payment;
import static com.example.mise.Network.ConstantAPIKt.Capture_Payment;
import static com.example.mise.Network.ConstantAPIKt.Forgot_Password;
import static com.example.mise.Network.ConstantAPIKt.FullRegister;
import static com.example.mise.Network.ConstantAPIKt.Get_PracticeZone_Questions;
import static com.example.mise.Network.ConstantAPIKt.Get_Result;
import static com.example.mise.Network.ConstantAPIKt.Get_Student_Image;
import static com.example.mise.Network.ConstantAPIKt.Get_Subjects;
import static com.example.mise.Network.ConstantAPIKt.Login;
import static com.example.mise.Network.ConstantAPIKt.Plan_Package;
import static com.example.mise.Network.ConstantAPIKt.Quick_Register;
import static com.example.mise.Network.ConstantAPIKt.Update_Password;
import static com.example.mise.Network.ConstantAPIKt.Upload_Profile_Image;


public interface ApiInterface {


    @POST(Quick_Register)
    Call<QuickRegisterResponse> createQuickRegister(@Body QuickRegisterRequestParams model);

    @POST(Login)
    Call<loginResponse> createLogin(@Body loginRequestParams model);

    @POST(Update_Password)
    Call<updatePasswordResponse> updtePassword(@Body updatePasswordRequestParams model);

    @POST(FullRegister)
    Call<fullRegisterResponse> createfullRegistration(@Body fullRegisterRequestParams model);

    @POST(Plan_Package)
    Call<planPackageResponse> createplanPackage(@Body planPackageRequestParams model);

    @POST(Forgot_Password)
    Call<forgotPassResponse> forgotPassword(@Body forgotPassRequestParams model);

    @POST(Add_Selected_Subjects_After_Payment)
    Call<addSubAfterPaymentResponse> addPaymentAfterSubject(@Body addSubAfterPaymentRequestParams model);

    @POST(Capture_Payment)
    Call<capturePaymentResponse> capturePayment(@Body capturePaymentRequestParams model);

    @POST(Get_Result)
    Call<resultResponse> getResults(@Body resultRequestParams model);

    @POST(Get_Student_Image)
    Call<getStudentImageResponse> getImage(@Body getStudentImageRequestParams model);

    @Multipart
    @POST(Upload_Profile_Image)
    Call<RequestBody> uploadImage(@Part MultipartBody.Part image, @Part("SR_Email") RequestBody SR_Email);

    @POST(Get_PracticeZone_Questions)
    Call<QuestionStatus> getQuestions(@Body QuestionFetch model);

    @POST(Get_Subjects)
    Call<FreePackageSubjects> getsubjects(@Body SubjectsFetch model);

    @POST(Plan_Package)
    Call<PlanPackageStatus> getsubjectsperplan(@Body PlanPackageFetch model);


}
