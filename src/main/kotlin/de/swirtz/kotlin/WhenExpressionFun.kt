package de.swirtz.kotlin

/**
 * Created on 29.05.2017.
 * @author: simon-wirtz
 */

enum class Colour {
    RED, BLUE, YELLOW, BLACK, ORANGE, GREEN
}

fun mixColours(col1: Colour, col2: Colour): Colour =
        when (setOf(col1, col2)) {
            setOf(Colour.RED, Colour.YELLOW) -> Colour.ORANGE
            setOf(Colour.BLUE, Colour.YELLOW) -> Colour.GREEN
            else -> throw IllegalArgumentException("Colours $col1 & $col2 cannot be mixed!")
        }

fun main(args: Array<String>) {
    println(mixColours(Colour.BLUE, Colour.YELLOW))
    println(mixColours(Colour.YELLOW, Colour.BLUE))
    println(mixColours(Colour.RED, Colour.YELLOW))
    println(mixColours(Colour.YELLOW, Colour.RED))
}