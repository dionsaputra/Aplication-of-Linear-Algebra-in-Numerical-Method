class EquationSolver {

    /**
     * compute determinant of a matrix
     */
    fun det(matrix: Matrix): Double {
        require(matrix.rows == matrix.cols)

        if (matrix.rows == 1) return matrix[0, 0]
        if (matrix.rows == 2) return matrix[0, 0] * matrix[1, 1] - matrix[0, 1] * matrix[1, 0]

        var det = 0.0
        for (c in 0 until matrix.cols) {
            var incValue = 1.0
            var decValue = 1.0
            for (r in 0 until matrix.rows) {
                incValue *= matrix[r, (c + r) % matrix.cols]
                decValue *= matrix[matrix.rows - r - 1, (c + r) % matrix.cols]
            }
            det += (incValue - decValue)
        }
        return det
    }

    /**
     * get inverse of a matrix
     */
    fun inverse(matrix: Matrix): Matrix? {
        require(matrix.rows == matrix.cols)
        if (det(matrix).equalsDelta(0.0)) return null

        val inverse = Matrix.identity(matrix.rows)
        val temp = matrix.clone()
        for (fdRow in 0 until matrix.rows) {
            // find focus-diagonal element (first non-zero element in i-th row)
            var fdCol = 0
            while (fdCol < matrix.cols && temp[fdRow, fdCol].equalsDelta(0.0)) fdCol++

            // matrix hasn't inverse if all row is zero
            if (fdCol == matrix.cols) return null

            // scale current row so it's fd has value 1.0
            val scalingFactor = 1 / temp[fdRow, fdCol]
            for (c in 0 until matrix.cols) {
                temp[fdRow, c] *= scalingFactor
                inverse[fdRow, c] *= scalingFactor
            }

            // subtract other row with current-row * subs-factor
            for (r in 0 until matrix.rows) {
                if (r == fdRow) continue
                val subFactor = temp[r, fdCol]
                for (c in 0 until matrix.cols) {
                    temp[r, c] -= subFactor * temp[fdRow, c]
                    inverse[r, c] -= subFactor * inverse[fdRow, c]
                }
            }
        }

        return inverse
    }

    /**
     * solve matrix equation AX = B
     */
    fun solve(lhsMatrix: Matrix, rhsMatrix: Matrix): Matrix? {
        return inverse(lhsMatrix)?.let { it x rhsMatrix }
    }
}