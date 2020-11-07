import kotlin.math.abs
import kotlin.math.max

data class Matrix(private var value: Array<DoubleArray>) {

    private val rows get() = value.size
    private val cols get() = if (value.isEmpty()) 0 else value[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return value.contentDeepEquals((other as Matrix).value)
    }

    override fun hashCode(): Int {
        return value.contentDeepHashCode()
    }

    override fun toString() = value.contentDeepToString()

    operator fun get(row: Int, col: Int) = value[row][col]

    operator fun set(row: Int, col: Int, element: Double) {
        value[row][col] = element
    }

    operator fun plus(other: Matrix) = elementWise(other, Double::plus)

    operator fun minus(other: Matrix) = elementWise(other, Double::minus)

    operator fun times(other: Matrix): Matrix {
        require(cols == other.rows)
        val result = Array(rows) { DoubleArray(other.cols) }
        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) result[i][j] += this[i, k] * other[k, j]
            }
        }
        return Matrix(result)
    }

    fun det(): Double {
        require(rows == cols)

        var det = 0.0
        for (j in 0 until cols) {
            var incValue = 1.0
            var decValue = 1.0
            for (i in 0 until rows) {
                incValue *= this[i, (j + i) % cols]
                decValue *= this[rows - i - 1, (j + i) % cols]
            }
            det += (incValue - decValue)
        }
        return det
    }

    fun inverse(): Matrix? {
        require(rows == cols)
        if (det() == 0.0) return null

        val result = Matrix.identity(rows)
        val temp = clone()
        for (fdRow in 0 until rows) {
            // find focus-diagonal element (first non-zero element in i-th row)
            var fdCol = 0
            while (fdCol < cols && temp[fdRow, fdCol].equalsDelta(0.0)) fdCol++

            // matrix hasn't inverse if all row is zero
            if (fdCol == cols) return null

            // scale current row so it's fd has value 1.0
            val scalingFactor = 1/temp[fdRow, fdCol]
            temp.rowScaling(fdRow, scalingFactor)
            result.rowScaling(fdRow, scalingFactor)

            // subtract other row with current-row * subs-factor
            for (i in 0 until rows) {
                if (i == fdRow) continue
                val subFactor = temp[i, fdCol]
                for (j in 0 until cols) {
                    temp[i, j] -= subFactor * temp[fdRow, j]
                    result[i, j] -= subFactor * result[fdRow, j]
                }
            }
        }

        return result
    }

    fun clone(): Matrix {
        val value = Array(rows) { row ->
            DoubleArray(cols) { col -> this[row, col] }
        }
        return Matrix(value)
    }

    private fun elementWise(other: Matrix, transform: Double.(Double) -> Double): Matrix {
        require(cols == other.cols && rows == other.rows)
        val result = Array(rows) { DoubleArray(cols) }
        for (i in 0 until rows) {
            for (j in 0 until cols) result[i][j] = this[i, j].transform(other[i, j])
        }
        return Matrix(result)
    }

    private fun rowScaling(row: Int, factor: Double) {
        for (i in 0 until cols) this[row, i] *= factor
    }

    private fun Double.equalsDelta(other: Double) = abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2

    companion object {

        fun of(vararg doubleArray: DoubleArray) = Matrix(doubleArray.asList().toTypedArray())

        fun of(vararg intArray: IntArray): Matrix {
            return Matrix(intArray.map { rows ->
                rows.map { it.toDouble() }.toDoubleArray()
            }.toTypedArray())
        }

        fun identity(size: Int): Matrix {
            val value = Array(size) { DoubleArray(size) }
            for (i in 0 until size) value[i][i] = 1.0
            return Matrix(value)
        }
    }
}