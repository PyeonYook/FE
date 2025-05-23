plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.pyeonyook_fe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pyeonyook_fe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Retrofit 2.9.0 라이브러리 추가
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON 컨버터: JSON 데이터를 객체로 변환
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // (선택) OkHttp 로깅 인터셉터: 네트워크 요청/응답 데이터 로깅
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
}