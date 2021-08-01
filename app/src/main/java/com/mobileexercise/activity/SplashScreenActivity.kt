package com.mobileexercise.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobileexercise.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreenActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Suppress("DEPRECATION")
    private fun initView() {
        ivBack.visibility = View.GONE
        ivLogo.background = getDrawable(R.drawable.ic_logo)
        Handler().postDelayed({
            LoginActivity.launch(this)
            finish()
        }, 3000)
    }
}