import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UniverseTest {
    @Test fun universeShouldBeGeneratedWithGivenSize() {
        val universe = Universe(size = 3)
        assertEquals(9, universe.cells.size)
    }

    @Test fun universeShouldSetupCorrectNeighborRelations() {
        val cells = Universe(size = 3).cells

        assertTrue(cells[0].hasNeighbors(cells[1], cells[3], cells[4]))
        assertTrue(cells[1].hasNeighbors(cells[0], cells[2], cells[3], cells[4], cells[5]))
        assertTrue(cells[2].hasNeighbors(cells[1], cells[4], cells[5]))
        assertTrue(cells[3].hasNeighbors(cells[0], cells[1], cells[4], cells[6], cells[7]))
        assertTrue(cells[4].hasNeighbors(cells[0], cells[1], cells[2], cells[3], cells[5], cells[6], cells[7], cells[8]))
        assertTrue(cells[5].hasNeighbors(cells[1], cells[2], cells[4], cells[7], cells[8]))
        assertTrue(cells[6].hasNeighbors(cells[3], cells[4], cells[7]))
        assertTrue(cells[7].hasNeighbors(cells[3], cells[4], cells[5], cells[6], cells[8]))
        assertTrue(cells[8].hasNeighbors(cells[4], cells[5], cells[7]))
    }
}

fun Cell.hasNeighbors(vararg cells: Cell): Boolean {
    cells.forEach { if (!this.neighbors.contains(it)) return false }
    return cells.size == this.neighbors.size
}

