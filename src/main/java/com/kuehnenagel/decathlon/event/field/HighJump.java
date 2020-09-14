package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;

public class HighJump extends JumpEvent {

    public HighJump(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "High jump";
    private static final BigDecimal A = new BigDecimal("0.8465");
    private static final BigDecimal B = new BigDecimal("75.00");
    private static final BigDecimal C = new BigDecimal("1.42");

    @Override
    public String name() {
        return NAME;
    }
}
