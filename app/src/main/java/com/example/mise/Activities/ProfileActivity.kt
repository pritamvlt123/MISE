package com.example.mise.Activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.mise.Network.ApiClient
import com.example.mise.Network.ApiInterface
import com.example.mise.R
import com.example.miseexam.NetworkCallBack
import com.example.miseexam.NetworkError
import com.example.miseexam.utility.NetworkResponse
import com.example.miseexam.utility.getStudentImageRequestParams
import com.example.miseexam.utility.getStudentImageResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ProfileActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private val sharedPrefFile = "kotlinsharedpreference"
    private var imageUtils : ImageUtils?=null
    private var imageUrl : String? = null
    private var cameraImgUri : Uri?= null
    var sharedemailValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Basic Info"))
        tabLayout.addTab(tabLayout.newTab().setText("Package Info"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyProfileAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharednameValue = sharedPreferences.getString("name_key","")
        sharedemailValue = sharedPreferences.getString("email_key","").toString()
        name.text = sharednameValue
        getImages()
        imageViewEdit.setOnClickListener{

            AlertDialog.Builder(this)
                .setTitle("Select Image for profile")
                //.setMessage("This is a simple alert dialog")
                .setPositiveButton("Camera") { dialog, which ->
                    openCamera()
                }
                .setNeutralButton("Gallery") { dialog, which ->
                    openGallery()
                }
                .show()

        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Home-> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }

                R.id.Register-> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }

                R.id.Exams-> {
                    val intent = Intent(this, ExamDetailsActivity::class.java)
                    startActivity(intent)
                }
                R.id.Profile-> {
                    it.setIcon(R.drawable.ic_class)

                }

            }
            false

        }

    }

    private fun openGallery() {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 1)
    }

    private fun openCamera() {
        checkPermission(Manifest.permission.CAMERA, 100);
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> if (resultCode === Activity.RESULT_OK) {
                val extras: Bundle = data?.extras!!
                val imageBitmap = extras["data"] as Bitmap?
                imageView2.setImageBitmap(imageBitmap)
                upload(File(imageBitmap.toString()),sharedemailValue)
            }
            1 -> if (resultCode === Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data
                imageView2.setImageURI(selectedImage)
                if (selectedImage != null) {
                    upload(File(selectedImage.toString()),sharedemailValue)
                  //  uploadImage(selectedImage)
                }
            }

        }
    }
    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission)
            == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this, arrayOf(permission),
                requestCode
            )
        } else {
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, 0) //zero can be replaced with any action code

        }
    }

    fun getImages(){
        val param = getStudentImageRequestParams(
           sharedemailValue
        )

        val apiInterface = ApiClient.getInstance().client.create(ApiInterface::class.java)
        val responseCall = apiInterface.getImage(param)
        responseCall.enqueue(getStudentImageResponse as Callback<getStudentImageResponse>)
    }
    private val getStudentImageResponse = object : NetworkCallBack<getStudentImageResponse>() {
        override fun onSuccessNetwork(
            data: Any?,
            response: NetworkResponse<getStudentImageResponse>
        ) {
            Toast.makeText(applicationContext, "RESult"+ response.data?.count.toString(), Toast.LENGTH_SHORT)
                .show()
            if (response.data?.count!! > 0){
                var imgDetail = response.data?.Details?.get(0)
                if (imgDetail != null) {

                    val path = imgDetail.path.toString()
                    Picasso.get().load(path).fit().into(imageView2)
                }
            }
        }


        override fun onFailureNetwork(data: Any?, error: NetworkError) {
        }
    }

    class upload(val file: File,
                           var email:String?) : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            // ...G
            GloblalDataRepository.getInstance().uploadImage(file,email)


            return ""
        }

        override fun onPreExecute() {
            super.onPreExecute()

            // ...
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            // ...
        }
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        init {
            execute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        while (keyCode== KeyEvent.KEYCODE_BACK){
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
            return true

        }
        return super.onKeyDown(keyCode, event)
    }

}