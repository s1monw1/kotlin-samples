package de.swirtz.kotlin.coroutines

import kotlinx.coroutines.experimental.*


suspend fun getReceiverAddressFromDatabase(): String {
    delay(1000)
    return "coroutine@kotlin.org"
}

suspend fun sendEmail(r: String, msg: String): Boolean {
    delay(2000)
    println("Sent '$msg' to $r")
    return true
}

suspend fun sendEmailSuspending(): Boolean {
    val msg = GlobalScope.async {
        delay(500)
        "The message content"
    }
    val recipient = GlobalScope.async { getReceiverAddressFromDatabase() }
    println("Waiting for email data")

    val sendStatus = GlobalScope.async {
        sendEmail(recipient.await(), msg.await())
    }
    return sendStatus.await()
}

fun main(args: Array<String>) = runBlocking {
    val job = GlobalScope.launch {
        sendEmailSuspending()
        println("Email sent successfully.")
    }
    job.join()
    println("Finished")

}


