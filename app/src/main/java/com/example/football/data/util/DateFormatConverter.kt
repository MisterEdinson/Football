package com.example.football.data.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatConverter {
    fun dateConverter(utcDateTime: String?):String{
        //val pattern = "dd.MM.yyyy HH:mm:ss"
//        val pattern = "HH:mm"
//        val locale = Locale("kk", "KZ")
//        val timeZone = TimeZone.getTimeZone("Asia/Aqtobe")
//        val dateFormat = SimpleDateFormat(pattern, locale)
//        dateFormat.timeZone = timeZone
//
//        val date = Date()
//        val formattedDate = dateFormat.format(date)
//        return formattedDate
        // Создание объекта даты и времени в формате UTC
        val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val utcDate = LocalDateTime.parse(utcDateTime, utcFormatter)
        val utcZone = ZoneId.of("UTC")
        val utcZoned = ZonedDateTime.of(utcDate, utcZone)

        // Преобразование даты и времени в часовой пояс +5
        val kazakhstanZone = ZoneId.of("Asia/Almaty")
        val kazakhstanZoned = utcZoned.withZoneSameInstant(kazakhstanZone)

        // Форматирование даты и времени в строку "HH:mm"
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
        return kazakhstanZoned.format(formatter)
    }
}