package de.swirtz.kotlin.dsl

import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun generateSQLInsert(init: SQLGeneratorConfig.() -> Unit) {
    val config = SQLGeneratorConfig(SQLGeneratorConfig.Operation.INSERT).apply(init)
    config.convertToSql()
}

class SQLGeneratorConfig(val op: Operation) {

    enum class Operation {
        INSERT;
    }

    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null
    var tableName: String? = null
    var separator: String? = ";"

    fun input(s: InputStream) {
        inputStream = s
    }

    fun fromFile(p: Path) {
        inputStream = Files.newInputStream(p)
    }

    fun writeResultTo(out: OutputStream) {
        outputStream = out
    }

    fun writeResultToFile(file: Path) {
        outputStream = Files.newOutputStream(file)
    }

    private fun String.csvParts(separator: String = ";") = split(separator)

    //Only works with String columns
    internal fun convertToSql(): String {
        return when (op) {
            Operation.INSERT -> convertToSqlInsert()
            else -> throw NotImplementedError()
        }
    }

    private fun convertToSqlInsert(): String {
        val csvLines = inputStream?.reader()?.readLines()
                ?: throw IllegalStateException("inputStream in config must be set.")
        val tableName = tableName ?: throw IllegalStateException("inputStream in config must be set.")
        val separator = separator ?: ";"

        val sql = StringBuilder(
            "INSERT INTO `$tableName` (${csvLines[0].csvParts(separator).joinToString(",") { "`$it`" }}) \nVALUES"
        )

        csvLines.drop(1).joinToString(separator = ",", postfix = ";") {
            "\n\t(${it.csvParts(separator).joinToString(",") { "'$it'" }})"
        }.apply { sql.append(this) }
        return sql.toString().apply {
            outputStream?.let {
                this.byteInputStream().copyTo(it)
            }
        }
    }


}


fun main(args: Array<String>) {

    generateSQLInsert {
        fromFile(Paths.get("src/main/resources/ui_glossary_optimizer.csv"))
        tableName = "ui_glossary"
        writeResultTo(System.out)
    }
}

