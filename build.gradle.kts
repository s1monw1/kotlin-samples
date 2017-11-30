import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.2.0"
val kotlinxCoroutinesVversion = "0.19.2"

plugins {
    kotlin("jvm") version "1.2.0"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile(kotlin("stdlib-jre8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVversion")
    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.6")

    compile("org.slf4j:slf4j-api:1.7.14")
    compile("ch.qos.logback:logback-classic:1.1.3")

    testCompile(kotlin("test-junit", kotlinVersion))
    testCompile("junit:junit:4.11")
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url =uri("http://dl.bintray.com/kotlin/kotlinx.html/")
    }
}


