package sealed

sealed class Mammal(val name: String)

class Cat(val catName: String) : Mammal(catName)
data class Human(val humanName: String, val job: String) : Mammal(humanName)

fun main(args: Array<String>) {
    println(greetMammal(Cat("Garfield")))
    println(greetMammal(Human("Peter", "Designer")))
    val (x,y) = Human("","")

}

fun greetMammal(mammal: Mammal) =
        when (mammal) {
            is Human -> "Hello ${mammal.name}; You're working as a ${mammal.job}"
            is Cat -> "Hello ${mammal.name}"
            else -> "Hello unknown"

        }