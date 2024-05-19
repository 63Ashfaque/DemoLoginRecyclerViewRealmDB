// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.15.1")
    }
}
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
   // id("io.realm.kotlin") version "10.8.0" apply true
//    id("io.realm.kotlin") version "10.6.0" apply false
}

//buildscript {
//    dependencies {
//       // classpath("io.realm:realm-gradle-plugin:10.8.0")
//       classpath("io.realm:realm-gradle-plugin:10.6.0")
//   //    classpath("io.realm:realm-gradle-plugin:10.4.0")
//  //     classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
//    }
//}

