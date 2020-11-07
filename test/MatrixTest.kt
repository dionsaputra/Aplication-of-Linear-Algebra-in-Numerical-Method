import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MatrixTest {

    private lateinit var matrix: Matrix

    @BeforeAll
    fun setupAll() {
        matrix = Matrix.of(doubleArrayOf(1.0, 2.0, 3.0))
    }

    @Test
    fun test_elementWise_DifferentDimension_expectIllegalArgumentException() {
        try {
            matrix.elementWise(Matrix.of(doubleArrayOf(1.0, 2.0)), Double::plus)
        } catch (e: Exception) {
            assert(e is IllegalArgumentException)
        }
    }

    @Test
    fun test_elementWise_matchDimension_expectValidResult() {
        assertEquals(Matrix.of(doubleArrayOf(2.0, 4.0, 6.0)), matrix.elementWise(matrix, Double::plus))
    }
}