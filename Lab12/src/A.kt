class A {
    private lateinit var prop: String

    init {
        this.setUp()
    }

    private fun setUp() {
        prop = "100 + 200"
    }

    fun display() {
        println(prop)
    }
}
