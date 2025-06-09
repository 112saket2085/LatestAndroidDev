plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "app"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.navigation.compose) // Original alias
                implementation(libs.androidx.room.runtime)     // Original alias
                implementation(libs.androidx.room.ktx)         // Original alias
                implementation(libs.kotlinx.coroutines.core) // Original alias
                implementation(libs.kotlinxDatetime) // Corrected alias
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlinTestCommon) // Corrected alias
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)       // Original alias
                implementation(libs.androidx.activity.compose) // Original alias
                implementation(libs.kotlinx.coroutines.android) // Original alias
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.kotlinTestJunit) // Corrected alias
                implementation(libs.junit)           // Corrected alias (was fine, but ensuring consistency)
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidxComposeUiTestJunit4) // Corrected alias
                implementation(libs.kotlinTestJunit)           // Corrected alias
                implementation(libs.junit)                     // Corrected alias
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.androidxSqliteDriver) // Corrected alias
            }
        }
        val desktopTest by getting {
            dependencies {
                implementation(libs.kotlinTestJunit)           // Corrected alias
                implementation(libs.junit)                     // Corrected alias
                implementation(libs.androidxComposeUiTestJunit4) // Corrected alias
            }
        }
    }
}

android {
    namespace = "com.expensetrackerapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.expensetrackerapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling) // Original alias
    }
}

dependencies {
    ksp(libs.androidx.room.compiler) // Original alias
}
