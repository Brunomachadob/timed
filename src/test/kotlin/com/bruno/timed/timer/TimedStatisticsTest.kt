package com.bruno.timed.timer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration

internal class TimedStatisticsTest {

    @Test
    fun `should return 0 for avg of an empty statistics`() {
        val statistics = TimedStatistics()

        assertEquals(Duration.ZERO, statistics.avg())
    }

    @Test
    fun `should return 3 for avg after adding 2 and then 4 minutes`() {
        val statistics = buildStatistics(
            Duration.ofMinutes(2),
            Duration.ofMinutes(4)
        )

        assertEquals(3, statistics.avg().toMinutes())
    }

    @Test
    fun `should return 4 for max after adding 2 and then 4 minutes`() {
        val statistics = buildStatistics(
            Duration.ofMinutes(2),
            Duration.ofMinutes(4)
        )

        assertEquals(4, statistics.max().toMinutes())
    }

    @Test
    fun `should return 2 for min after adding 2 and then 4 minutes`() {
        val statistics = buildStatistics(
            Duration.ofMinutes(2),
            Duration.ofMinutes(4)
        )

        assertEquals(2, statistics.min().toMinutes())
    }

    @Test
    fun `should return 6 for sum after adding 2 and then 4 minutes`() {
        val statistics = buildStatistics(
            Duration.ofMinutes(2),
            Duration.ofMinutes(4)
        )

        assertEquals(6, statistics.sum().toMinutes())
    }

    private fun buildStatistics(vararg intervals: Duration) = intervals
        .fold(TimedStatistics()) { statistics, duration ->
            statistics.addTime(duration)
        }
}
