package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;


public class JavelinThrow extends FieldEvent {

    public JavelinThrow(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "Javelin throw";
    private static final BigDecimal A = new BigDecimal("10.14");
    private static final BigDecimal B = new BigDecimal("7");
    private static final BigDecimal C = new BigDecimal("1.08");

    @Override
    public String name() {
        return NAME;
    }
}
