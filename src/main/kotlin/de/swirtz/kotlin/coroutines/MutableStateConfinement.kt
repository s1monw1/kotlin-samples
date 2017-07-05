package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking
import java.time.Instant
import java.time.ZoneId

/**
 *
 * File created on 05.07.2017.
 */

fun main(args: Array<String>) {
    fun getTime(): Instant {
        return Instant.now().atZone(ZoneId.systemDefault()).toInstant()
    }

    val start = getTime()
    LOG.debug("$start: Start")

    var c = 0
    runBlocking (CoroutineName("blockingRoutine")){
        val singleThreadContext = newSingleThreadContext("mysinglethread")
        launch(singleThreadContext) {
            val jobs = List(1_000_000) {
                launch(context) {
                    c += 1
                }
            }
            jobs.forEach { it.join() }
        }.join()
    }
    val end = getTime()
    LOG.debug("$end: End")
    LOG.debug("Duration: ${end.epochSecond-start.epochSecond}")
    println(c)

}
