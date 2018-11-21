package com.example.ikaru.footballapps.Main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.ikaru.footballapps.R
import com.example.ikaru.footballapps.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.savedInstanceState = savedInstanceState
        navigation.setOnNavigationItemSelectedListener {
                item -> when(item.itemId){
            navigation_match -> {
                message.setText(R.string.title_home)
                //supportActionBar?.hide()
                //openFragment(MatchesFragment())
                return@setOnNavigationItemSelectedListener true
            }
            navigation_team -> {
                message.setText(R.string.title_home)
                supportActionBar?.show()
                //openFragment(TeamsFragment())
                return@setOnNavigationItemSelectedListener true
            }
            navigation_favorite -> {
                message.setText(R.string.title_home)
                //supportActionBar?.hide()
                //openFragment(FavoritesFragment())
                return@setOnNavigationItemSelectedListener true
            }
            }
            false
        }

        navigation.selectedItemId = navigation_match
    }
}
