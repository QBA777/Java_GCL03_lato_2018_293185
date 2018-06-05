package sample;

public class Alarm {

    private int hour;
    private int minute;

    public Alarm(){
        this.hour=0;
        this.minute=0;
    }

    public Alarm(int hour, int minute){
        this.hour=hour;
        this.minute=minute;
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
}