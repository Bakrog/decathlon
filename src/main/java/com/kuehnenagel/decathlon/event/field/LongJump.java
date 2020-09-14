package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;


public class LongJump extends JumpEvent {

    public LongJump(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "Long jump";
    private static final BigDecimal A = new BigDecimal("0.14354");
    private static final BigDecimal B = new BigDecimal("220");
    private static final BigDecimal C = new BigDecimal("1.4");

    @Override
    public String name() {
        return NAME;
    }
}
