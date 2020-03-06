package com.bruno.timed.timer

import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.util.concurrent.atomic.AtomicReference

class StopWatch(
    private val clock: Clock
): Timer(clock) {
    private var lastLap = startTime
    private var statistics = TimedStatistics()

    fun lap(): Unit = synchronized(this) {
        val now = clock.instant()

        statistics = statistics.addTime(
            Duration.between(lastLap, now)
        )

        lastLap = now
    }

    fun statistics() = statistics
}
