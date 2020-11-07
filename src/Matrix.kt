data class Matrix(private var elements: Array<DoubleArray>) {

    val rows get() = elements.size
    val cols get() = if (elements.isEmpty()) 0 else elements[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Matrix
        if (rows != other.rows || cols != other.cols) return false
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (!this[r, c].equalsDelta(other[r, c])) return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        return elements.contentDeepHashCode()
    }

    override fun toString() = elements.contentDeepToString()

    operator fun get(row: Int, col: Int) = elements[row][col]

    operator fun set(row: Int, col: Int, element: Double) {
        elements[row][col] = element
    }

    operator fun plus(other: Matrix) = elementWise(other, Double::plus)

    operator fun minus(other: Matrix) = elementWise(other, Double::minus)

    /**
     * matrix dot-product
     */
    operator fun times(other: Matrix): Double {
        var res = 0.0
        for (r in 0 until rows) {
            for (c in 0 until cols) res += (this[r, c] * other[r, c])
        }
        return res
    }

    /**
     * matrix cross-product
     */
    infix fun x(other: Matrix): Matrix {
        require(cols == other.rows)
        val result = Array(rows) { DoubleArray(other.cols) }
        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) result[i][j] += this[i, k] * other[k, j]
            }
        }
        return Matrix(result)
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
        for (r in 0 until rows) {
            for (c in 0 until cols) result[r][c] = this[r, c].transform(other[r, c])
        }
        return Matrix(result)
    }

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