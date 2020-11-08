open class Matrix<T : Number>(val rows: Int, val cols: Int) {

    lateinit var elements: Array<T>

    constructor(rows: Int, cols: Int, elements: Array<T>) : this(rows, cols) {
        this.elements = elements
        for (r in 0 until rows) {
            for (c in 0 until cols) set(r, c, elements[index(r, c)])
        }
    }

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

    operator fun set(r: Int, c: Int, element: T) {
        elements[index(r, c)] = element
    }

    operator fun plus(other: Matrix<T>) = elementWise(other, Number::plus)

    operator fun minus(other: Matrix<T>) = elementWise(other, Number::minus)

    /**
     * matrix dot-product
     */
    operator fun times(other: Matrix<T>): Double {
        var res = 0.0
        for (r in 0 until rows) {
            for (c in 0 until cols) res += (this[r, c].toDouble() * other[r, c].toDouble())
        }
        return res
    }

    /**
     * matrix cross-product
     */
    infix fun <R : Number> x(other: Matrix<R>): Matrix<Double> {
        require(cols == other.rows)
        val res = Matrix(rows, other.cols, Array(rows * other.cols) { 0.0 })
        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) res[i, j] += (this[i, k] * other[k, j]).toDouble()
            }
        }
        return res
    }

    fun clone() = Matrix(rows, cols, elements.copyOf())

    fun transpose(): Matrix<T> {
        val res = Matrix<T>(cols, rows, elements.copyOf())
        for (r in 0 until rows) {
            for (c in 0 until cols) res[c, r] = this[r, c]
        }
        return res
    }

    @Suppress("UNCHECKED_CAST")
    private fun elementWise(other: Matrix<T>, transform: Number.(Number) -> Number): Matrix<T> {
        require(cols == other.cols && rows == other.rows)
        val res = Matrix(rows, cols, elements.copyOf())
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val element = this[r, c].transform(other[r, c])
                res[r, c] = element as T
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Matrix<*>
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
        inline fun <reified T : Number> diagonal(size: Int, one: T, zero: T): Matrix<T> {
            val res = Matrix<T>(size, size, Array(size * size) { zero })
            for (i in 0 until size) res[i, i] = one
            return res
        }

        fun <T : Number> square(dimen: Int, elements: Array<T>) = Matrix(dimen, dimen, elements)

        inline fun <reified T : Number> vector(vararg elements: T) = Matrix<T>(elements.size, 1, arrayOf(*elements))

    }
}