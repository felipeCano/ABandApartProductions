package com.aband.apart.productions.center

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragmentLayout(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFinishedViewLoad()
    }

    @LayoutRes
    abstract fun fragmentLayout(): Int

    abstract fun onFinishedViewLoad()

    protected fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }
}