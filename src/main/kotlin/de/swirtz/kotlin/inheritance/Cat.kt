package de.swirtz.kotlin.inheritance

import org.slf4j.LoggerFactory

val LOG = LoggerFactory.getLogger(Cat::class.java.name)

/**
 * Created on 09.03.2017.
 * @author: simon-wirtz
 */
class Cat(val catName: String) : Animal(catName), ExampleInterface {

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
    override fun hashCode(): Int = catName.hashCode()

    override fun toString(): String = "Cat(catName='$catName')"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as Cat
        if (catName != other.catName) return false
        return true
    }
}

fun main(args: Array<String>) {
    with(Cat("cat")) {
        LOG.debug("Cat created: $this")
        LOG.debug("Cat abstractFunction: ${abstractFunction()}")
        LOG.debug("Cat openFunction: ${openFunction()}")
        LOG.debug("Cat notOpenFunction: ${notOpenFunction()}")
        LOG.debug("Cat interfaceFunction: ${interfaceFunction()}")
    }

}