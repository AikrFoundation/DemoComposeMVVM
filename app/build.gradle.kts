plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.hilt)
}

android {
  namespace = "aikr.demo"
  compileSdk = 35

  defaultConfig {
    applicationId = "aikr.demo"
    minSdk = 21
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    release {
      isMinifyEnabled = true
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

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

composeCompiler {
  enableStrongSkippingMode = true
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))

  implementation(libs.bundles.androidxCompose)
  implementation(libs.bundles.core)

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
