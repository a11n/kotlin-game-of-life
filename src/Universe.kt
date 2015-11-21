class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    init {
        cells.forEachIndexed { i, cell -> cell.neighbors.addAll(getNeighbors(i)) }
    }

    private fun getNeighbors(index: Int): List<Cell> {
        val neighbors: MutableList<Cell> = arrayListOf()
        neighborCoordinatesOf(index.toX(), index.toY())
                .filter { it.isInBounds() }
                .forEach { neighbors.add(cells[it.toIndex()]) }

        return neighbors
    }

    private fun neighborCoordinatesOf(x: Int, y: Int)
            = arrayOf(Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1), Pair(x - 1, y),
            Pair(x + 1, y), Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1))

    private fun Pair<Int, Int>.isInBounds() = !((first < 0).or(first >= size).or(second < 0).or(second >= size))
    private fun Pair<Int, Int>.toIndex() = second * size + first
    private fun Int.toX() = this % size
    private fun Int.toY() = this / size
}
