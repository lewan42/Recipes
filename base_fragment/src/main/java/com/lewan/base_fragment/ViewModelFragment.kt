package com.lewan.base_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class ViewModelFragment<VB : ViewBinding>(
    bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : ViewBindingFragment<VB>(bindingInflater) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}
