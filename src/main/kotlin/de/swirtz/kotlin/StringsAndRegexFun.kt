package de.swirtz.kotlin

/**
 * Created by simonw on 17.06.17.
 */

fun main(args: Array<String>) {
    val mod = "ThisIsAString".replaceAfterLast("A", "ManipulatedString")
    println(mod)

    val kotlinLogo = """
                    .| //
                    .|//
                    .|/ \"""
    println(kotlinLogo.trimMargin("."))
}