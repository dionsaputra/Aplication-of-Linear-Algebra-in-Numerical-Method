import kotlin.math.abs
import kotlin.math.max

operator fun Number.minus(other: Number): Number {
    return when (this) {
        is Long -> this.toLong() - other.toLong()
        is Int -> this.toInt() - other.toInt()
        is Short -> this.toShort() - other.toShort()
        is Byte -> this.toByte() - other.toByte()
        is Double -> this.toDouble() - other.toDouble()
        is Float -> this.toFloat() - other.toFloat()
        else -> throw RuntimeException("Unknown numeric type")
    }
}

operator fun Number.plus(other: Number): Number {
    return when (this) {
        is Long -> this.toLong() + other.toLong()
        is Int -> this.toInt() + other.toInt()
        is Short -> this.toShort() + other.toShort()
        is Byte -> this.toByte() + other.toByte()
        is Double -> this.toDouble() + other.toDouble()
        is Float -> this.toFloat() + other.toFloat()
        else -> throw RuntimeException("Unknown numeric type")
    }
}

operator fun Number.times(other: Number): Number {
    return when (this) {
        is Long -> this.toLong() * other.toLong()
        is Int -> this.toInt() * other.toInt()
        is Short -> this.toShort() * other.toShort()
        is Byte -> this.toByte() * other.toByte()
        is Double -> this.toDouble() * other.toDouble()
        is Float -> this.toFloat() * other.toFloat()
        else -> throw RuntimeException("Unknown numeric type")
    }
}

operator fun Number.div(other: Number): Number {
    return when (this) {
        is Long -> this.toLong() / other.toLong()
        is Int -> this.toInt() / other.toInt()
        is Short -> this.toShort() / other.toShort()
        is Byte -> this.toByte() / other.toByte()
        is Double -> this.toDouble() / other.toDouble()
        is Float -> this.toFloat() / other.toFloat()
        else -> throw RuntimeException("Unknown numeric type")
    }
}

fun Number.equalsDelta(other: Number): Boolean {
    return when (this) {
        is Long -> this.toLong() == other.toLong()
        is Int -> this.toInt() == other.toInt()
        is Short -> this.toShort() == other.toShort()
        is Byte -> this.toByte() == other.toByte()
        is Double -> abs(this.toDouble() - other.toDouble()) < max(Math.ulp(this), Math.ulp(other.toDouble())) * 2
        is Float -> abs(this.toFloat() - other.toFloat()) < max(Math.ulp(this), Math.ulp(other.toFloat())) * 2
        else -> throw RuntimeException("Unknown numeric type")
    }
}