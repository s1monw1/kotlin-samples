package de.swirtz.kotlin.misc

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * The initializer will be called once, on first access. The current instance (in which the lazy property is defined)
 * will be passed into that block.
 */
class LazyThisContext<out T>(private val initializer: (Any?) -> T) : ReadOnlyProperty<Any?, T> {
    private object UNINITIALIZED_VALUE

    private var prop: Any? = UNINITIALIZED_VALUE

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return if (prop == UNINITIALIZED_VALUE) {
            synchronized(this) {
                return if (prop == UNINITIALIZED_VALUE) {
                    //invoke the initializer block with thisRef
                    initializer(thisRef).also { prop = it }
                } else {
                    prop as T
                }
            }
        } else prop as T
    }
}

