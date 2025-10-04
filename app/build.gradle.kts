import java.util.Properties
import java.io.FileInputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.ZoneId

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val beijingTime = LocalDate.now(ZoneId.of("Asia/Shanghai"))
val buildDate = beijingTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

android {
    namespace = "io.github.nwmafywf.clickcounter"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.nwmafywf.clickcounter"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.0-rc.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    applicationVariants.all {
        outputs.map { output ->
            if (output is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                val appName = "ClickCounter"
                val version = versionName
                val abi = output.getFilter(com.android.build.OutputFile.ABI) ?: "universal"
                output.outputFileName = "${version}-${buildDate}-${appName}-${abi}.apk"
            }
        }
    }

    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val keystoreProperties = Properties().apply {
        if (keystorePropertiesFile.exists()) {
            load(FileInputStream(keystorePropertiesFile))
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as? String ?: "upload"
            keyPassword = keystoreProperties["keyPassword"] as? String ?: ""
            storeFile = file(keystoreProperties["storeFile"] as? String ?: "not-set")
            storePassword = keystoreProperties["storePassword"] as? String ?: ""
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("arm64-v8a", "armeabi-v7a")
            isUniversalApk = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // 添加 DataStore 依赖
    implementation(libs.androidx.datastore.preferences)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}