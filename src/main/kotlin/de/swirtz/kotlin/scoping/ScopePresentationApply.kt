package de.swirtz.kotlin.scoping

fun initApply() {
    val bar: Bar = Bar().apply {
        foo = "another value"
    }
}

class FooBar(var a: Int = 0, var b: String? = null) {
    fun first(aArg: Int) = apply { a = aArg }
    fun second(bArg: String) = apply { b = bArg }
}


fun main(args: Array<String>) {
    FooBar().first(10).second("foobarValue")
}

