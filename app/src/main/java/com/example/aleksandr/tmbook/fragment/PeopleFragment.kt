package com.example.aleksandr.tmbook.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.aleksandr.tmbook.R
import com.google.firebase.firestore.ListenerRegistration

class PeopleFragment : Fragment() {

    private lateinit var userListenerRegistration: ListenerRegistration

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false)
    }


}