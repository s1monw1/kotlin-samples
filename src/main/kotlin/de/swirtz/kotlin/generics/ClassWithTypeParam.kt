package de.swirtz.kotlin.generics

/**
 * Created on 09.03.2017.
 * @author: simon-wirtz
 *
 *  What 'out' means: You can think of ClassWithTypeParam as being a producer of T's, and NOT a consumer of T's.
 */
class ClassWithTypeParam<out T>(t: T) {
    val type = t

    fun getTypeMember() = type
}

fun main(args: Array<String>) {
    val c1 = ClassWithTypeParam<Int>(123)
    val member1: Int = c1.getTypeMember()
    //type param can be inferred because of constructor argument
    val c2 = ClassWithTypeParam("StringParam")
    val member2: String = c2.getTypeMember();
    println("Member of Int-ParamType: $member1\nMember of String-ParamType: $member2")

    //declaration-side variance; notice the 'out' keyword in TypeParam-declaration of ClassWithTypeParam;
    //this means: class only produces T's (get-method)
    val objects: ClassWithTypeParam<Any> = c2
    val objectFromClass = objects.getTypeMember()
    println("Member of Object-ParamType: $objectFromClass")

    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any: Array<Any> = arrayOf(3, 2, 1)
    println(ints)
    copy(ints, any)
//    not allowed!! copy(any, ints)

    var intList:List<String> = genericFunction("String-List expected")
    val stringList:List<Int> = genericFunction(11)

}

//from is projected, use-site variance
fun copy(from: Array<out Any>, to: Array<in Any>) {
    println("copy called")
    to.set(0, from.get(0))

}

fun <T> genericFunction(item: T) : List<T>{
    return arrayListOf<T>(item)
}