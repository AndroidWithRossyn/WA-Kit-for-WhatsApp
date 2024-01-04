package com.SachinApps.Whatscan.Pro.WhatsClone.deleted.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.ui.intro.MyAppIntro
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.utility.isFinishedIntro
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.utility.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WADeletedActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val isFinished = isFinishedIntro()
        if (isFinished) startActivity(DeletedMainActivity::class.java)
        else startActivity(MyAppIntro::class.java)
    }
}