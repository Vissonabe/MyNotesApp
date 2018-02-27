package com.example.viswanathankp.mynotes.DB;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
