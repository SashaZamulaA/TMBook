package com.example.aleksandr.tmbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.aleksandr.tmbook.util.FirestoreUtil
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.Item

class ChatActivity : AppCompatActivity() {

    private lateinit var messagesListenerRegistration: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(AppConstants.USER_NAME)

        val otherUserId = intent.getStringExtra(AppConstants.USER_ID)
        FirestoreUtil.getOrCreateChatChannel(otherUserId){ channelId ->
messagesListenerRegistration = FirestoreUtil.addChatMessagesListener(channelId, this, this::onMessagesChanged)
        }
    }

    private fun onMessagesChanged(messages :List<com.xwray.groupie.kotlinandroidextensions.Item>){

    }
}
