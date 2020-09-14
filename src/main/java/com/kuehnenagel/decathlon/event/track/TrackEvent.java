package com.kuehnenagel.decathlon.event.track;

import com.kuehnenagel.decathlon.event.Event;

public abstract class TrackEvent implements Event {

    protected final double time;

    public TrackEvent(String time) {
        this.time = Double.parseDouble(time);
    }

    @Override
    public String value() {
        return String.valueOf(time);
    }

    protected int calculateScore(double A, double B, double C) {
        return (int) (A * Math.pow(Math.abs(B - time), C));
    }
}
