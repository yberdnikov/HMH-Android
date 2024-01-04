package com.hmh.hamyeonham.common.time

import android.content.Context
import android.text.format.DateUtils
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.util.concurrent.TimeUnit

fun Instant.Companion.systemNow(): Instant = Clock.System.now()

fun Instant.toDefaultLocalDate(): LocalDate = toLocalDateTime(TimeZone.currentSystemDefault()).date

fun Long.formatDate(context: Context): String =
    DateUtils.formatDateTime(
        context,
        this,
        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE,
    )

fun Instant.formatDate(context: Context): String = toEpochMilliseconds().formatDate(context)

fun Long.formatNumericDate(context: Context): String =
    DateUtils.formatDateTime(
        context,
        this,
        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NUMERIC_DATE,
    )

fun Instant.formatNumericDate(context: Context): String = toEpochMilliseconds().formatNumericDate(context)

// LocalDate의 확장 함수로 해당 날짜의 시작 시간과 종료 시간을 LocalDateTime으로 반환
fun LocalDate.toStartOfDay(): LocalDateTime = LocalDateTime(year, monthNumber, dayOfMonth, 0, 0)

fun LocalDate.toEndOfDay(): LocalDateTime = LocalDateTime(year, monthNumber, dayOfMonth, 23, 59, 59)

// LocalDateTime의 확장 함수로 해당 시간을 Epoch 밀리초로 변환
fun LocalDateTime.toEpochMilliseconds(timeZone: TimeZone): Long = toInstant(timeZone).toEpochMilliseconds()

// 현재 날짜의 시작 시간과 종료 시간을 Epoch 밀리초로 반환하는 함수
fun getCurrentDayStartEndEpochMillis(): Pair<Long, Long> {
    val timeZone = TimeZone.currentSystemDefault()
    val currentDate = Clock.System.now().toLocalDateTime(timeZone).date
    val startOfDay = currentDate.toStartOfDay().toEpochMilliseconds(timeZone)
    val endOfDay = currentDate.toEndOfDay().toEpochMilliseconds(timeZone)
    return Pair(startOfDay, endOfDay)
}

fun convertMillisecondsToMinute(ms: Long) = TimeUnit.MILLISECONDS.toMinutes(ms)

fun convertTimeToString(time: Long): String {
    val hour = convertMillisecondsToMinute(time) / 60
    val min = convertMillisecondsToMinute(time) % 60
    var result = ""
    if (hour > 0) result = result.plus("$hour" + "시간")
    if (min >= 0) result = result.plus("$min" + "분")
    return result
}
