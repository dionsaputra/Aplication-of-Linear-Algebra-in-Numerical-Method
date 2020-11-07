import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SolverTest {

    @Test
    fun testSolveSolutionExist() {
        val solver = Solver()
        val lhs = Matrix.of(
            intArrayOf(3, 4),
            intArrayOf(7, -2)
        )
        val rhs = Matrix.of(intArrayOf(18), intArrayOf(8))
        val expected = Matrix.of(intArrayOf(2), intArrayOf(3))
        assertEquals(expected, solver.solve(lhs, rhs))
    }
}