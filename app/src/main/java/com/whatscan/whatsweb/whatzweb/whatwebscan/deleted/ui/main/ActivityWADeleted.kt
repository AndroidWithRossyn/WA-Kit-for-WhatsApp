package com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.ui.intro.MyAppIntro
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.utility.isFinishedIntro
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.utility.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityWADeleted : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val isFinished = isFinishedIntro()
        if (isFinished) startActivity(DeletedMainActivity::class.java)
        else startActivity(MyAppIntro::class.java)
    }
}