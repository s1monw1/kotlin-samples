package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId

val LOG = LoggerFactory.getLogger("CoroutineFun-Example")

fun main(args: Array<String>) {
    fun getTime(): Instant {
        return Instant.now().atZone(ZoneId.systemDefault()).toInstant()
    }

    LOG.debug("${getTime()}: Start")

    // Start a coroutine
    GlobalScope.launch {
        delay(1000)
        LOG.debug("${getTime()}: Hello from coroutine")
    }

    runBlocking {
        delay(1500)
        LOG.debug("${getTime()}: Hello from second coroutine ")
    }
    LOG.debug("${getTime()}: Stop")

    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async { n }
    }

    runBlocking {
        val sum = deferred.sumBy { it.await() }
        println("Sum: $sum")
    }


}

