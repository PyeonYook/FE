plugins {
    id("com.google.gms.google-services")
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
        // Java 8 이상의 새로운 API들을 이전 Android 버전에서도 사용할 수 있게 해주는 기능을 활성화
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("com.kizitonwose.calendar:core:2.4.0")
    // 원하는 경우 view 라이브러리도:
    implementation ("com.kizitonwose.calendar:view:2.4.0")
    implementation(platform("com.google.firebase:firebase-bom:33.14.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth")
    // Java 8 이상의 새로운 API들을 이전 Android 버전에서 사용할 수 있게 해주는 라이브러리
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // GridLayout 의존성 추가
    implementation("androidx.gridlayout:gridlayout:1.1.0")

    // cal
//    implementation("com.github.prolificinteractive:material-calendarview:2.0.1")
    // implementation("com.kizitonwose.calendar:view:2.7.0")
    implementation("com.kizitonwose.calendar:view:2.4.1")



    // Retrofit 라이브러리
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // JSON 데이터 처리를 위한 GSON 컨버터
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // (선택) OkHttp 로깅 인터셉터 - 네트워크 요청/응답 디버깅용
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
}