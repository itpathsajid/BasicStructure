package com.kotlin.basicstructure.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.basicstructure.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(i)

                // close this activity
                finish()
            },0
        ) // wait for 2.5 seconds

    }
}