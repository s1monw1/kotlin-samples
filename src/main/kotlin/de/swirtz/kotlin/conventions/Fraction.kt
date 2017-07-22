package de.swirtz.kotlin.conventions

data class Fraction(val numerator: Int, val denominator: Int) : Comparable<Fraction> {

    val decimal by lazy { numerator.toDouble() / denominator }

    override operator fun compareTo(other: Fraction) = decimal.compareTo(other.decimal)

    override fun toString() = "$numerator/$denominator"

    //unary operators
    operator fun unaryMinus() = Fraction(-this.numerator, this.denominator)

    //binary operators
    operator fun plus(add: Fraction) =
            if (this.denominator == add.denominator) Fraction(this.numerator + add.numerator, denominator)
            else {
                val a = this * add.denominator
                val b = add * this.denominator
                Fraction(a.numerator + b.numerator, a.denominator)
            }

    operator fun times(num: Int) = Fraction(numerator * num, denominator * num)

    //increments
    operator fun dec() = Fraction(this.numerator - 1, this.denominator)


    //invoke convention
    operator fun invoke(prefix: String = "") = println(prefix + toString())
}


operator fun Fraction.get(ind: Int) =
        when (ind) {
            0 -> numerator
            1 -> denominator
            else -> IllegalArgumentException("Index must be 0 or 1")
        }

operator fun Fraction.inc() = Fraction(this.numerator + 1, this.denominator)

operator fun ClosedRange<Fraction>.iterator() =
        object : Iterator<Fraction> {
            var curr: Fraction = start
            override fun hasNext() = curr <= endInclusive
            override fun next() = curr++

        }

fun main(args: Array<String>) {
    println(Fraction(2, 3))

    var sum = Fraction(2, 3) + Fraction(3, 2)
    println("Sum: ${sum.decimal}")
    //invoke
    sum("My invoke prefix: ")
    println("3/2 > 2/2: ${Fraction(3, 2) > Fraction(2, 2)}")
    println("1/2 <= 2/4: ${Fraction(1, 2) <= Fraction(2, 4)}")
    println("Sum after inc: ${++sum}")
    println("Sum after dec: ${--sum}")
    println("Sum negated: ${-sum}")
    println("Sum numerator: ${sum[0]}")

    val fracRange = Fraction(1, 5)..Fraction(5, 7)
    println(Fraction(3, 5) in fracRange)
    for (i in fracRange) {
        println("Next: $i")
    }

    val f = Fraction(2, 3)
    val (a, b) = f
    println("Destructed sum to: ($a, $b)")
}
