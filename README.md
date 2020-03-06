# timed

![CI](https://github.com/Brunomachadob/timed/workflows/CI/badge.svg)

### Timer
```kotlin
val timed = Timed(clock)
val timer = timed.timer()

longRunningComputation()

println(timer.duration().toMinutes())
```

```kotlin
val timed = Timed(clock)

val (result, timer) = timed.withTimer {
    longRunningComputation()
}

println(result)
println(timer.duration().toMinutes())
```

### StopWatch

```kotlin
val timed = Timed(clock)
val stopWatch = timed.stopWatch()

for (i in 1..1000) {
    longRunningComputation(i)           
    stopWatch.lap()
}

println(stopWatch.statistics().toString())
```
```kotlin
val timed = Timed(clock)

val (result, stopWatch) = timed.withStopWatch {
    for (i in 1..1000) {
         longRunningComputation(i)           
        it.lap()
    }

    "result" to "ok"
}

println(result)
println(stopWatch.statistics().toString())
```