data class Matrix<T : Number>(private val value: Array<Array<T>>) {

    /**
     * Size of columns
     */
    val width: Int get() = if (value.isEmpty()) 0 else value.first().size

    /**
     * Size of rows
     */
    val height: Int get() = value.size

    /**
     * Matrix result = this + other
     */
    @Suppress("UNCHECKED_CAST")
    operator fun plus(other: Matrix<T>): Matrix<T> {
        if (width != other.width || height != other.height) {
            throw InvalidOperation("Operand matrix have different dimension")
        } else {
            val resultValue = value.copyOf()
            for (i in other.value.indices) {
                for (j in other.value[i].indices) {
                    resultValue[i][j] = (resultValue[i][j] + other.value[i][j]) as T
                }
            }
            return Matrix(resultValue)
        }
    }

    override fun toString() = value.contentDeepToString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return value.contentDeepEquals((other as Matrix<*>).value)
    }

    override fun hashCode(): Int {
        return value.contentDeepHashCode()
    }
}