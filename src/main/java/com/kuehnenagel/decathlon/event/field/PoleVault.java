package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;


public class PoleVault extends JumpEvent {

    public PoleVault(String meters) {
        super(meters);
    }

    @Override
    public int points() {
        return calculateScore(A, B, C);
    }

    private static final String NAME = "Pole vault";
    private static final BigDecimal A = new BigDecimal("0.2797");
    private static final BigDecimal B = new BigDecimal("100");
    private static final BigDecimal C = new BigDecimal("1.35");

    @Override
    public String name() {
        return NAME;
    }
}
