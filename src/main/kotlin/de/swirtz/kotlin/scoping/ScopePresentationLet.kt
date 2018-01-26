package de.swirtz.kotlin.scoping

//let1
fun getLength(text: String?): Int {
    val len = text?.let {
        println("get length of $it")
        it.length
    } ?: 0
    return len
}

//let2
fun confinedVariable(): String {
    val transform = "stringConfinedToLetScope".let {
        println("variable can be accessed in let: $it")
        "$it${it.length}"
    }
    //cannot access original string from here
    return transform
}

fun main(args: Array<String>) {
    println(confinedVariable())
}
