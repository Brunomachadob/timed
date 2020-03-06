package com.bruno.timed.timer

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit

internal class TimerTest {

    private val clock = mock<Clock>()

    @Test
    fun `should successfully calculate a duration`() {
        val now = Instant.now()
        val twoMinutesLater = now.plus(2, ChronoUnit.MINUTES)

        whenever(clock.instant())
            .thenReturn(now)
            .thenReturn(twoMinutesLater)

        val timer = Timer(clock)

        val result = timer.duration().toMinutes()

        Assertions.assertEquals(2, result)
    }
}
