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

    operator fun get(row: Int, col: Int) = value[row][col]

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

    private fun elementWise(other: Matrix, transform: Double.(Double) -> Double): Matrix {
        require(cols == other.cols && rows == other.rows)
        val result = Array(rows) { DoubleArray(cols) }
        for (i in 0 until rows) {
            for (j in 0 until cols) result[i][j] = this[i, j].transform(other[i, j])
        }
        return Matrix(result)
    }

    companion object {
        fun of(vararg doubleArray: DoubleArray) = Matrix(doubleArray.asList().toTypedArray())
    }
}