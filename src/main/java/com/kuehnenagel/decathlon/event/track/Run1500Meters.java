package com.kuehnenagel.decathlon.event.track;

import java.util.Arrays;

public class Run1500Meters extends TrackEvent {

    public Run1500Meters(String time) {
        super(convertTimeStringToDouble(time));
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "1500 metres";
    private static final double A = 0.03768;
    private static final double B = 480;
    private static final double C = 1.85;

    @Override
    public String name() {
        return NAME;
    }

    private static String convertTimeStringToDouble(String time) {
        return String.valueOf(
                Arrays.stream(time.split(":"))
                        .mapToDouble(Double::parseDouble)
                        .reduce((left, right) -> (left * 60d) + right)
                        .orElse(0.0d)
        );
    }
}
