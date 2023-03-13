package com.lewan.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lewan.core_api.mediator.AppWithFacade
import com.lewan.core_api.mediator.SearchMediator
import com.lewan.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var searchMediator: SearchMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.mainNavigationView).setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    searchMediator.startSearchScreen(
                        containerId = R.id.mainFragmentsContainer,
                        fragmentManager = supportFragmentManager
                    )
                    true
                }

                R.id.navigation_create_recipes -> {
                    true
                }

                else -> false
            }
        }
    }
}