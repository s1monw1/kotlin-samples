package de.swirtz.kotlin

/**
 * Created on 06.03.2017.
 */

fun main(args: Array<String>) {
    val notNullable: String = "abc";
    //Compiler Error
    // notNullable = null;
    val nullable: String?
    nullable = null

    //Cannot throw NPE
    notNullable.length

    //Compiler Error, could throw NPE
    //nullable.length

    //Option 1: Works after explicit check
    val l = if (nullable != null) nullable.length else -1

    //Option 2: Safe Calls; returns length if not null, 'null' otherwise [useful in chains, will return null if ANY is null: bob?.department?.head?.name ]
    println("Length of nullable: ${nullable?.length}")


    val listWithNulls = listOf("a", "b", null)
    for (item in listWithNulls) {
        item?.let(::println) // prints A and ignores null
    }

    showElvis(nullable)
    showElvis("NotNull")

    //Option3: If someone loves NPE you can force it with !!
    //nullable!!.length <- this will actually throw a NPE

}

//Elvis operator ?:
private fun showElvis(nullable: String?) {
    val elvis = nullable?.length ?: -1
    println("Elvis operator with '$nullable': $elvis")
}

