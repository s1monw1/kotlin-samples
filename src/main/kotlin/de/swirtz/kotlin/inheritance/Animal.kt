package de.swirtz.kotlin.inheritance

/**
 * Created on 09.03.2017.
 * @author: simon-wirtz
 */
abstract class Animal(val name: String) {
    fun notOpenFunction() = "I'm not an open function and cannot be overridden"
    abstract fun abstractFunction(): String // MUST be overriden
    open fun openFunction(): String = "I'm an open function and I can be overriden"
}