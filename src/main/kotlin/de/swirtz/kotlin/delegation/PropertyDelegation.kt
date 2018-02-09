package de.swirtz.kotlin.delegation

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaField


class Comp2(var xProp: String, val yProp: String)

class Comp1(private val comp: Comp2) {
    var a by delegated(comp::xProp)
}

fun <R> delegated(prop: KMutableProperty<R>) = MutablePropertyDelegate(prop)

class MutablePropertyDelegate<R>(private val prop: KMutableProperty<R>) {
    private fun <T> ensureDifference(prop1: KProperty<T>, prop2: KProperty<T>) {
        if (prop1.javaField == prop2.javaField) throw IllegalArgumentException("Can't delegate to same property.")
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): R {
        ensureDifference(prop, property)
        return prop.getter.call()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: R) {
        ensureDifference(prop, property)
        return prop.setter.call(value)
    }
}