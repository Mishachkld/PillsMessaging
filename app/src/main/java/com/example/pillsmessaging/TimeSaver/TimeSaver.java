package com.example.pillsmessaging.TimeSaver;

import java.sql.Time;

public class TimeSaver {
    private int hours;
    private int minutes;
    private int globalTime;

    public TimeSaver(int hours, int minutes, int globalTime){
        this.hours = hours;
        this.minutes = minutes;
        this.globalTime = globalTime;
    }


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
