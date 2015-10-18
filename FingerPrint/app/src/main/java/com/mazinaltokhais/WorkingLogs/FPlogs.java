package com.mazinaltokhais.WorkingLogs;

/**
 * Created by mazoo_000 on 08/09/2015.
 */
public class FPlogs {
    private String time;
    private String day;
    private String month;
    private String year;
    private int type;
    private int id;

    public FPlogs() {
    }

    public FPlogs(String date, String time, int type) {
        this.setTime(time);
        this.setMonth(date);
        this.setType(type);
    }

    public FPlogs(  String day,String month,String year, String time, int type ) {
        this.setTime(time);
        this.setType(type);
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);

    }
    public FPlogs(int id,  String day,String month,String year, String time, int type ) {
        this.setId(id);
        this.setTime(time);
        this.setType(type);
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   /* public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
*/
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
