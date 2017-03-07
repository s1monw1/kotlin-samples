package de.swirtz.kotlin

/**
 * Created on 06.03.2017.
 * @author: simon-wirtz
 */
//Declare new Data Class
data class Person(val age: Int, val name: String, val country: String = "Germany")

//Method Syntax for MAIN
fun main(args: Array<String>) {
    //create new instance, toString equals etc. is overridden
    val person = Person(14, "Pete")
    println("Standard toString(): $person")
    // '==' means _structural equality_, whereas '===' means _referential equality_
    println("Two different Person instances are structurally equal? ${person == Person(14, "Pete")}")
    println("Two different Person instances are referentially equal? ${person === Person(14, "Pete")}")

    println("Two Persons from different countries are structurally equal? ${person == Person(14, "Pete", "Denmark")}")

    //Destructuring, using componentX functions
    val (age, name, city) = person
    println("$name, $age years of age, from $city")
}
