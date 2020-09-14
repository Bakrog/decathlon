package com.kuehnenagel.decathlon.event.track;

public class Run400Meters extends TrackEvent {

    public Run400Meters(String time) {
        super(time);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "400 metres";
    private static final double A = 1.53775;
    private static final double B = 82;
    private static final double C = 1.81;

    @Override
    public String name() {
        return NAME;
    }
}
