package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {
    val outer = launch {
        try {
            launch(coroutineContext) {
                try {
                    // work here
                    println("wait inner")
                    delay(5000)
                } catch (ex: CancellationException) {
                    println("In inner catch:CancellationException")
                }
                println("inner done")
            }
            println("wait outer")
            try {
                delay(400)
            } catch (ex: CancellationException) {
                println("In delay catch:CancellationException")
            }

        } catch (ex: CancellationException) {
            println("In outer catch:CancellationException")
        }

    }

    delay(300) // give it  a chance to run
    outer.cancelChildren()
    println(outer.isCancelled)
    println(outer.isCompleted)
    outer.join()
    println(outer.isCompleted)

}