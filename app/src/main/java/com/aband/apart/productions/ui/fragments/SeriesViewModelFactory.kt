package com.aband.apart.productions.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aband.apart.productions.control.repository.SeriesRepository

class SeriesViewModelFactory (private val seriesRepository: SeriesRepository) :
 ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeriesViewModel(seriesRepository) as T
    }

}