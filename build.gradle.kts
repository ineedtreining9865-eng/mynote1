buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath(kotlin("gradle-plugin", version = "1.9.22"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
