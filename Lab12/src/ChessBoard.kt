object ChessBoard {
    private var figures: MutableList<ChessFigureDesc> = mutableListOf(
            ChessFigureDesc(1, pawnName, FigureColor.BLACK, "E8"),
            ChessFigureDesc(2, kingName, FigureColor.WHITE, "E1"),
            ChessFigureDesc(3, pawnName, FigureColor.BLACK, "D8"),
            ChessFigureDesc(4, ferzinName, FigureColor.WHITE, "D1"),
    )

    fun getInitChessBoard(): MutableList<ChessFigureDesc> {
        return this.figures
    }
}