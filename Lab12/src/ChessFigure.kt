interface ChessFigure {
    var id: Int

    fun verifyFigurePosition(position: String?)

    fun initFunc(name: String, position: String?, color: FigureColor) {
        if (position != null) {
            println("The figure $name with ${color.color} color was created on position $position")
        } else {
            println("The figure $name with ${color.color} color was created")
        }
    }
}