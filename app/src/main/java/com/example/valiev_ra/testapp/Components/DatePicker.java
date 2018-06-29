package com.example.valiev_ra.testapp.Components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import java.util.Calendar;
import java.util.Date;

public class DatePicker {
    public static void showDatePicker(Context context, Date date, final OnSelectedDate onSelectedDate) {
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd;
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                (datePickerDialog, i, i1, i2) -> {
                    now.set(i, i1, i2);

                    onSelectedDate.selectDate(now.getTime());
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setThemeDark(true);
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.setAccentColor(Color.parseColor("#767A86"));
        dpd.show(((Activity) context).getFragmentManager(), "Datepickerdialog");
    }

    public interface OnSelectedDate {
        void selectDate(Date date);
    }

}
