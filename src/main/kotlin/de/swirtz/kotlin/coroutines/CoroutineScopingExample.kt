package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    coroutineScope {
        val outerLaunch = launch {
            launch {
                while (true) {
                    delay(300)
                    println("Hello from first inner launch")
                }
            }
            launch {
                while (true) {
                    delay(300)
                    println("Hello from second inner launch")
                }
            }
        }

        println("Hello from runBlocking after outer launch")
        delay(800)
        outerLaunch.cancel()
    }
    println("finished coroutineScope")
}