package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {
    val startTime = System.currentTimeMillis()
    val jobs = arrayListOf<Job>()
    jobs += GlobalScope.launch {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // check if still active
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Job1: Sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }

    //another job
    jobs += GlobalScope.launch {
        while (isActive) { // check if still active
            if (System.currentTimeMillis() >= 42) {
                println("Job2: Sleeping 42 ...")
                delay(500L)
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: Cancelling the sleeping job!")
    jobs.forEach { it.cancelAndJoin() } // cancels the job and waits for its completion
}

