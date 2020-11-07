import kotlin.math.abs
import kotlin.math.max

fun Double.equalsDelta(other: Double) = abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2
