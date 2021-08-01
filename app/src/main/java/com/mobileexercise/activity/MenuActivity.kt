package com.mobileexercise.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobileexercise.R
import com.mobileexercise.api.KEY_LAUNCH
import kotlinx.android.synthetic.main.activity_splash.*

class MenuActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context,launchBoolean: Boolean) {
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(KEY_LAUNCH, launchBoolean)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView() {
        ivBack.visibility = View.VISIBLE
        ivBack.setOnClickListener { DashboardActivity.launch(this) }
        if (intent.getBooleanExtra(KEY_LAUNCH,true)){
            ivLogo.background = getDrawable(R.drawable.ic_google)
        }else{
            ivLogo.background = getDrawable(R.drawable.ic_apple)
        }
    }
}