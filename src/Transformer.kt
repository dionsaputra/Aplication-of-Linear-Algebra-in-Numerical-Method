import kotlin.math.cos
import kotlin.math.sin

object Transformer {

    inline fun <reified T : Number> translate(vec2d: Matrix<T>, tx: T, ty: T): Matrix<T> {
        val vec3d = Matrix.vector(*vec2d.elements, 1)
        val transformer = Matrix(3, 3, arrayOf(1, 0, tx, 0, 1, ty, 0, 0, 1))
        val result = transformer x vec3d
        return Matrix.vector(result[0, 0] as T, result[1, 0] as T)
    }

    fun <T : Number> rotate(vec2d: Matrix<T>, rad: Double): Matrix<Double> {
        val cosRad = cos(rad)
        val sinRad = sin(rad)
        return matrix2d(arrayOf(cosRad, -sinRad, sinRad, cosRad)) x vec2d
    }

    fun <T : Number> scale(vec2d: Matrix<T>, kx: Double, ky: Double): Matrix<Double> {
        return matrix2d(arrayOf(kx, 0.0, 0.0, ky)) x vec2d
    }

    fun <T : Number> shearX(vec2d: Matrix<T>, k: Double): Matrix<Double> {
        return matrix2d(arrayOf(1.0, 0.0, k, 1.0)) x vec2d
    }

    fun <T : Number> shearY(vec2d: Matrix<T>, k: Double): Matrix<Double> {
        return matrix2d(arrayOf(1.0, k, 0.0, 1.0)) x vec2d
    }

    fun <T : Number> stretchX(vec2d: Matrix<T>, k: Double): Matrix<Double> {
        return matrix2d(arrayOf(k, 0.0, 0.0, 1.0)) x vec2d
    }

    fun <T : Number> stretchY(vec2d: Matrix<T>, k: Double): Matrix<Double> {
        return matrix2d(arrayOf(1.0, 0.0, 0.0, k)) x vec2d
    }

    fun <T : Number> reflectX(vec2d: Matrix<T>): Matrix<Double> {
        return matrix2d(arrayOf(1, 0, 0, -1)) x vec2d
    }

    fun <T : Number> reflectY(vec2d: Matrix<T>): Matrix<Double> {
        return matrix2d(arrayOf(-1, 0, 0, 1)) x vec2d
    }

    fun <T : Number> reflectYX(vec2d: Matrix<T>): Matrix<Double> {
        return matrix2d(arrayOf(0, 1, 1, 0)) x vec2d
    }

    fun <T : Number> reflectYminX(vec2d: Matrix<T>): Matrix<Double> {
        return matrix2d(arrayOf(0, -1, -1, 0)) x vec2d
    }

    fun <T : Number> reflectOrigin(vec2d: Matrix<T>): Matrix<Double> {
        return matrix2d(arrayOf(-1, 0, 0, -1)) x vec2d
    }

    private fun <T : Number> matrix2d(elements: Array<T>): Matrix<T> {
        return Matrix(2, 2, elements);
    }
}