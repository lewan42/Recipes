package com.lewan.main_impl

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lewan.convert_api.ConvertMediator
import com.lewan.core_di_api.AppWithFacade
import com.lewan.main_impl.di.MainComponent
import com.lewan.search_api.SearchRecipeMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var searchRecipeMediator: SearchRecipeMediator

    @Inject
    lateinit var convertMediator: ConvertMediator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.main_navigation_view)

        navigation.menu.clear()
        navigation.inflateMenu(R.menu.bottom_nav_menu)
        val tintStates = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled),
            intArrayOf()
        )
        val tintColors = intArrayOf(
            getColor(R.color.selected),
            getColor(R.color.unselected),
            getColor(R.color.unselected),
        )
        val colorStateList = ColorStateList(tintStates, tintColors)
        navigation.itemIconTintList = colorStateList
        navigation.itemTextColor = colorStateList

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    searchRecipeMediator.startSearchFragment(
                        R.id.main_fragments_container,
                        supportFragmentManager
                    )
                    true
                }

                R.id.navigation_create_recipes -> {
                    convertMediator.openCreateRecipeFragment(
                        R.id.main_fragments_container,
                        supportFragmentManager
                    )
                    true
                }

                else -> false
            }
        }

        searchRecipeMediator.startSearchFragment(
            R.id.main_fragments_container,
            supportFragmentManager
        )
    }
}