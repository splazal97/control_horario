package com.example.control_horario.model;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Converters {
    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        if (dateString == null) return null;

        return LocalDateTime.parse(dateString);

    }

    @TypeConverter
    public static String toDateString(LocalDateTime date) {
        if (date == null) return null;
        return date.toString();
    }

}