package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.lang.Thread.sleep

fun read() {
    println("into read")
    sleep(1000)
    println("fin read")
}

fun showScreen() {
    println("into show")

    sleep(1000)
    println("shown")

}

fun main(args: Array<String>) = runBlocking {
    val readVal = async { read() }
    val shown = async { showScreen() }
    readVal.await()
    shown.await()
}