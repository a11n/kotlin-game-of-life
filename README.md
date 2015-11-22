#Game of Life
The [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) is a cellular automation game with a very limited set of principles and rules. Despite its simplicity it allows a variety of different implementations.

This implementation is based on the idea, that a `Cell`, which could be dead or alive, is aware of all other `Cells` surrounding itself (=neighbors):
```kotlin
class Cell(var isAlive : Boolean = false) {
    val neighbors: MutableList<Cell> = arrayListOf()
}
```

With this design a `Cell` is capable of evolving itself. So the few following lines represent the complete set of Conway's rules:
```kotlin
fun evolve(livingNeighborsCount: Int =
            neighbors.count { it.isAlive }) {
        isAlive = when(livingNeighborsCount) { 
            0, 1 -> false
            2 -> isAlive
            3 -> true
            else -> false
        }
    }
```

Since the *Game of Life* is not about a single `Cell` but about a bunch of `Cells` this implementation also consists of an `Universe`, which contains all `Cells` and is responsible for triggering the evolution of all `Cells`:
```kotlin
class Universe(val size: Int) {
    val cells = Array(size * size, { Cell() })

    fun evolve() {
        val livingNeighborCounts = cells.map { it.neighbors.count { it.isAlive } }
        cells.forEachIndexed { i, cell -> cell.evolve(livingNeighborCounts[i]) }
    }
}
```