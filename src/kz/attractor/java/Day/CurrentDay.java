package kz.attractor.java.Day;

import java.lang.reflect.Parameter;
import java.util.Calendar;

public class CurrentDay {
    Calendar calendar = Calendar.getInstance();
    private int dayOfMonth;
    private int dayOfWeek;

    public CurrentDay(int dayOfMonth, int dayOfWeek) {
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        this.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    }


    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}

