package com.bruno.timed

import com.bruno.timed.timer.StopWatch
import com.bruno.timed.timer.Timer
import java.time.Clock

class Timed(
    private val clock: Clock
) {
    fun timer() = Timer(clock)
    fun <T> withTimer(block: (timer: Timer) -> T): Pair<T, Timer> = timer().let {
        block(it) to it
    }

    fun stopWatch() = StopWatch(clock)
    fun <T> withStopWatch(block: (timer: StopWatch) -> T): Pair<T, StopWatch> = stopWatch().let {
        block(it) to it
    }
}
