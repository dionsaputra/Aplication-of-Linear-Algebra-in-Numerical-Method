data class Matrix(private val value: Array<DoubleArray>) {

    private val width get() = if (value.isEmpty()) 0 else value[0].size
    private val height get() = value.size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return value.contentDeepEquals((other as Matrix).value)
    }

    override fun hashCode(): Int {
        return value.contentDeepHashCode()
    }

    fun elementWise(other: Matrix, transform: Double.(Double) -> Double): Matrix {
        require(width == other.width && height == other.height)
        val result = Array(height) { DoubleArray(width) }
        for (i in 0 until height) {
            for (j in 0 until width) result[i][j] = value[i][j].transform(other.value[i][j])
        }
        return Matrix(result)
    }

    companion object {
        fun of(vararg doubleArray: DoubleArray) = Matrix(doubleArray.asList().toTypedArray())
    }
}