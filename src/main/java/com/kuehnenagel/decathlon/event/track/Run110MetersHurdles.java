package com.kuehnenagel.decathlon.event.track;

public class Run110MetersHurdles extends TrackEvent {

    public Run110MetersHurdles(String time) {
        super(time);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "110 metres hurdles";
    private static final double A = 5.74352;
    private static final double B = 28.5;
    private static final double C = 1.92;

    @Override
    public String name() {
        return NAME;
    }
}
