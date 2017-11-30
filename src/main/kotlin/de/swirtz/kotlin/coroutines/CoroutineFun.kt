package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId
import java.util.concurrent.atomic.AtomicInteger

val LOG = LoggerFactory.getLogger("CoroutineFun-Example")

/**
 * Created on 08.03.2017.
 * @author: simon-wirtz
 */
fun main(args: Array<String>) {
    fun getTime(): Instant {
        return Instant.now().atZone(ZoneId.systemDefault()).toInstant()
    }

    LOG.debug("${getTime()}: Start")

    // Start a coroutine
    launch(CommonPool) {
        delay(1000)
        LOG.debug("${getTime()}: Hello from coroutine")
    }

    runBlocking {
        delay(1500)
        LOG.debug("${getTime()}: Hello from second coroutine ")
    }
    LOG.debug("${getTime()}: Stop")

    val c = AtomicInteger()

//    for (i in 1..1_000_000)
//        thread(start = true) {
//            c.addAndGet(i)
//        }
//    for (i in 1..1_000_000)
//        launch(CommonPool) {
//            c.addAndGet(i)
//        }
    val deferred = (1..1_000_000).map { n ->
        async(CommonPool) {
            n
        }
    }
    runBlocking {
        val sum = deferred.sumBy { it.await() }
        println("Sum: $sum")
    }

}

