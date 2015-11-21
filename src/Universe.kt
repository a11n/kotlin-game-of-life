class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    init {
        cells.forEachIndexed { i, cell -> cell.neighbors.addAll(getNeighbors(i)) }
    }

    private fun getNeighbors(index: Int): List<Cell> {
        val x = index.toX()
        val y = index.toY()

        val neighbors: MutableList<Cell> = arrayListOf()
        for (x1 in (x - 1..x + 1))
            for (y1 in (y - 1..y + 1))
                if (isInBounds(x1, y1).and(isNotSelf(x, x1, y, y1)))
                    neighbors.add(cells[indexOf(x1, y1)])

        return neighbors
    }

    private fun isNotSelf(x: Int, x1: Int, y: Int, y1: Int) = (x1 != x).or(y1 != y)

    private fun isInBounds(x: Int, y: Int) = !((x < 0).or(x >= size).or(y < 0).or(y >= size))

    private fun indexOf(x: Int, y: Int) = y * size + x

    private fun Int.toX() = this % size
    private fun Int.toY() = this / size

}
