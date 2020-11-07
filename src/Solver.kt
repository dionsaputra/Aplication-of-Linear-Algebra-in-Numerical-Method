class Solver {

    /**
     * solve matrix equation AX = B
     */
    fun solve(lhsMatrix: Matrix, rhsMatrix: Matrix): Matrix? {
        print(lhsMatrix.inverse())
        return lhsMatrix.inverse()?.let { it * rhsMatrix }
    }
}