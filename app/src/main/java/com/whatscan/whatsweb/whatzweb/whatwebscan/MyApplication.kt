package com.whatscan.whatsweb.whatzweb.whatwebscan


import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.whatscan.whatsweb.whatzweb.whatwebscan.BuildConfig
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        var app: MyApplication? = null

        fun getInstance(): MyApplication? {
            return app
        }
    }


    override fun onCreate() {
        super.onCreate()

        app = this@MyApplication




        val testDeviceIds = listOf("")
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        MobileAds.setRequestConfiguration(configuration)

        MobileAds.initialize(
            this
        ) { }
    }

}