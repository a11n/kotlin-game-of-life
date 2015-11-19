class Cell(var isAlive: Boolean) {
    val neighbors: MutableList<Cell> = arrayListOf()

    fun evolve() {
        val livingNeighborsCount = neighbors.filter { it.isAlive }.count()
        isAlive = when(livingNeighborsCount) { 
            0, 1 -> false
            2 -> isAlive
            3 -> true
            else -> false
        }
    }
}
