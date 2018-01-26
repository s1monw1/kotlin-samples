package de.swirtz.kotlin.scoping

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

val ENDPOINT = "https://api.github.com/repos/jetbrains/kotlin/contributors"

data class Contributor(val login: String, val contributions: Int)

object GitHubApiCaller {
    private val client = OkHttpClient()

    private var cachedLeadResult: Contributor? = null

    fun getKotlinContributor(name: String): Contributor {
        if (cachedLeadResult != null) {
            println("return cached: $cachedLeadResult")
            return cachedLeadResult as Contributor
        }
        val request = Request.Builder()
            .url(ENDPOINT)
            .build()

        val response = client.newCall(request).execute()


        val responseAsString = response.use {
            val responseBytes = it.body()?.source()?.readByteArray()
            if (responseBytes != null) {
                String(responseBytes)
            } else throw IllegalStateException("No response from server!")
        }

        println("response from git api: $responseAsString\n")

        val contributors =
            Gson().fromJson(responseAsString, Array<Contributor>::class.java)


        val ab = contributors.first { it.login == name }
        this.cachedLeadResult = ab
        println("found kotlin lead: $ab")
        return ab
    }
}

fun main(args: Array<String>) {
    GitHubApiCaller.getKotlinContributor("abreslav")
    GitHubApiCaller.getKotlinContributor("abreslav")
    GitHubApiCallerNextGen.getKotlinContributor("abreslav")
    GitHubApiCallerNextGen.getKotlinContributor("abreslav")
}

