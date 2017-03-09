package de.swirtz.kotlin.inheritance

/**
 * Created on 09.03.2017.
 * @author: simon-wirtz
 */
interface ExampleInterface {

    /**
     * As in Java8 interfaces can provide default implementations
     */
    fun openFunction() = "openFunction ExampleInterface"

    /**
     * This function is implicitly abstract
     */
    fun interfaceFunction():String
}