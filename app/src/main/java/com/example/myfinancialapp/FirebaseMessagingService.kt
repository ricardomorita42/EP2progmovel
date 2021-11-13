package com.example.myfinancialapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


//https://www.youtube.com/watch?v=kyOQkCE6Ieo
class FirebaseMessagingService : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.e("Message", "Message received")
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("Token", "New Token")
    }
}