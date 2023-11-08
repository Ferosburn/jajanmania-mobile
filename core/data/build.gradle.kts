plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.tokodizital.jajanmania.core.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    implementation(project(":core:domain"))

    implementation("androidx.core:core-ktx:1.12.0")


    implementation("com.google.protobuf:protobuf-javalite:3.25.0")
    implementation("androidx.datastore:datastore:1.1.0-alpha06")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.haroldadmin:NetworkResponseAdapter:5.0.0")

    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:21.7"
//    }
//
//    // Generates the java Protobuf-lite code for the Protobufs in this project. See
//    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
//    // for more information.
//    generateProtoTasks {
//        all().forEach { task ->
//            task.builtins {
//                java {
//                    option = "lite"
//                }
//            }
//        }
//    }
//}

protobuf {
    protoc {
        // find latest version number here:
        // https://mvnrepository.com/artifact/com.google.protobuf/protoc
        artifact = "com.google.protobuf:protoc:3.25.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins{
                create("java") {
                    option("lite")
                }
            }
        }
    }
}



