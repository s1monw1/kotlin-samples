package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun sampleSuspendFun(x: Int): Int {
    delay(2000)
    return x * x
}


fun main(args: Array<String>) {
    GlobalScope.launch {
        sampleSuspendFun(5)
        print("Hello!")
    }

}


