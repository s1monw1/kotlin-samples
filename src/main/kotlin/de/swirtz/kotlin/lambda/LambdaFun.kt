package de.swirtz.kotlin.lambda

/**
 * Created on 06.03.2017.
 * @author: simon-wirtz
 */
fun main(args: Array<String>) {
//    val list = (0..100)
//    val sub = list.filter { it % 2 == 0 && it <= 50 }
//    println("Even numbers from 0 to 50 (${sub.size} Results): \n$sub")
//    //Using lambda as argument
//    val sub2 = applyToList(list.toList(), { i -> i > 42 })
//    println("Numbers greater 42 with lambda example (${sub2.size} Results): \n$sub2")
//
//
//    fun foo(i: Int): Boolean = i > 3
//
//    applyToList(list.toList(), ::foo)
//
//    //simple Lambda expression
//    val sum = { x: Int, y: Int -> x + y }
//    println("3 + 10 = ${sum.invoke(3, 10)}")
//    println("3 + 10 = ${sum(3, 10)}")
//
//    //Lambda accessing its closure (variable in outer scope)
//    var completeSum = 0
//    list.forEach {
//        completeSum += it
//    }
//    print(completeSum)


}

fun foo(str: String) = str.takeIf { it.isNotBlank() } ?: "empty"

//Defining function with lambda as parameter
fun <T> applyToList(list: List<T>, func: (T) -> Boolean) = list.filter { it -> func(it) }
