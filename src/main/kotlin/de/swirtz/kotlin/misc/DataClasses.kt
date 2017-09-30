package de.swirtz.kotlin.misc

import org.slf4j.LoggerFactory

/**
 * Created on 06.03.2017.
 * @author: simon-wirtz
 */
//Declare new Data Class
data class Person(val age: Int, val name: String, val country: String = "Germany") {
    companion object {
        val LOG = LoggerFactory.getLogger(Person::class.java.name)
    }

    init {
        LOG.debug("Person created ${this}")
    }

    //Mutable property with custom accessor methods using a backing field
    var mutableProp: String = "initialValue"
        get() {
            println("mutableProp get() called")
            return field
        }
        set(value) {
            println("mutableProp set('$value') called")
            field = value
        }

}

//Method Syntax for MAIN
fun main(args: Array<String>) {
    //create new instance, toString equals etc. is overridden
    val person = Person(14, "Pete")
    Person.LOG.debug("Standard toString(): $person")
    // '==' means _structural equality_, whereas '===' means _referential equality_
    Person.LOG.debug("Two different Person instances are structurally equal? ${person == Person(14, "Pete")}")
    Person.LOG.debug("Two different Person instances are referentially equal? ${person === Person(14, "Pete")}")
    Person.LOG.debug("Two Persons from different countries are structurally equal? ${person == Person(14, "Pete", "Denmark")}")


    //Destructuring, using componentX functions
    val (age, name, city) = person
    Person.LOG.debug("$name, $age years of age, from $city")

    println("MutableProp: ${person.mutableProp}")
    person.mutableProp = "changed"
    println("MutableProp: ${person.mutableProp}")
}
