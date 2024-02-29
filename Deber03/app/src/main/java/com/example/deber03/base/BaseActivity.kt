package com.example.deber03.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    companion object {
        private const val overrideOrientation = true
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (overrideOrientation) {
            try {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}