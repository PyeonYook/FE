// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

// 버전 에러 제거용
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:none")
}