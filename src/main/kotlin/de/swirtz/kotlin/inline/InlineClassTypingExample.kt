package de.swirtz.kotlin.inline

inline class Password(val value: String)
inline class UserName(val value: String)

fun auth(userName: String, password: String){}
fun auth(userName: UserName, password: Password){}

fun main() {
    auth(UserName("user1"), Password("12345"))
    //does not compile due to type mismatch
    //auth(Password("12345"), UserName("user1"))
}