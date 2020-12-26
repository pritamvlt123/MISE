package com.example.mise.Activities

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mise.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.*

class AboutUsActivity : AppCompatActivity() {
    private var expandableListView: ExpandableListView? = null
    //private var adapter: ExpandableListAdapter? = null
    //private var titleList: List<String>? = null
    private var lastExpandedPosition = -1
    var listAdapter: CustomExpandableAdapter? = null
    var listDataHeader: List<String>? = null
    var listDataChild: HashMap<String, List<String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us_new)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbarLayout.title = "About Us"
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        expandableListView = findViewById(R.id.expendableList)
        /*if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)
            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                if (lastExpandedPosition != -1
                    && groupPosition != lastExpandedPosition) {
                    expandableListView!!.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;

            }


        }*/
        prepareListData()
        listAdapter = CustomExpandableAdapter(this, listDataHeader as ArrayList<String>, listDataChild as HashMap<String, List<String>>)
        // setting list adapter
        expandableListView!!.setAdapter(listAdapter)
        expandableListView!!.setOnGroupExpandListener { groupPosition ->
            if (lastExpandedPosition != -1
                && groupPosition != lastExpandedPosition) {
                expandableListView!!.collapseGroup(lastExpandedPosition);
            }
            lastExpandedPosition = groupPosition;

        }
    }
    private fun prepareListData() {
        listDataHeader = ArrayList()
        listDataChild = HashMap()

        // Adding child data
        (listDataHeader as ArrayList<String>).add("WHO WE ARE")
        (listDataHeader as ArrayList<String>).add("ABOUT MISE")
        (listDataHeader as ArrayList<String>).add("OUR TEAM")
        (listDataHeader as ArrayList<String>).add("WHAT WE DO?")

        // Adding child data
        val whoWeAreMsg: MutableList<String> = ArrayList()
        whoWeAreMsg.add("Founded in the year 2009, the Minervaa Group aims at providing education to all and has been an active partner in many such charitable activities.We are a prominent name in the field of educational assessments and a one-stop solution for your HR-related needs. Besides partaking in charitable activities, the Group has spearheaded many new-age programs in the field of education to nurture young talents, make them future-ready, contribute to skilled Human Resources and prepare them to adapt the new normal easily.  The organization offers a plethora of opportunities that vary from the prestigious MISE and MockTest4You to Career Coupons Minervaa Honour, NEEST and my facility among several others. These programs are specially designed keeping in mind the changing job requirements and employment trends currently and in the upcoming years as well.")

        val aboutMiseMsg: MutableList<String> = ArrayList()
        aboutMiseMsg.add("MISE or MINERVAA International Scholarship Examination is one of the best and prestigious scholarship examinations for students from KG to Class XII. This scientifically standardized assessment evaluation, available both online &amp; offline, is meant for students who want to excel in different academic fields for a better career and future.This scholarship examination is conducted once every year where schools at national and international level participate to ensure that their students get a chance to compete with their peers in subjects such as English, Arts and Painting, Abacus, Mathematics, Computer Skills to name a few. MISE is now becoming available in Indian languages like Bengali.  By taking these exams, students get the chance to compete in School Level Olympiads which further provide them with a plethora of awards and prizes. This increases the skills and talents in the kids, and also enhances the excellent competitiveness in them as well. With the help of this scholarship examination, students have a better way of life. It is a one-of-a-kind examination which involves the teachers, parents, and students for the assessment of candidate in order to test their academic excellence.")


        val ourTeamMsg: MutableList<String> = ArrayList()
        ourTeamMsg.add("The MISE Group Team comprises of motivated and highly skilled professionals who will work together for the betterment of the students. The search for a proper Olympiad trainer and experts has led the students to this interesting and amazing platform where they can get amazing support and suggestions. Our team is made with thinkers and motivators who take the chance with innovation and strive to provide proper results.")

        val whatWeDoMsg: MutableList<String> = ArrayList()
        whatWeDoMsg.add("We bring ample opportunities for students aspiring to make a career in various fields of research. We strive to assist candidates in refining their academic excellence by conducting annual esteemed scholarship examinations within the major schools of India as well as overseas. It opens the windows for those who are willing to plunge into the world of academic talents to pursue their encoded employment goals with refined skills and approaches. The focused background of our team enables us to function smoothly. Instilling confidence in students and preparing them for future academic challenge is our guiding ethos.  We equip students with right advice and instructions, guiding them to make their own future interesting. With over 11 years of experience in this field, we provide with a great variety of assessment services – both offline and online assessment or scholarship test, career counseling online test, online scholarship exams, online Olympiad practice, and assessment test for school children. Through the amazing All India Talent Scholarship, the candidates have the chance to make sure that they are getting all the attention and help that they need. Students participate in these exams so that they can get awards and prizes which are going to be a further help in their education. The exam is a very transparent and functional Scholarship examination which enables the students to get some amazing options. Hence, in order to make sure that your children are getting a shot at the right education, choosing our services is the best for sure. Our team goes above and beyond to help students embark on a path to excellence by unlocking their potential and intellectual skills.")

        listDataChild!![(listDataHeader as ArrayList<String>).get(0)] = whoWeAreMsg // Header, Child data
        listDataChild!![(listDataHeader as ArrayList<String>).get(1)] = aboutMiseMsg
        listDataChild!![(listDataHeader as ArrayList<String>).get(2)] = ourTeamMsg
        listDataChild!![(listDataHeader as ArrayList<String>).get(3)] = whatWeDoMsg
    }
}