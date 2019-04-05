package de.swirtz.kotlin.inline

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

inline class JsonString(val value: String)

data class JsonData(val x: Int, val y: Int)

inline fun <reified T> JsonString.asJson() = jacksonObjectMapper().readValue<T>(this.value)

fun main() {
    val jsonString = JsonString("""{ "x":200, "y":300 }""")
    val data: JsonData = jsonString.asJson()
    val nonJsonString = "whatever"
    nonJsonString.asJson<JsonData>()


}