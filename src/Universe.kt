class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    init {
        cells.forEachIndexed { i, cell -> cell.neighbors.addAll(getNeighbors(i)) }
    }

    private fun getNeighbors(index: Int): List<Cell> {
        val x = index.toX()
        val y = index.toY()

        val neighbors: MutableList<Cell> = arrayListOf()
        neighbors.tryToAddNeighborAt(x - 1, y - 1)
        neighbors.tryToAddNeighborAt(x, y - 1)
        neighbors.tryToAddNeighborAt(x + 1, y - 1)
        neighbors.tryToAddNeighborAt(x - 1, y)
        neighbors.tryToAddNeighborAt(x + 1, y)
        neighbors.tryToAddNeighborAt(x - 1, y + 1)
        neighbors.tryToAddNeighborAt(x, y + 1)
        neighbors.tryToAddNeighborAt(x + 1, y + 1)

        return neighbors
    }

    private fun MutableList<Cell>.tryToAddNeighborAt(x: Int, y: Int) {
        if (isInBounds(x, y)) this.add(cells[indexOf(x, y)])
    }

    private fun isInBounds(x: Int, y: Int) = !((x < 0).or(x >= size).or(y < 0).or(y >= size))

    private fun indexOf(x: Int, y: Int) = y * size + x

    private fun Int.toX() = this % size
    private fun Int.toY() = this / size

}
