package com.bruno.timed

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit

internal class TimedTest {

    private val clock = mock<Clock>()

    private val underTest = Timed(clock)

    @Test
    fun timer() {
        whenever(clock.instant())
            .thenReturn(Instant.now())

        Assertions.assertNotNull(underTest.timer())
    }

    @Test
    fun stopWatch() {
        whenever(clock.instant())
            .thenReturn(Instant.now())

        Assertions.assertNotNull(underTest.stopWatch())
    }

    @Test
    fun withTimer() {
        val now = Instant.now()
        val twoMinutesLater = now.plus(2, ChronoUnit.MINUTES)

        whenever(clock.instant())
            .thenReturn(now)
            .thenReturn(twoMinutesLater)

        val (result, timer) = underTest.withTimer {
            true
        }

        Assertions.assertTrue(result)
        Assertions.assertEquals(2, timer.duration().toMinutes())
    }

    @Test
    fun withStopWatch() {
        val now = Instant.now()
        val twoMinutesLater = now.plus(2, ChronoUnit.MINUTES)

        whenever(clock.instant())
            .thenReturn(now)
            .thenReturn(twoMinutesLater)

        val (result, timer) = underTest.withStopWatch {
            it.lap()
            true
        }

        Assertions.assertTrue(result)
        Assertions.assertEquals(2, timer.duration().toMinutes())
        Assertions.assertEquals(2, timer.statistics().avg().toMinutes())
    }
}
