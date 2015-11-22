import java.util.*

class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    init {
        cells.forEachIndexed { i, cell -> cell.neighbors.addAll(getNeighbors(i)) }
    }

    fun evolve() {
        val livingNeighborCounts = cells.map { it.neighbors.count { it.isAlive } }
        cells.forEachIndexed { i, cell -> cell.evolve(livingNeighborCounts[i]) }
    }

    private fun getNeighbors(index: Int): List<Cell> {
        return ArrayList<Cell>().apply {
            neighborCoordinatesOf(index.toX(), index.toY())
                    .filter { it.isInBounds() }
                    .forEach { add(cells[it.toIndex()]) }
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
