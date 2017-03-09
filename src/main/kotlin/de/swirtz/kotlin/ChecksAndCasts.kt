package de.swirtz.kotlin

/**
 * Created on 06.03.2017.
 * @author: simon-wirtz
 */
fun main(args: Array<String>) {
    checkType("StringX")
    checkTypeWithWhen(42)

    val x = "AnotherString"
    // x is automatically cast to string on the right-hand side of `&&`
    if (x is String && x.isNotEmpty()) {
        print("Automatic cast example result: ${x.length}") // x is automatically cast to String
    }

    val anyString: Any = "bla"
    //unsafe cast, may throw Exception
    val unsafeCastToString: String = anyString as String
    //safe cast, may result in null
    val safeCastToString: String? = anyString as? String
}

/**
 * check type at runtime with is and !is
 * FYI: Any in Kotlin ~ Object in Java
 */
fun checkType(inst: Any) {
    if (inst is String) {
        println("String value: $inst")
        //Automatic cast by compile after is-check
        println("String length: ${inst.length}")
    } else if (inst is Int) {
        println("Int value: $inst")
        //Automatic cast by compile after is-check
        println("Int multiplied with 10: ${inst * 10}")
    } else println("Any value: $inst")
}

//check type at runtime with is and !is
fun checkTypeWithWhen(inst: Any) {
    when (inst) {
        is String -> {
            println("String value: $inst")
            //Automatic cast by compile after is-check
            println("String length: ${inst.length}")
        }
        is Int -> {
            println("Int value: $inst")
            //Automatic cast by compile after is-check
            println("Int multiplied with 10: ${inst * 10}")
        }
        else -> println("Any value: $inst")
    }
}