class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    init {
        cells.forEachIndexed { i, cell -> cell.setupNeighbors(i) }
    }

    fun evolve() {
        val livingNeighborCounts = cells.map { it.neighbors.count { it.isAlive } }
        cells.forEachIndexed { i, cell -> cell.evolve(livingNeighborCounts[i]) }
    }

    private fun Cell.setupNeighbors(index: Int) {
        neighbors.apply {
            neighborCoordinatesOf(index.toX(), index.toY())
                    .filter { it.isInBounds() }.map { it.toIndex() }
                    .forEach { add(cells[it]) }
        }
    }

    private fun neighborCoordinatesOf(x: Int, y: Int)
            = arrayOf(Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1), Pair(x - 1, y),
            Pair(x + 1, y), Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1))

    private fun Pair<Int, Int>.isInBounds() = !((first < 0).or(first >= size).or(second < 0).or(second >= size))
    fun Pair<Int, Int>.toIndex() = second * size + first
    private fun Int.toX() = this % size
    private fun Int.toY() = this / size
}
