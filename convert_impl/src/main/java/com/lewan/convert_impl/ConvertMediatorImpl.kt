package com.lewan.convert_impl

import androidx.fragment.app.FragmentManager
import com.lewan.convert_api.ConvertMediator
import com.lewan.convert_impl.ui.ConvertFragment
import javax.inject.Inject

class ConvertMediatorImpl @Inject constructor() : ConvertMediator {

    override fun openCreateRecipeFragment(containerId: Int, fragmentManager: FragmentManager) {

        val fragment = fragmentManager.findFragmentByTag(ConvertFragment::class.java.name)
            ?: ConvertFragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment, ConvertFragment::class.java.name)
            .addToBackStack(ConvertFragment::class.java.name)
            .commit()
    }
}