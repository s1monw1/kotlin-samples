import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion
val kotlinxCoroutinesVersion = "0.21.2"

plugins {
    kotlin("jvm") version "1.2.21"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile("io.reactivex.rxjava2:rxkotlin:2.2.0")
    compile(kotlin("stdlib-jre8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.6")
    compile("com.google.code.gson:gson:2.8.2")
    compile("org.slf4j:slf4j-api:1.7.14")
    compile("ch.qos.logback:logback-classic:1.1.3")
    compile("com.squareup.okhttp3:okhttp:3.9.1")
    testCompile(kotlin("test-junit", kotlinVersion))
    testCompile("junit:junit:4.11")
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        setUrl("http://dl.bintray.com/kotlin/kotlinx.html/")
    }
}


