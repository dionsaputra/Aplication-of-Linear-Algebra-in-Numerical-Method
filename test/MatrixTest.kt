import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatrixTest {

    @Test
    fun `test plus operator`() {
        val m = Matrix(1, 3, arrayOf(1.0, 2.0, 3.0))
        val expected = Matrix(1, 3, arrayOf(2.0, 4.0, 6.0))
        assertEquals(expected, m + m)
    }

    @Test
    fun `test minus operator`() {
        val m = Matrix(1, 3, arrayOf(1, 2, 3))
        val expected = Matrix(1, 3, arrayOf(0, 0, 0))
        assertEquals(expected, m - m)
    }

    @Test
    fun `test dot-product operator`() {
        val m = Matrix(1, 2, arrayOf(1, 2))
        val n = Matrix(1, 2, arrayOf(3, 4))
        assertEquals(11.0, m * n)
    }

    @Test
    fun `test cross-product operator`() {
        val m = Matrix(2, 3, arrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
        val n = Matrix(3, 2, arrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
        val expected = Matrix(2, 2, arrayOf(22.0, 28.0, 49.0, 64.0))
        assertEquals(expected, m x n)
    }

    @Test
    fun `test transpose`() {
        val m = Matrix(2, 3, arrayOf(1, 2, 3, 4, 5, 6))
        val expected = Matrix(3, 2, arrayOf(1, 4, 2, 5, 3, 6))
        assertEquals(expected, m.transpose())
    }
}