package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;


public class DiscusThrow extends FieldEvent {

    public DiscusThrow(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "Discus throw";
    private static final BigDecimal A = new BigDecimal("12.91");
    private static final BigDecimal B = new BigDecimal("4");
    private static final BigDecimal C = new BigDecimal("1.1");

    @Override
    public String name() {
        return NAME;
    }
}
