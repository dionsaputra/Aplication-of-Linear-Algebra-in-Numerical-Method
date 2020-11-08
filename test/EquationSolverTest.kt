import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EquationSolverTest {

    @Test
    fun `test determinant size 1`() {
        assertEquals(2, EquationSolver.det(Matrix.square(1, arrayOf(2))))
    }

    @Test
    fun `test determinant size 2`() {
        val m = Matrix.square(2, arrayOf(4, 9, 3, 7))
        assertEquals(1, EquationSolver.det(m))
    }

    @Test
    fun `test determinant size 3`() {
        val m = Matrix.square(3, arrayOf(1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0, 0.0, 1.0))
        assertEquals(-8.0, EquationSolver.det(m))
    }

    @Test
    fun `test inverse case has inverse`() {
        val m = Matrix.square(3, arrayOf(1, 0, 5, 2, 1, 6, 3, 4, 0))
        val expected = Matrix.square(3, arrayOf(-24, 20, -5, 18, -15, 4, 5, -4, 1).map { it.toDouble() }.toTypedArray())
        assertEquals(expected, EquationSolver.inverse(m))
    }

    @Test
    fun `test inverse case has no inverse`() {
        val m = Matrix.square(3, arrayOf(1, 0, 5, 2, 1, 6, 4, 2, 12))
        assertEquals(null, EquationSolver.inverse(m))
    }

    @Test
    fun `test solve has solution`() {
        val lhs = Matrix.square(2, arrayOf(3, 4, 7, -2))
        val rhs = Matrix.vector(18, 8)
        val expected = Matrix.vector(2.0, 3.0)
        assertEquals(expected, EquationSolver.solve(lhs, rhs))
    }

    @Test
    fun `test solve has no solution`() {
        val lhs = Matrix.square(2, arrayOf(1, 2, 1, 2))
        val rhs = Matrix.vector(3, 3)
        assertEquals(null, EquationSolver.solve(lhs, rhs))
    }
}