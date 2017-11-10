package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * File created on 02.11.2017.
 */

fun main(args: Array<String>) = runBlocking {
    val jobs = arrayListOf<Job>()
    jobs += launch(coroutineContext) {
        // context of the parent, runBlocking coroutine
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("'coroutineContext': After delay in thread ${Thread.currentThread().name}")
    }
    (0..100).forEach { println(System.currentTimeMillis()) }
    jobs.forEach { it.join() }
}