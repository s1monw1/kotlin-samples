package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ActorJob
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 *
 * File created on 05.07.2017.
 */

// Message types for counterActor
sealed class CounterMsg

object IncCounter : CounterMsg() // one-way message to increment counter
class GetCounter(val response: SendChannel<Int>) : CounterMsg() // a request with reply

// This function launches a new counter actor
fun counterActor() = actor<CounterMsg>(CommonPool) {
    var counter = 0 // actor state
    for (msg in channel) { // iterate over incoming messages
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.send(counter)
        }
    }
}

suspend fun getCurrentCount(counter: ActorJob<CounterMsg>, response: Channel<Int>): Int {
    counter.send(GetCounter(response))
    val receive = response.receive()
    println("Counter = $receive")
    return receive
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val counter = counterActor() // create the actor
    val response = Channel<Int>()
    launch(CommonPool) {
            while(getCurrentCount(counter, response) < 100){
                delay(100)
                println("sending")
                counter.send(IncCounter)
            }
        }
    // jobs.forEach{it.join()}

    launch(CommonPool) {
        while ( getCurrentCount(counter, response) < 100) {
            delay(200)
        }
    }.join()
    println("Counter = ${response.receive()}")
    counter.close() // shutdown the actor
}
