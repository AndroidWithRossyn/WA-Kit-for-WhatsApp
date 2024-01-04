package com.SachinApps.Whatscan.Pro.WhatsClone.deleted.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.SachinApps.Whatscan.Pro.WhatsClone.R
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.ActivityMainBinding
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.services.NLService
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeletedMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpMain()
        navigateToChatDetail()
    }

    private fun setUpMain() {
        //Start the service
        val intent = Intent(applicationContext, NLService::class.java)
        startService(intent)

        binding.toolbar.mToolBarThumb.setOnClickListener { v -> onBackPressed() }
        binding.toolbar.mToolBarText.text = "WA Deleted"


        //set up fragment Navigation
        navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        setupActionBarWithNavController(navHost.navController)
    }

    private fun navigateToChatDetail() {
        val notiDeleted = intent.getBooleanExtra("notificationDeleted", false)
        val user = intent.getStringExtra("user")
        val app = intent.getStringExtra("app")
        if (notiDeleted) navHost.findNavController()
            .navigate(R.id.chatDetailFragment, bundleOf("user" to user, "app" to app))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}