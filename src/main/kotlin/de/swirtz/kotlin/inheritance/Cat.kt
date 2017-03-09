package de.swirtz.kotlin.inheritance

import org.slf4j.LoggerFactory

/**
 * Created on 09.03.2017.
 * @author: simon-wirtz
 */
class Cat(val catName: String) : Animal(catName), ExampleInterface {
    companion object {
        val LOG = LoggerFactory.getLogger(Cat::class.java.name)
    }

    override fun abstractFunction(): String = "Meow"

    override fun openFunction(): String {
        LOG.debug("Overriding open function")
        //openFunction is known in both: ExampleInterface and Animal
        LOG.debug("open function of Animal: ${super<Animal>.openFunction()}")
        LOG.debug("open function of ExampleInterface: ${super<ExampleInterface>.openFunction()}")
        return "Overriding open function finished"
    }
    //not working because method is final: override fun notOpenFunction() = "trying to override"

    override fun interfaceFunction() = "Override of interface function"

    //Override of Any-methods
    override fun hashCode(): Int {
        return catName.hashCode()
    }

    override fun toString(): String {
        return "Cat(catName='$catName')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as Cat
        if (catName != other.catName) return false
        return true
    }



}

fun main(args: Array<String>) {
    val a = Cat("cat")
    Cat.LOG.debug("Cat created: $a")
    Cat.LOG.debug("Cat abstractFunction: ${a.abstractFunction()}")
    Cat.LOG.debug("Cat openFunction: ${a.openFunction()}")
    Cat.LOG.debug("Cat notOpenFunction: ${a.notOpenFunction()}")
    Cat.LOG.debug("Cat interfaceFunction: ${a.interfaceFunction()}")
}