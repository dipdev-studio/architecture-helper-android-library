package studio.dipdev.ahal.utils

import java.text.SimpleDateFormat
import java.util.*

class AhalDateTimeHelper {

    fun format24Hour(time: Long, duration: Long, nextDay: String): String {
        val calendar1 = Calendar.getInstance()
        calendar1.timeInMillis = time
        val calendar2 = Calendar.getInstance()
        calendar2.timeInMillis = time + duration

        var result = String.format(Locale.getDefault(), "%02d:%02d - %02d:%02d",
                calendar1.get(Calendar.HOUR_OF_DAY),
                calendar1.get(Calendar.MINUTE),
                calendar2.get(Calendar.HOUR_OF_DAY),
                calendar2.get(Calendar.MINUTE))

        if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2
                        .get(Calendar.DAY_OF_MONTH)) {
            result = String.format("%s(%s)", result, nextDay)
        }

        return result
    }

    fun format12Hour(time: Long, duration: Long, nextDay: String): String {

        try {
            val date1 = Date(time)
            var sdf = SimpleDateFormat("hh:mm a", Companion.sLocale)
            val startTime = sdf.format(date1)

            val date2 = Date(time + duration)
            sdf = SimpleDateFormat("hh:mm a", Companion.sLocale)
            val endTime = sdf.format(date2)

            val calendar1 = Calendar.getInstance()
            calendar1.time = date1
            val calendar2 = Calendar.getInstance()
            calendar2.time = date2

            return if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2
                            .get(Calendar.DAY_OF_MONTH)) {
                String
                        .format("%s - %s(%s)", startTime, endTime, nextDay)
            } else {
                String.format("%s - %s", startTime, endTime)
            }
        } catch (e: Exception) {
            return ""
        }

    }

    fun formatDuration(duration: Long, hourSign: String, minuteSign: String): String {
        var dur = duration
        dur /= 1000L
        val hour = (dur / 3600L).toInt()
        val minute = (dur % 3600L / 60).toInt()
        return String.format(Locale.getDefault(), "%d%s %d%s", hour, hourSign, minute, minuteSign)
    }

    companion object {
        private val sLocale = Locale.ENGLISH
    }


}