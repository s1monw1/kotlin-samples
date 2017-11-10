package de.swirtz.kotlin.misc

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val file = args[0]
    Files.lines(Paths.get(file)).forEach {
        if (it.startsWith("# ")) {
            println(it.replace("# ", ""))
        } else if (it.startsWith("##")) {
            println(it.replace("#", " "))
        }
    }
}