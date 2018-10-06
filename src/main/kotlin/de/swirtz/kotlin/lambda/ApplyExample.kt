package de.swirtz.kotlin.lambda

class GuiContainer {
    var width: Int = 0
    var height: Int = 0
    var background: String = "red"
    fun printMe() = println("I'm a container: $this")
}


fun main(args: Array<String>) {
    val container = GuiContainer().apply {
        width = 10
        height = 20
        background = "blueish"
        printMe()
    }

    val nullable: String? = "anything"
    nullable?.let {
        println(it)
    }

}

class AlsoTester {
    var state: Int = 0

    fun next(): Int {
        //do some crazy stuff
        return 1.also { state = it }
    }
}