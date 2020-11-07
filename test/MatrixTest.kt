import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MatrixTest {

    private lateinit var matrix: Matrix

    @BeforeEach
    fun setupAll() {
        matrix = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
    }

    @Test
    fun plusTest() {
        matrix += matrix
        assertEquals(Matrix.of(doubleArrayOf(2.0, 4.0, 6.0)), matrix)
    }

    @Test
    fun minusTest() {
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
            doubleArrayOf(22.0,28.0),
            doubleArrayOf(49.0, 64.0)
        )
        assertEquals(expected, C)
    }
}