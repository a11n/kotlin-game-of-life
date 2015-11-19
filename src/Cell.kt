class Cell(var isAlive: Boolean) {
}

fun List<Cell>.evolve() {
    this.forEach { it.isAlive = false }
}