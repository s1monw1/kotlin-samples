package de.swirtz.kotlin.inline

inline class WrappedInt(val value: Int)

//In byte code we use boxed type
fun take(w: WrappedInt?) {
    if (w != null) println(w.value)
}


fun main() {
    take(WrappedInt(5))
}



