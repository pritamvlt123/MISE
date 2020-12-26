package com.example.mise.Activities

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mise.Fragments.ExamDetailScheduledFragment
import com.example.mise.Fragments.ExamDetailsInstantFragment

@Suppress("DEPRECATION")
internal class ExamDetailsAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ExamDetailsInstantFragment()
            }
            1 -> {
                ExamDetailScheduledFragment()
            }

            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
