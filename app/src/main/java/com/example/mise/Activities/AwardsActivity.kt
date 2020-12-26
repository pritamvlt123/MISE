package com.example.mise.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.mise.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_full_registration.*

class AwardsActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awards)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbarLayout.title = "Awards"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Instant Exam"))
        tabLayout.addTab(tabLayout.newTab().setText("Scheduled Exam"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = AwardsAdapter(this, supportFragmentManager,
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
                tabLayout.visibility = View.VISIBLE
                viewPager.visibility = View.VISIBLE

            }
        })
    }
}