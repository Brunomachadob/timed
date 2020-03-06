package com.bruno.timed.timer

import java.time.Clock
import java.time.Duration
import java.time.Instant

open class Timer(
    private val clock: Clock
) {
    protected val startTime: Instant = clock.instant()

    fun duration(): Duration = Duration.between(startTime, clock.instant())
}
