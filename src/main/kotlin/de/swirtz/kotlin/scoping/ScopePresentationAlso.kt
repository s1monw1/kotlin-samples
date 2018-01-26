package de.swirtz.kotlin.scoping

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
    println(BazBar { "123" }.getThatBaz())
}
