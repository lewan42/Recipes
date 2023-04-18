package com.lewan.main_api

import androidx.annotation.IdRes

interface MainContainer {

    @IdRes
    fun getMainContainerId(): Int
}