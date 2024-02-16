package com.example.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rickandmorty.R
import dagger.hilt.android.AndroidEntryPoint

private const val DELAY_MILLIS : Long = 2500

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(DELAY_MILLIS)
        installSplashScreen()

        setContentView(R.layout.activity_main)
    }
}