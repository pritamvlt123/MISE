package com.example.mise.Activities

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mise.Fragments.ProfileBasicInfoFragment
import com.example.mise.Fragments.ProfileMyActivityFragment
import com.example.mise.Fragments.ProfilePackageDetailsFragment

@Suppress("DEPRECATION")
internal class MyProfileAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProfileBasicInfoFragment()
            }
            1 -> {
                ProfilePackageDetailsFragment()
            }
            2 -> {
                ProfileMyActivityFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
