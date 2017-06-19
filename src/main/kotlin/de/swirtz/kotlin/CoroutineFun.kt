package de.swirtz.kotlin

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId

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
    LOG.debug("${getTime()}: Sleeping")
    Thread.sleep(2000) // wait for 2 seconds
    LOG.debug("${getTime()}: Stop")
}

