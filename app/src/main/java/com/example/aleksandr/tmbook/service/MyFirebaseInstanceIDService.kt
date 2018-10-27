package com.example.aleksandr.tmbook.service

import android.graphics.RegionIterator
import android.media.session.MediaSession
import com.example.aleksandr.tmbook.util.FirestoreUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import kotlinx.coroutines.experimental.Job

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
           val newRegistrationToken = FirebaseInstanceId.getInstance().token

        if(FirebaseAuth.getInstance().currentUser!=null ){
            addTokenToFirestore(newRegistrationToken)
        }
        }
    companion object {
        fun addTokenToFirestore(newRegistrationToken: String?){
            if (newRegistrationToken==null) throw NullPointerException("FCM token is null")
            FirestoreUtil.getFCMRegistrationToken { tokens ->
                if (tokens.contains(newRegistrationToken)){
                    return@getFCMRegistrationToken

                    tokens.add(newRegistrationToken)
                    FirestoreUtil.setFCMRegistrationToken(tokens)
                }

            }

    }
    }
}