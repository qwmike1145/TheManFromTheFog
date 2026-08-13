plugins {
    id("com.android.library")
}

android {
    namespace = "com.bzlzhh.ng_gl4es"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    lint {
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }

        create("fordebug") {
            initWith(getByName("debug"))
        }
    }

    libraryVariants.all {
        packageLibraryProvider.get().let {
            it.destinationDirectory.set(file("${rootDir}/FCL/libs"))
        }
    }

    externalNativeBuild {
        cmake {
            path = file("NG-GL4ES/CMakeLists.txt")
            version = "4.1.2"
        }
    }

    ndkVersion = "29.0.14206865"
}

tasks.register("buildNGG") {
    dependsOn("assembleRelease")
}
