package de.swirtz.kotlin.misc


data class TheData(
    val b: Int,
    val c1: Int,
    val c2: Int,
    val d1: Int,
    val d2: Int
)

data class E(val sumC: Int, val sumD: Int)

fun main(args: Array<String>) {
    val alist = listOf(TheData(1, 2, 1, 4, 5), TheData(1, 3, 4, 6, 3), TheData(2, 2, 2, 2, 2), TheData(3, 1, 2, 1, 2))
    val grouped1 = alist.groupBy(TheData::b).mapValues {
        E(
                it.value.sumBy { it.c1 + it.c2 },
                it.value.sumBy { it.d1 + it.d2 }
        )
    }

    val grouped2 = alist.groupingBy(TheData::b).fold(E(0, 0)) { acc, e ->
        E(acc.sumC + e.c1 + e.c2, acc.sumD + e.d1 + e.d2)
    }

    val grouped3 = alist.groupingBy(TheData::b).aggregate { _, acc: E?, e, _ ->
        E((acc?.sumC ?: 0) + e.c1 + e.c2, (acc?.sumD ?: 0) + e.d1 + e.d2)
    }


    grouped3.forEach {
        println("Group b=${it.key}: ${it.value}")
    }
}
