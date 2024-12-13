package com.example.shop

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}