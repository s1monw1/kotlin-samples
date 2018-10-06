package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.time.Instant
import java.time.ZoneId
import java.util.concurrent.atomic.AtomicInteger

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

    val c = AtomicInteger()
    runBlocking(CoroutineName("blockingRoutine")) {
        GlobalScope.launch {
            val jobs = List(1_000_000) {
                launch {
                    c.incrementAndGet()
                }
            }
            jobs.forEach { it.join() }
        }.join()
    }
    val end = getTime()
    LOG.debug("$end: End")
    LOG.debug("Duration: ${end.epochSecond - start.epochSecond}")
    println(c)

}
