package com.example.aleksandr.tmbook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.aleksandr.tmbook.fragment.MyAccountFragment
import com.example.aleksandr.tmbook.fragment.PeopleFragment
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        replaseFragment(PeopleFragment())
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_people -> {
                    replaseFragment(PeopleFragment())
                    true
                }
                R.id.navigation_my_account -> {
                    replaseFragment(MyAccountFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaseFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit()

    }
}
