package com.lewan.main_impl

import com.lewan.main_api.MainContainer
import javax.inject.Inject

class MainContainerImpl @Inject constructor() : MainContainer {

    override fun getMainContainerId(): Int = R.id.main_fragments_container
}