package com.google.android.apps.photos

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 元のインテントを取得し、転送先（ReVanced）のパッケージ名を設定
        val redirectIntent = Intent(intent).apply {
            setPackage("com.google.android.apps.photos.revanced") // ReVancedの実際のパッケージ名
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
