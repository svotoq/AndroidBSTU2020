data class User(var name: String, var rank: Int, var amountOfWins: Int, var amountOfLoses: Int) {
    override fun toString(): String {
        return "Name: $name\nRank: $rank\nAmount of wins: $amountOfWins\nAmount of loses: $amountOfLoses"
    }
}

operator fun User.compareTo(user: User): Int {
    if (this.amountOfWins == user.amountOfWins) {
        return 0
    }

    if (this.amountOfWins > user.amountOfWins) {
        return 1
    }

    return -1
}

operator fun User.inc(): User {
    this.amountOfWins++

    return this
}

operator fun User.dec(): User {
    this.amountOfWins--

    return this
}

operator fun User.rem(user: User): Double {
    return this.amountOfWins.toDouble() % user.amountOfWins.toDouble()
}