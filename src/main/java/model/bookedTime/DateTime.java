package model.bookedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;



    public DateTime(int day , int month, int year, int hour, int minute) {
        this.day = day;
        this.month=month;
        this.year=year;
        this.hour = hour;
        this.minute = minute;


    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    public boolean isBefore (DateTime other) {
        if (this.year < other.getYear()) {
            return true;
        } else if (this.year == other.getYear() && this.month < other.getMonth()) {
            return true;
        } else if (this.year == other.getYear() && this.month == other.getMonth() && this.day < other.getDay()){
            return true;
        } else if(this.year == other.getYear() && this.month == other.getMonth() && this.day == other.getDay()&&this.hour<other.hour){
            return true;
        } else if(this.year == other.getYear() && this.month == other.getMonth() && this.day == other.getDay()
                &&this.hour==other.hour&&this.minute<other.minute){
            return true;
        } else return false;
    }

    public boolean isAfter (DateTime other) {
        if (this.year > other.getYear()) {
            return true;
        } else if (this.year == other.getYear() && this.month > other.getMonth()) {
            return true;
        } else if (this.year == other.getYear() && this.month == other.getMonth() && this.day > other.getDay()){
            return true;
        } else if(this.year == other.getYear() && this.month == other.getMonth() && this.day == other.getDay()&&this.hour>other.hour){
            return true;
        } else if(this.year == other.getYear() && this.month == other.getMonth() && this.day == other.getDay()&&this.hour==other.hour&&this.minute>other.minute){
            return true;
        } else return false;
    }
    public long getDatTimeInMillieSecond() throws Exception{
        String myDate = year+"/"+month+"/"+day+" "+hour+":"+minute+":00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       try {
           Date date = sdf.parse(myDate);
           long millis = date.getTime();
           return millis;
       }catch (Exception e){
           throw e;
       }
    }


    public String getTime() {
        return hour + ":" + minute;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof DateTime))
            return false;
        DateTime other= (DateTime) obj;
        return day==other.day && month==other.month && year==other.year && hour==other.hour && minute== other.minute;
    }



    public String toString() {
        return "Date: " +"day= " + day +", month= "+month+", year= "+year +", hour= " + hour + ", minute= " + minute;
    }

    public static void main(String[] args) {
       DateTime a = new  DateTime(10,12,2021,10,30);
        DateTime d = new  DateTime(10,12,2021,10,31);
       try {
           System.out.println(a.getDatTimeInMillieSecond());
           System.out.println(d.getDatTimeInMillieSecond());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
}