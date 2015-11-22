class Cell(var isAlive : Boolean = false) {
    val neighbors: MutableList<Cell> = arrayListOf()

    fun evolve(livingNeighborsCount: Int) {
        isAlive = when(livingNeighborsCount) { 
            0, 1 -> false
            2 -> isAlive
            3 -> true
            else -> false
        }
    }
}
