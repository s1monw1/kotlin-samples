package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.ZoneId

/**
 *
 * File created on 05.07.2017.
 */

fun main(args: Array<String>) {
    fun getTime(): Instant = Instant.now().atZone(ZoneId.systemDefault()).toInstant()
    val start = getTime().also { LOG.debug("$it: Start") }

    var count = 0
    runBlocking(CoroutineName("blockingRoutine")) {
        launch(newSingleThreadContext("mysinglethread")) {
            val jobs = List(1_000_000) {
                launch(coroutineContext) {
                    count += 1
                }
            }
            jobs.forEach { it.join() }
        }
    }
    val end = getTime().also { LOG.debug("$it: End") }
    LOG.debug("Duration: ${end.epochSecond - start.epochSecond} s")
    println("Counter: $count")

}
