package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.*


fun main(args: Array<String>) {
    val context = newSingleThreadContext("launch-SingleThread")
    runBlocking {
        val s = System.currentTimeMillis()
        var i = 0

        List(1_000_000) {
            CoroutineScope(context).launch(CoroutineName("myroutine")) {
                i += 1
            }
        }.forEach { it.join() }

        println("Value: $i, Duration: ${System.currentTimeMillis() - s}")
    }
}