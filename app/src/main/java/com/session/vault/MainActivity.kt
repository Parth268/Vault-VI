package com.session.vault

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.session.vault.setting.SettingFragment
import com.session.vault.storeImage.ImageAlbumFragment
import com.session.vault.storeVideo.VideoFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(navView, navHostFragment.navController)
        }

        navView.setOnNavigationItemSelectedListener {
                item -> // By using switch we can easily get
            // the selected fragment
            // by using there id.
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.image_icon -> selectedFragment = ImageAlbumFragment()
                R.id.video_icon -> selectedFragment = VideoFragment()
                R.id.setting_icon -> selectedFragment = SettingFragment()
            }
            // It will help to replace the
            // one fragment to other.
            selectedFragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, it)
                    .commit()
            }
            true
        }

    }
}