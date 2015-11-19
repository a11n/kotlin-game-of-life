import org.junit.Test
import kotlin.test.assertFalse

class CellTest {
    @Test fun cellShouldDieWhenUnderpopulation() {
        val cells = listOf(Cell(true))

        cells.evolve()

        assertFalse(cells.first().isAlive)
    }
}
