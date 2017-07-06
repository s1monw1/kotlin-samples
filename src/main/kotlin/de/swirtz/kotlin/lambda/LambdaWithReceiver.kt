package lambda

/**
 * Created by simonw on 29.06.17.
 */

var greet: String.() -> Unit = { println("Hello $this") }

fun main(args: Array<String>) {
    greet("Hadi")

    println(StringBuilder("Hello ")
            .apply {
                append("Kotliner")
                append("! ")
                append("How are you doing?")
            }.toString())

    val message = with(StringBuilder()) { append("This ").append("is a").append(" simple example").toString() }
    println(message)

    println(myHigherOrderFun { "The Number is $it" })
}

fun myHigherOrderFun(functionArg: (Int) -> String) = functionArg(5)
