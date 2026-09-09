package com.google.android.apps.photos.pager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class HostPhotoPagerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val intent = packageManager.getLaunchIntentForPackage("app.revanced.android.photos")
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                Log.e(TAG, "Immich app not found")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Immich app", e)
        } finally {
            finish()
        }
    }

    companion object {
        private const val TAG = "HostPhotoPagerActivity"
    }
}
