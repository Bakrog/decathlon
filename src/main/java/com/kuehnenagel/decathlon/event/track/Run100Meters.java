package com.kuehnenagel.decathlon.event.track;

public class Run100Meters extends TrackEvent {

    public Run100Meters(String time) {
        super(time);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "100 metres";
    private static final double A = 25.4347;
    private static final double B = 18;
    private static final double C = 1.81;

    @Override
    public String name() {
        return NAME;
    }
}
