package de.swirtz.kotlin.scoping

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object GitHubApiCallerNextGen {
    private val client = OkHttpClient()
    private var cachedLeadResult: Contributor? = null

    fun getKotlinContributor(name: String): Contributor {
        if (cachedLeadResult != null) {
            println("return cached: $cachedLeadResult")
            return cachedLeadResult as Contributor
        }

        val contributors = with(client) {
            val request = Request.Builder().url(ENDPOINT).build()
            val response = request.let {
                newCall(it).execute().use {
                    it.body()?.source()?.readByteArray()?.let { String(it) }
                            ?: throw IllegalStateException("No response from server!")
                }
            }
            response.let {
                println("response from git api: $it\n")
                Gson().fromJson(it, Array<Contributor>::class.java)
            }

        }

        return contributors.first { it.login == name }.also {
            this.cachedLeadResult = it
            println("found kotlin lead: $it")
        }


    }
}
