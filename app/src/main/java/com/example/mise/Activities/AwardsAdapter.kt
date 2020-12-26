package com.example.mise.Activities

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mise.Fragments.AwardScheduledFragment
import com.example.mise.Fragments.AwardsInstantFragment

@Suppress("DEPRECATION")
internal class AwardsAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AwardsInstantFragment()
            }
            1 -> {
                AwardScheduledFragment()
            }

            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
