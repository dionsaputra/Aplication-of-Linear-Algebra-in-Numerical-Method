import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MatrixTest {

    @Test
    fun testPlus_differentWidth_expectInvalidOperation() {
        val matrixA = Matrix(arrayOf(arrayOf(1, 2), arrayOf(1, 2)))
        val matrixB = Matrix(arrayOf(arrayOf(1, 2, 3), arrayOf(1, 2, 3)))

        try {
            matrixA + matrixB
        } catch (e: Exception) {
            assert(e is InvalidOperation)
        }
    }

    @Test
    fun testPlus_differentHeight_expectInvalidOperation() {
        val matrixA = Matrix(arrayOf(arrayOf(1, 2)))
        val matrixB = Matrix(arrayOf(arrayOf(1, 2), arrayOf(1, 2)))
        try {
            matrixA + matrixB
        } catch (e: Exception) {
            assert(e is InvalidOperation)
        }
    }

    @Test
    fun testPlus_matchDimension_expectValidResult() {
        val matrixA = Matrix(arrayOf(arrayOf(1,2,3)))
        val matrixB = Matrix(arrayOf(arrayOf(4,5,6)))
        assertEquals(Matrix(arrayOf(arrayOf(5,7,9))), matrixA + matrixB)
    }
}