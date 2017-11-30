package de.swirtz.kotlin.misc

/**
 * Created on 06.03.2017.
 * @author: simon-wirtz
 */
fun main(args: Array<String>) {
    //Declare most common collection structures, Kotlin has mutable and immutable collection types
//    val list = arrayListOf(1, 2, 3)
//
//
//    println("Size of readonly List: ${list.size}")
//    println("(First, Last) Element in List: (${list.first()},${list.last()})")
//    val set = hashSetOf("a", "b", "c", "d")
//    println("Size of Set: ${set.size}")
//    val map = hashMapOf(1 to "single", 2 to "many")
//    println("Size of Map: ${map.size}")
//
//    for ((i, s) in map){
//        println("Entry in map: $i: $s")
//    }

    val names = arrayOf("adam", "zack", "eva")
    val copy = names.copyOf()

    val sortedArray = copy.sortedArray()
    val alphabeticalOrder = names.contentEquals(sortedArray)
    println(if (alphabeticalOrder )"Strings are in alphabetical order." else "Strings are not in alphabetical order.")
}
