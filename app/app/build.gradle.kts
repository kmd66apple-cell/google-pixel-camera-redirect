plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.google.android.apps.photos"
    compileSdk = 34

    // KEYSTORE_PATH がある場合のみリリース署名を作成
    val releaseSigning = System.getenv("KEYSTORE_PATH")?.takeIf(String::isNotEmpty)?.let { path ->
        signingConfigs.create("release") {
            val password = System.getenv("KEYSTORE_PASSWORD")
            storeFile = file(path)
            storePassword = password
            keyAlias = "immich-redirect"
            keyPassword = password
        }
    }

    defaultConfig {
        applicationId = "com.google.android.apps.photos"
        minSdk = 26
        targetSdk = 34
        versionCode = providers.gradleProperty("versionCode").getOrElse("1").toInt()
        versionName = providers.gradleProperty("versionName").getOrElse("1.0")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = releaseSigning ?: signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // HostPhotoPagerActivity.kt (AppCompatActivity) に必要な基礎ライブラリ
    implementation("androidx.appcompat:appcompat:1.6.1")
}
