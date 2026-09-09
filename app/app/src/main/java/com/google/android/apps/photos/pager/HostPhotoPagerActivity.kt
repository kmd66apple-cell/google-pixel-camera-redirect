package com.google.android.apps.photos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HostPhotoPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val redirectIntent = Intent(intent).apply {
            setPackage("com.google.android.apps.photos.revanced")
            component = null
        }

        try {
            startActivity(redirectIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        finish()
    }
}
