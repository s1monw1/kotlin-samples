package de.swirtz.kotlin.scoping

import kotlinx.coroutines.experimental.channels.*
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

//run1
fun notUsingReceiver(text: String?): Int {
    return 1234.also { println("the function did its job!") }
}

class Bar(var foo: String? = null)
class BazBar(private var bazInit: () -> String) {
    private lateinit var baz: String

    fun getThatBaz() = bazInit().also { baz = it }
}

//run2
fun initAlsoAssign() {
    val bar: Bar = Bar().also {
        it.foo = "another value"
    }

}

fun main(args: Array<String>) {


    fun produceNumbers()= produce<Int>(capacity = 99) {
        var count = 0
        for (i in 1..100) {
            send(i)
            count++
        }
        println("Sent: $count numbers")
    }

    val consumer = actor<Int>(capacity = Channel.UNLIMITED) {
        var sum = 0
        var count = 0
        for (i in channel) {
            sum += i
            count++
        }
        println("Received: $count numbers")
        println("Sum: $sum")
    }

    runBlocking {
        val c = produceNumbers()
        c.toChannel(consumer).close()
    }

}
