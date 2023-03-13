package com.lewan.search

import android.content.Context
import android.util.Log
import com.lewan.core.ViewModelFragment
import com.lewan.core_api.mediator.AppWithFacade
import com.lewan.search.databinding.FragmentSearchBinding
import com.lewan.search.di.SearchComponent

class SearchFragment : ViewModelFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by activityViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SearchComponent.create((context.applicationContext as AppWithFacade).getFacade()).inject(this)
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", viewModel.test())
    }
}