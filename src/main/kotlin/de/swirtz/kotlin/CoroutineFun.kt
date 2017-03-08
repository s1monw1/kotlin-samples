package de.swirtz.kotlin

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.time.Instant
import java.time.ZoneId

/**
 * Created on 08.03.2017.
 * @author: simon-wirtz
 */
fun main(args: Array<String>) {
    println("${getTime()}: Start")

    // Start a coroutine
    launch(CommonPool) {
        delay(1000)
        println("${getTime()}: Hello from coroutine")
    }
    println("${getTime()}: Sleeping")
    Thread.sleep(2000) // wait for 2 seconds
    println("${getTime()}: Stop")
}

fun getTime(): Instant {
    return Instant.now().atZone(ZoneId.systemDefault()).toInstant()
}