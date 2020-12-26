package com.example.mise.Activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.mise.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class ExamDetailsActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_details)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        collapsingToolbarLayout.title = "Exam Details"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        tabLayout.addTab(tabLayout.newTab().setText("Instant Exam"))
        //tabLayout.addTab(tabLayout.newTab().setText("Scheduled Exam"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ExamDetailsAdapter(this, supportFragmentManager,
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
        lateinit var radioButton: RadioButton
        val radioGrp2 = findViewById<RadioGroup>(R.id.radioGroup11)
        radioGrp2.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            radioButton = findViewById(radioGrp2.checkedRadioButtonId)
            //Toast.makeText(applicationContext, "Radio Button: ${radioButton.text}", Toast.LENGTH_LONG).show()
            if (radioButton.text == "Offline"){
                tabLayout.visibility = View.GONE
                viewPager.visibility = View.GONE
            }
            else{
                tabLayout.visibility = View.GONE
                viewPager.visibility = View.VISIBLE

            }
        })

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
                    it.setIcon(R.drawable.ic_class)
                }
                R.id.Profile-> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }

            }
            false

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