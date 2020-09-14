package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;


public class ShotPut extends FieldEvent {

    public ShotPut(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "Shot put";
    private static final BigDecimal A = new BigDecimal("51.39");
    private static final BigDecimal B = new BigDecimal("1.5");
    private static final BigDecimal C = new BigDecimal("1.05");

    @Override
    public String name() {
        return NAME;
    }
}
