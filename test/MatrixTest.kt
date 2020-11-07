import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatrixTest {

    @Test
    fun `test plus operator`() {
        val m = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
        assertEquals(Matrix.of(doubleArrayOf(2.0, 4.0, 6.0)), m + m)
    }

    @Test
    fun `test minus operator`() {
        val m = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
        assertEquals(Matrix.of(doubleArrayOf(0.0, 0.0, 0.0)), m - m)
    }

    @Test
    fun `test dot-product operator`() {
        val m = Matrix.of(intArrayOf(1, 2))
        val n = Matrix.of(intArrayOf(3, 4))
        assertEquals(11.0, m * n)
    }

    @Test
    fun `test cross-product operator`() {
        val m = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0))
        val n = Matrix.of(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0), doubleArrayOf(5.0, 6.0))
        val expected = Matrix.of(doubleArrayOf(22.0, 28.0), doubleArrayOf(49.0, 64.0))
        assertEquals(expected, m x n)
    }
}