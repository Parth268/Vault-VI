package com.session.vault

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.list_action_bottomnav)
        navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment.navController
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(NavController(this), null)
    }

}