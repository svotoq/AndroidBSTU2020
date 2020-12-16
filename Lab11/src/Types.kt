val implicitInt = 10
val implicitDouble = 10.10
val impicitString = "I am String"

var expicitInt: Int = 5
var expicitDouble: Double = 5.5
var expicitString: String = "I am String too"


val byteVal: Byte = 1
val intValForByte: Int = byteVal.toInt()

val intValForString: Int = 10
val stringVal: String = intValForString.toString()

const val constant: String = "Constant String"

var nullableInt: Int? = null

fun main(args: Array<String>) {
    println("ImplicitInt: $implicitInt")
    println("ImplicitDouble: $implicitDouble")
    println("ImplicitString: $impicitString")

    println("\nExplicitInt: $expicitInt")
    println("ExplicitDouble: $expicitDouble")
    println("ExplicitString: $expicitString")

    println("\nByte to Int: $byteVal - $intValForByte")
    println("Int to String: $intValForString - $stringVal")

    println("Constant string: $constant")

    print("Enter number: ")
    nullableInt = readLine()?.toInt()
    println("NullableInt value: $nullableInt")
}