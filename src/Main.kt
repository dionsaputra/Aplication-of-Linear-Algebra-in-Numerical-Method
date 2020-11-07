fun main(args: Array<String>) {
    val matrixA = Matrix(arrayOf(arrayOf(1,2,3)))
    val matrixB = Matrix(arrayOf(arrayOf(4,5,6)))
    val matrixC = matrixA + matrixB
    val matrixD = Matrix(arrayOf(arrayOf(5,7,9)))
    println(matrixC)
    println(matrixD)
    println(matrixC == matrixD)
}