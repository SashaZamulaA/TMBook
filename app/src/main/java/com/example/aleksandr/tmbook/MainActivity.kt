package com.example.aleksandr.tmbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.navigation_people ->{
                    true
                }
                R.id.navigation_my_account -> {
                    true
                }
                else -> false
            }
        }
    }
}
