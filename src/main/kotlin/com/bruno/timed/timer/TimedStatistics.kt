package com.bruno.timed.timer

import java.time.Duration
import java.time.temporal.ChronoUnit

class TimedStatistics(
    private val min: Duration = Duration.of(999, ChronoUnit.DAYS),
    private val max: Duration = Duration.ofNanos(0),
    private val sum: Duration = Duration.ZERO,
    private val count: Long = 0
) {
    fun avg(): Duration = when(count) {
        0L -> Duration.ZERO
        else -> sum.dividedBy(count)
    }

    fun max() = max
    fun min() = min
    fun sum() = sum

    fun addTime(duration: Duration): TimedStatistics = TimedStatistics(
        min = getMin(min, duration),
        max = getMax(max, duration),
        sum = sum.plus(duration),
        count = count + 1
    )

    private fun getMax(a: Duration, b: Duration) = if (a > b) a else b
    private fun getMin(a: Duration, b: Duration) = if (a < b) a else b
}
