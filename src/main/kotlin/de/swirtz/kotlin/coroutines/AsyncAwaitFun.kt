package de.swirtz.kotlin.coroutines

import java.security.InvalidAlgorithmParameterException
import java.util.Random
import java.util.concurrent.ThreadLocalRandom


object Random {

    private fun <T : Comparable<T>> checkConditions(left: T, right: T, leftLowerBound: T) {
        if (left < leftLowerBound) {
            throw InvalidAlgorithmParameterException(
                "Left limit can 't be negative"
            )
        } else if (right <= left) {
            throw InvalidAlgorithmParameterException(
                "Right limit can't be less or equal than left limit"
            )
        }
    }

    internal fun generatePositiveFloat(
        leftLimit: Float = Float.MIN_VALUE,
        rightLimit: Float = Float.MAX_VALUE
    ): Float {
        checkConditions(leftLimit, rightLimit, 0F)
        return Random().nextFloat()
    }


    internal fun generatePositiveShort(
        leftLimit: Short = 0,
        rightLimit: Short = Short.MAX_VALUE
    ): Short {
        checkConditions(leftLimit, rightLimit, leftLimit)

        return ThreadLocalRandom.current().nextInt(
            leftLimit.toInt(),
            rightLimit.toInt()
        ).toShort()
    }

}