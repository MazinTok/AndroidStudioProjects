package com.mazinaltokhais.WorkingLogs;

/**
 * Created by mazoo_000 on 08/09/2015.
 */
public class FPlogsResult {
    private String timeIN;
    private String day;
    private String month;
    private String year;
    private String timeOut;


    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeIN() {
        return timeIN;
    }

    public void setTimeIN(String timeIN) {
        this.timeIN = timeIN;
    }


    public FPlogsResult() {
    }


    public FPlogsResult(String day, String month, String year, String time, String type) {
        this.setTimeIN(time);
        this.setTimeOut(type);
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
