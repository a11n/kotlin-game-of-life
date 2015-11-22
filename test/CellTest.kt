import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CellTest {
    @Test fun cellShouldDieWhenUnderpopulation() {
        val cell = Cell(true)

        cell.evolve(livingNeighborsCount = 0)

        assertFalse(cell.isAlive)
    }

    @Test fun cellShouldLiveWhenTwoOrThreeLivingNeighbors() {
        val cell = Cell(true)
        
        cell.evolve(livingNeighborsCount = 2)
        
        assertTrue(cell.isAlive)
    }

    @Test fun cellShouldDieWhenOverpopulation() {
        val cell = Cell(true)
        
        cell.evolve(livingNeighborsCount = 4)

        assertFalse(cell.isAlive)
    }

    @Test fun cellShouldRebornWhenThreeLivingNeighbors() {
        val cell = Cell(false)

        cell.evolve(livingNeighborsCount = 3)

        assertTrue(cell.isAlive)
    }
}
