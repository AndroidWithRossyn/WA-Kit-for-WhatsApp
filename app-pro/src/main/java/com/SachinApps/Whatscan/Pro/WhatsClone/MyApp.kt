package com.SachinApps.Whatscan.Pro.WhatsClone


import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp: Application() {

    companion object{
        var app: MyApp? = null

        fun getInstance(): MyApp? {
            return app
        }
    }


    override fun onCreate() {
        super.onCreate()

        app = this@MyApp




        val testDeviceIds = listOf("")
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        MobileAds.setRequestConfiguration(configuration)

        MobileAds.initialize(
            this
        ) { }
    }

}