import org.junit.Test
import kotlin.test.assertFalse

class CellTest {
    @Test fun cellShouldDieWhenUnderpopulation() {
        val cell = Cell(true)

        cell.evolve()

        assertFalse(cell.isAlive)
    }
}
