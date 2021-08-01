package com.mobileexercise.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobileexercise.R
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_navigation.ivHome
import kotlinx.android.synthetic.main.activity_splash.*

class NavigateMenuActivity: AppCompatActivity() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, NavigateMenuActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        initView()
    }

    private fun initView() {
        ivHome.setOnClickListener { DashboardActivity.launch(this)}
        tvMyAccount.setOnClickListener { startMenuActivity(true)}
        tvSettings.setOnClickListener { startMenuActivity(false) }
        tvLogout.setOnClickListener { LoginActivity.launch(this)}
    }

    private fun startMenuActivity(launchBoolean: Boolean) {
        MenuActivity.launch(this,launchBoolean)
    }
}