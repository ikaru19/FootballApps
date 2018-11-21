package com.example.ikaru.footballapps.Main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.ikaru.footballapps.Match.MatchFragment
import com.example.ikaru.footballapps.R
import com.example.ikaru.footballapps.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.savedInstanceState = savedInstanceState
        navigation.setOnNavigationItemSelectedListener {
                item -> when(item.itemId){
                    navigation_match -> {

                        supportActionBar?.hide()
                        createFragment(MatchFragment())
                        return@setOnNavigationItemSelectedListener true
                    }
                    navigation_team -> {
                        supportActionBar?.show()
                        //openFragment(TeamsFragment())
                        return@setOnNavigationItemSelectedListener true
                    }
                    navigation_favorite -> {
                        supportActionBar?.hide()
                        //openFragment(FavoritesFragment())
                        return@setOnNavigationItemSelectedListener true
                    }
                }
            false
        }
        navigation.selectedItemId = navigation_match
    }

    private fun createFragment(fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
}
