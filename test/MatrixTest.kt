import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatrixTest {


    @Test
    fun plusTest() {
        var matrix = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
        matrix += matrix
        assertEquals(Matrix.of(doubleArrayOf(2.0, 4.0, 6.0)), matrix)
    }

    @Test
    fun minusTest() {
        var matrix = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
        matrix -= matrix
        assertEquals(Matrix.of(doubleArrayOf(0.0, 0.0, 0.0)), matrix)
    }

    @Test
    fun timesTest() {
        val A = Matrix.of(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val B = Matrix.of(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0),
            doubleArrayOf(5.0, 6.0)
        )
        val C = A * B
        val expected = Matrix.of(
            doubleArrayOf(22.0, 28.0),
            doubleArrayOf(49.0, 64.0)
        )
        assertEquals(expected, C)
    }

    @Test
    fun detTest() {
        val M = Matrix.of(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(3.0, 2.0, 1.0),
            doubleArrayOf(1.0, 0.0, 1.0)
        )
        assertEquals(-8.0, M.det())
    }

    @Test
    fun inverse() {
        val M = Matrix.of(
            intArrayOf(1, 0, 5),
            intArrayOf(2, 1, 6),
            intArrayOf(3, 4, 0)
        )
        val expected = Matrix.of(
            intArrayOf(-24, 20, -5),
            intArrayOf(18, -15, 4),
            intArrayOf(5, -4, 1)
        )
        assertEquals(expected, M.inverse())
    }
}