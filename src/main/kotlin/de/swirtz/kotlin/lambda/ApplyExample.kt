package de.swirtz.kotlin.lambda

data class GuiContainer(var width: Int = 0, var height: Int = 0, var background: String = "red") {
    fun printMe() = println(this)

}

fun main(args: Array<String>) {
    val container = GuiContainer().apply {
        width = 10
        height = 20
        background = "blueish"
        printMe()
    }

}