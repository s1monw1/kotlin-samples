package de.swirtz.kotlin.misc

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
    fun <T> T.print() = println(this)

    mixColours(Colour.BLUE, Colour.YELLOW).print()
    mixColours(Colour.YELLOW, Colour.BLUE).print()
    mixColours(Colour.RED, Colour.YELLOW).print()
    mixColours(Colour.YELLOW, Colour.RED).print()
}