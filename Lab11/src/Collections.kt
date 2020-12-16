fun main(args: Array<String>) {
    val numberList = ArrayList<Int>()
    numberList.add(2)
    numberList.add(4)
    numberList.add(5)
    numberList.add(8)

    val firstResult: Boolean = containsIn(numberList) { x -> x % 3 == 0 }
    println("First lambda result: $firstResult")

    val secondResult: Boolean = containsIn(numberList) { x -> x - 5 > 0 }
    println("Second lambda result: $secondResult\n")

    listOf()
    mapTest()
}

fun containsIn(collection: Collection<Int>, operation: (item: Int) -> Boolean): Boolean =
        collection.any { item -> operation(item) }

fun listOf() {
    val intList: MutableList<Int> = mutableListOf(1, 2, 3, 2, 7, 7, 9)

    intList.plus(5)
    intList += 10

    val distinctElements: List<Int> = intList.distinct()
    print("Distinct:")
    distinctElements.forEach { item -> print(" $item") }

    val filteredElements: List<Int> = intList.filter { item -> item % 2 != 0 }
    print("\nFiltered:")
    filteredElements.forEach { item -> print(" $item") }

    val onlyPrimeNumbers: List<Int> = intList.filter(::isPrime)
    print("\nPrime numbers:")
    onlyPrimeNumbers.forEach { item -> print(" $item") }

    val findOnlyValueThree: Int? = intList.find { item -> item == 3 }
    println("\nDoes list contain item: $findOnlyValueThree")

    val groupByNums: Map<Boolean, List<Int>> = intList.groupBy { item -> item % 2 == 0 }
    print("Map of items:")
    println(groupByNums.forEach { item -> print(" ${item.key}: ${item.value} ") })

    val areAllItemsHigherThanFour: Boolean = intList.all { item -> item > 4 }
    println("Are all list items higher than 4: $areAllItemsHigherThanFour")

    val isThereOneItemLowerThanSix: Boolean = intList.any { item -> item < 6 }
    println("Is there item lower than 6: $isThereOneItemLowerThanSix")

    val (firstItem: Int, secondItem: Int) = intList
    print("First and second items: $firstItem and $secondItem")
}

fun isPrime(value: Int): Boolean {
    var flag = false
    for (i in 2..value / 2) {
        if (value % i == 0) {
            flag = true
            break
        }
    }

    return flag
}

fun mapTest() {
    val testResult: Map<String, Int> = mapOf(
            "Stasyan" to 29,
            "Yura" to 37,
            "Vasya" to 10,
            "Petya" to 39,
            "Pavel" to 9,
            "Lexa" to 30
    )

    val mappedMap: Map<String, Int> = testResult.mapValues { when(it.value) {
        40 -> 10
        39 -> 9
        38 -> 8
        in 35..37 -> 7
        in 32..34 -> 6
        in 29..31 -> 5
        in 25..28 -> 4
        in 22..24 -> 3
        in 19..21 -> 2
        else -> 1
    } }

    println("\n\nResults: ")
    for ((key, value) in mappedMap) {
        println("$key = $value")
    }

    val uniqueMarks: HashSet<Int> = mappedMap.values.toHashSet()
    println("\nMarks: ")
    for (mark in uniqueMarks) {
        val marksCount: Int = mappedMap.count { it.value == mark }
        println("$mark: $marksCount")
    }

    val amountOfBadMarks: Boolean = mappedMap.any { it.value < 4 }
    println("\nAre there any fails: $amountOfBadMarks")
}