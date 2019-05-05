package com.aband.apart.productions.center

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aband.apart.productions.ui.fragments.SeriesFragment

const val FAVORITE_TV_SHOW = "Favorite tv Show"
const val RECCOMENDATIONS_TV_SHOW = "Recommendations"
const val RATED_TV_SHOW = "Rated Tv Show"
class SeriesAdapterFragment(fm : FragmentManager/*, private val series : ArrayList<SerieLocal>*/) :
    FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 3 /*series.size * MAX_VALUE*/

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
        return when (position) {
            0 -> return FAVORITE_TV_SHOW
            1 -> return RECCOMENDATIONS_TV_SHOW
            2 -> return RATED_TV_SHOW
            else ->
                return FAVORITE_TV_SHOW

        }
    }
}