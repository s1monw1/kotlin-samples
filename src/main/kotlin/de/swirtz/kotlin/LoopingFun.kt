package de.swirtz.kotlin

/**
 * Created by simonw on 16.06.17.
 */


fun main(args: Array<String>) {
    for (i in 0..5) {
        println("Current: $i")
    }

    for (i in 0..10 step 2) {
        println("Current: $i")
    }

    (0..10 step 2).forEach { println("Current: $it") }

    val range = 3 downTo 1
    println(range::class)
    for (withIndex in range.withIndex()) {
        println("Ranged [${withIndex.index}] = ${withIndex.value}")
    }
}