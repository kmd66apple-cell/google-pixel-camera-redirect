package com.google.android.apps.photos.pager

import android.app.KeyguardManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class HostPhotoPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyguard = getSystemService(KeyguardManager::class.java)
        if (keyguard != null && keyguard.isKeyguardLocked) {
            keyguard.requestDismissKeyguard(this, object : KeyguardManager.KeyguardDismissCallback() {
                override fun onDismissSucceeded() {
                    forward()
                }
                override fun onDismissCancelled() {
                    finish()
                }
                override fun onDismissError() {
                    finish()
                }
            })
        } else {
            forward()
        }
    }

    private fun forward() {
        val uri = intent.data
        try {
            startActivity(
                Intent(Intent.ACTION_VIEW)
                    .setDataAndType(uri, intent.type ?: uri?.let(contentResolver::getType))
                    .setPackage(REVANCED_PACKAGE)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Google Photos ReVanced", e)
        } finally {
            finish()
        }
    }

    companion object {
        private const val TAG = "HostPhotoPagerActivity"
        // Google Photos ReVanced のパッケージ名を指定
        private const val REVANCED_PACKAGE = "com.google.android.apps.photos.revanced"
    }
}
