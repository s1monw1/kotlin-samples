package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.atomic.AtomicInteger


fun main(args: Array<String>) = runBlocking {
    val s = System.currentTimeMillis()
    val i = AtomicInteger()


//    val context = newSingleThreadContext("launch-SingleThread")
//    List(1_000_000) {
//        launch(context + CoroutineName("myroutine")) {
//            i.incrementAndGet()
//        }
//    }.forEach { it.join() }

    (1..1_000_000).forEach { i.incrementAndGet()}
    println("Value: $i, Duration: ${System.currentTimeMillis()-s}")
}