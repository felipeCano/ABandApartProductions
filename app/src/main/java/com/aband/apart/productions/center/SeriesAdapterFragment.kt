package com.aband.apart.productions.center

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aband.apart.productions.ui.fragments.SeriesFragment
private const val ARG_OBJECT = "object"
class SeriesAdapterFragment(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    lateinit var  mCategories: Array<String>
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> SeriesFragment()
            1 -> SeriesFragment()
            2 -> SeriesFragment()
            else ->
                return SeriesFragment()
        }
    }
    override fun getPageTitle(position: Int): CharSequence {
       // return mCategories[position]
        return ARG_OBJECT + (position + 1)
    }


}