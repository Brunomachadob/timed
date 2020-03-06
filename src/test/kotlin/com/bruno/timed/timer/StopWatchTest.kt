package com.bruno.timed.timer

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Duration
import java.time.Instant

internal class StopWatchTest {

    private val clock = mock<Clock>()

    @Test
    fun `should successfully calculate a duration`() {
        val stopWatch = buildStopWatch(
            Duration.ofMinutes(2)
        )

        val result = stopWatch.duration().toMinutes()

        assertEquals(2, result)
    }

    @Test
    fun `should successfully calculate some statistics`() {
        val stopWatch = buildStopWatch(
            Duration.ofMinutes(2),
            Duration.ofSeconds(30)
        )

        val duration = stopWatch.duration().seconds
        val statistics = stopWatch.statistics()

        assertEquals(150, duration)
        assertEquals(150, statistics.sum().seconds)
        assertEquals(75, statistics.avg().seconds)
        assertEquals(30, statistics.min().seconds)
        assertEquals(120, statistics.max().seconds)
    }

    private fun buildStopWatch(vararg intervals: Duration): StopWatch {
        val now = Instant.now()

        intervals.fold(
            whenever(clock.instant()).thenReturn(now) to now
        ) { (stub, prevTime), curr ->
            val newInstant = prevTime + curr

            stub.thenReturn(newInstant) to newInstant
        }

        return StopWatch(clock).also { watch ->
            repeat(intervals.size) {
                watch.lap()
            }
        }
    }
}
