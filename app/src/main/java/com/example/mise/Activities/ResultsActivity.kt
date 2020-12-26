package com.example.mise.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {
    var classtype = "KG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        val roll = findViewById<EditText>(R.id.roll)
        collapsingToolbarLayout.title = "Results"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        val no = findViewById<EditText>(R.id.no)
        lateinit var radioButton: RadioButton
        val radioGrp2 = findViewById<RadioGroup>(R.id.radioGroup11)
        radioGrp2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            radioButton = findViewById(radioGrp2.checkedRadioButtonId)
            //Toast.makeText(applicationContext, "Radio Button: ${radioButton.text}", Toast.LENGTH_LONG).show()
            if (radioButton.text == "KG") {
                classtype = "KG"
            } else {

                classtype = "upper"
            }
        })

        val register = findViewById<Button>(R.id.reslts)
        register.setOnClickListener {
            if (roll.text.toString()?.trim() == ""){
                Toast.makeText(applicationContext, "Please enter roll", Toast.LENGTH_SHORT).show()
            }
            else if (no.text.toString()?.trim() == ""){
                Toast.makeText(applicationContext, "Please enter no", Toast.LENGTH_SHORT).show()
            }
            else {
                getResults()
            }
        }
    }

    fun getResults(){
        val intent = Intent(applicationContext, ReportCardActivity::class.java)
        intent.putExtra("roll", roll.text.toString())
        intent.putExtra("no", no.text.toString())
        intent.putExtra("classtype", classtype)
        startActivity(intent)

    }
}