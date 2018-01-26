package de.swirtz.kotlin.scoping

import java.util.*

//run1
fun getLengthWithRun(text: String?): Int {
    return text?.run {
        println("get length of $this")
        length //this can be omitted
    } ?: 0
}

//run2
fun run2() {
    val date: Int = Calendar.getInstance().run {
        set(Calendar.YEAR, 2030)
        get(Calendar.DAY_OF_YEAR)
    }

}

fun main(args: Array<String>) {
    println(confinedVariable())
}
