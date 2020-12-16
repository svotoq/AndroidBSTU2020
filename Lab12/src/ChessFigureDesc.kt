class ChessFigureDesc constructor(
        _id: Int,
        _name: String,
        _color: FigureColor
) : ChessFigure {
    inner class FigureState {
        fun canThisFigureMove(isItMove: Boolean) {
            if (isItMove) {
                state = readyToMoveState
                availableFigures[availableFigures.indexOfFirst { x -> x.id == id }] = this@ChessFigureDesc
                return
            }

            state = cannotMoveState
            availableFigures[availableFigures.indexOfFirst { x -> x.id == id }] = this@ChessFigureDesc
        }

        fun canAttackOrBeingAttacked(isItMove: Boolean) {
            val closeFigures = availableFigures.count {
                it.position!![0] == this@ChessFigureDesc.position!![0]
                        && it.color != this@ChessFigureDesc.color
                        && (it.position!![1].toString().toInt() + 1 == this@ChessFigureDesc.position!![1].toString().toInt()
                        || it.position!![1].toString().toInt() -1 == this@ChessFigureDesc.position!![1].toString().toInt())
            }

            state = if (isItMove && closeFigures > 0) {
                isReadyToAttackState
            } else if (isItMove && closeFigures == 0) {
                noEnemiesToAttackedState
            } else if (!isItMove && closeFigures == 0) {
                isSafeState
            } else {
                canBeAttackedState
            }
        }
    }

    companion object {
        val availableFigures: MutableList<ChessFigureDesc> = mutableListOf()
        val movementHistory: MutableList<String> = mutableListOf()
    }

    override var id: Int = 0
    var name: String
    var color: FigureColor
    var position: String? = null
    var state: String = cannotMoveState

    init {
        this.id = _id
        this.name = _name
        this.color = _color
    }

    constructor(_id: Int, _name: String, _color: FigureColor, _position: String?) : this(_id, _name, _color) {
        this.verifyFigurePosition(_position)
        this.verifyCorrectAmountOfFigures()
    }

    fun getFigureMovementAbility() {
        when (this.name) {
            kingName -> println("King can move in any direction but only on one position")
            ferzinName -> println("Ferzin combines both types of movement from tower and elephant")
            horseName -> println("Horse can move in each direction in symbol of Г")
            towerName -> println("Tower can only move front, back, left and right on any position")
            elephantName -> println("Elephant can only move on diagonal on any position")
            pawnName -> println("Pawn can only go forward and attack close to it enemies on left and right sides")
            else -> println("Unknown figure")
        }
    }

    override fun verifyFigurePosition(position: String?) {
        if (position == null || position.length !in 1..2) {
            this.position = null
            return
        }

        if (position[0].toLowerCase() !in 'a'..'h' && position[1].toString().toInt() !in 1..8) {
            this.position = null
            return
        }

        this.position = position
    }

    private fun verifyCorrectAmountOfFigures() {
        val figures = availableFigures.groupBy { x -> x.name}[this.name]

        when (this.name) {
            kingName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfKings / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            ferzinName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfFerzins / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            horseName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfHorses / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            elephantName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfElephants / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            towerName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfTowers / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            pawnName -> {
                if (figures != null && figures.count { x -> x.color == this.color } >= amountOfPawns / 2) {
                    println("---Unable to add ${this.color} ${this.name} on position ${this.position} because it has reached limit")
                    return
                }
            }
            else -> {
                println("Unknown figure")
                return
            }
        }

        availableFigures.add(this)
        initFunc(this.name, this.position, this.color)
    }
}

fun ChessFigureDesc.moveToNewPosition(position: String) {
    val storyItem: String = "${this.color} ${this.name} has moved from ${this.position} to $position"

    this.position = position
    ChessFigureDesc.availableFigures[ChessFigureDesc.availableFigures.indexOfFirst { x -> x.id == this.id }] = this

    ChessFigureDesc.movementHistory.add(storyItem)
}

fun ChessFigureDesc.removeFigureFromBoard() {
    this.position = null
    ChessFigureDesc.availableFigures.removeIf { x -> x.position == null }
}