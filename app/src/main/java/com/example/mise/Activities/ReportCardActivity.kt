package com.example.mise.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.NetworkResponse
import com.example.miseexam.utility.resultRequestParams
import com.example.miseexam.utility.resultResponse
import retrofit2.Callback

class ReportCardActivity : AppCompatActivity() {
    var classtype = ""
    var roll = ""
    var no =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_card)
        title = "Result Details"
        val school = findViewById<TextView>(R.id.school)
        val name = findViewById<TextView>(R.id.sname)
        val fname = findViewById<TextView>(R.id.sfname)
        val rollno = findViewById<TextView>(R.id.srollno)
        val classname = findViewById<TextView>(R.id.cls)
        val sub1 = findViewById<TextView>(R.id.subj1)
        val sub2 = findViewById<TextView>(R.id.subj2)
        val mark1 = findViewById<TextView>(R.id.mrks1)
        val mark2 = findViewById<TextView>(R.id.mrks2)
        val totalMrks = findViewById<TextView>(R.id.totalmrk)
        val markobt = findViewById<TextView>(R.id.obtotalmrk)
        val percntg = findViewById<TextView>(R.id.pertotalmrk)
        val grade = findViewById<TextView>(R.id.grd)
        val remarks = findViewById<TextView>(R.id.rmrk)
        classtype = intent.getStringExtra("classtype").toString()
        roll = intent.getStringExtra("roll").toString().toString()
        no =intent.getStringExtra("no").toString()
        getResults()
    }

    fun getResults(){
        val param = resultRequestParams(
            classtype,
            roll,
            no
        )

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.getResults(param)
        responseCall.enqueue(resultResponse as Callback<resultResponse>)
    }
    private val resultResponse = object : NetworkCallBack<resultResponse>(){
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<resultResponse>
        ) {

            Toast.makeText(applicationContext, "RESult"+ response.data?.Result.toString(), Toast.LENGTH_SHORT)
                .show()
            val school = findViewById<TextView>(R.id.school)
            val name = findViewById<TextView>(R.id.sname)
            val fname = findViewById<TextView>(R.id.sfname)
            val rollno = findViewById<TextView>(R.id.srollno)
            val classname = findViewById<TextView>(R.id.cls)
            val sub1 = findViewById<TextView>(R.id.subj1)
            val sub2 = findViewById<TextView>(R.id.subj2)
            val mark1 = findViewById<TextView>(R.id.mrks1)
            val mark2 = findViewById<TextView>(R.id.mrks2)
            val totalMrks = findViewById<TextView>(R.id.totalmrk)
            val markobt = findViewById<TextView>(R.id.obtotalmrk)
            val percntg = findViewById<TextView>(R.id.pertotalmrk)
            val grade = findViewById<TextView>(R.id.grd)
            val remarks = findViewById<TextView>(R.id.rmrk)
            var result = response.data?.Result?.get(0)
            if (result != null) {
                school.text = result.School_Name
                name.text = "Student Name : "+result.NAME
                fname.text = "Guardian’s/Father’s Name : "+result.FATHER_NAME
                rollno.text = "Roll : "+result.ROLL+"    No :"+result.NO
                classname.text = "Class: "+result.CLASS
                mark1.text = result.MATH_SCI
                mark2.text = result.ENGLISH_GK
                totalMrks.text = result.FULL_MARKS
                markobt.text = result.MARKS_OBTAINED
                percntg.text = result.PER_OBTAINED
                grade.text = result.GRADE
                remarks.text = result.REMARKS
                val subjDet = result.TOTAL_SUBJECT
                var resultStr= subjDet?.split(",")?.map { it.trim() }
                if (resultStr != null) {
                    sub1.text = resultStr.get(0)
                    sub2.text = resultStr.get(1)
                }
            }
        }

        override fun onFailureNetwork(data: Any?, error: NetworkError) {
        }

    }
}