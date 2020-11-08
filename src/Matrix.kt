open class Matrix(val rows: Int, val cols: Int) {

    private var elements = DoubleArray(rows * cols)

    constructor(rows: Int, cols: Int, elements: DoubleArray) : this(rows, cols) {
        for (r in 0 until rows) {
            for (c in 0 until cols) set(r, c, elements[index(r, c)])
        }
    }

    constructor(rows: Int, cols: Int, elements: IntArray) : this(
        rows,
        cols,
        elements.map { it.toDouble() }.toDoubleArray()
    )

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        sb.append(elements.slice(0 until cols))
        for (r in 1 until rows) {
            sb.append(", ")
            sb.append(elements.slice(r * cols until (r + 1) * cols))
        }
        sb.append("]")
        return sb.toString()
    }

    operator fun get(r: Int, c: Int) = elements[index(r, c)]

    operator fun set(r: Int, c: Int, element: Double) {
        elements[index(r, c)] = element
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
        val res = Matrix(rows, other.cols)
        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) res[i, j] += this[i, k] * other[k, j]
            }
        }
        return res
    }

    fun clone() = Matrix(rows, cols, elements.copyOf())

    fun transpose(): Matrix {
        val res = Matrix(cols, rows)
        for (r in 0 until rows) {
            for (c in 0 until cols) res[c, r] = this[r, c]
        }
        return res
    }

    private fun elementWise(other: Matrix, transform: Double.(Double) -> Double): Matrix {
        require(cols == other.cols && rows == other.rows)
        val res = Matrix(rows, cols)
        for (r in 0 until rows) {
            for (c in 0 until cols) res[r, c] = this[r, c].transform(other[r, c])
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
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
        var result = rows
        result = 31 * result + cols
        result = 31 * result + elements.contentHashCode()
        return result
    }

    private fun index(r: Int, c: Int) = r * cols + c

    companion object {
        fun identity(size: Int): Matrix {
            val res = Matrix(size, size)
            for (i in 0 until size) res[i, i] = 1.0
            return res
        }

        fun square(dimen: Int, elements: DoubleArray) = Matrix(dimen, dimen, elements)
        fun square(dimen: Int, elements: IntArray) = Matrix(dimen, dimen, elements)

        fun vector(vararg elements: Double) = Matrix(elements.size, 1, elements)
        fun vector(vararg elements: Int) = Matrix(elements.size, 1, elements)
    }
}