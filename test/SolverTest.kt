import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EquationSolverTest {

    private val solver = EquationSolver()

    @Test
    fun `test determinant size 1`() {
        assertEquals(2.0, solver.det(Matrix.of(intArrayOf(2))))
    }

    @Test
    fun `test determinant size 2`() {
        val m = Matrix.of(intArrayOf(4, 9), intArrayOf(3, 7))
        assertEquals(1.0, solver.det(m))
    }

    @Test
    fun `test determinant size 3`() {
        val m = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(3.0, 2.0, 1.0), doubleArrayOf(1.0, 0.0, 1.0))
        assertEquals(-8.0, solver.det(m))
    }

    @Test
    fun `test inverse case has inverse`() {
        val m = Matrix.of(intArrayOf(1, 0, 5), intArrayOf(2, 1, 6), intArrayOf(3, 4, 0))
        val expected = Matrix.of(intArrayOf(-24, 20, -5), intArrayOf(18, -15, 4), intArrayOf(5, -4, 1))
        assertEquals(expected, solver.inverse(m))
    }

    @Test
    fun `test inverse case has no inverse`() {
        val m = Matrix.of(intArrayOf(1, 0, 5), intArrayOf(2, 1, 6), intArrayOf(4, 2, 12))
        assertEquals(null, solver.inverse(m))
    }

    @Test
    fun `test solve has solution`() {
        val lhs = Matrix.of(intArrayOf(3, 4), intArrayOf(7, -2))
        val rhs = Matrix.of(intArrayOf(18), intArrayOf(8))
        val expected = Matrix.of(intArrayOf(2), intArrayOf(3))
        assertEquals(expected, solver.solve(lhs, rhs))
    }

    @Test
    fun `test solve has no solution`() {
        val lhs = Matrix.of(intArrayOf(1, 2), intArrayOf(1,2))
        val rhs = Matrix.of(intArrayOf(3), intArrayOf(3))
        assertEquals(null, solver.solve(lhs, rhs))
    }
}