package de.swirtz.kotlin.scoping

import java.util.*

fun initWith() {
    val s: String = with(StringBuilder("init")) {
        append("some").append("thing")
        println("current value: $this")
        toString()
    }
}

object Foo {
    fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) + start
}


fun main(args: Array<String>) {
    val s = createString {
        append(4)
        append("hello")
    }

}

fun createString(block: StringBuilder.() -> Unit): String {
    val sb = StringBuilder().block()
    return sb.toString()
}