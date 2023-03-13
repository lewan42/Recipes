package com.lewan.search

import androidx.fragment.app.FragmentManager
import com.lewan.core_api.mediator.SearchMediator
import javax.inject.Inject

class SearchMediatorImpl @Inject constructor() : SearchMediator {

    override fun startSearchScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().add(containerId, SearchFragment.newInstance()).commit()
    }
}