package com.kuehnenagel.decathlon.event.field;

import com.kuehnenagel.decathlon.event.Event;

import java.math.BigDecimal;

public abstract class FieldEvent implements Event {

    protected final BigDecimal meters;

    public FieldEvent(BigDecimal meters) {
        this.meters = meters;
    }

    public FieldEvent(String meters) {
        this.meters = new BigDecimal(meters);
    }

    protected int calculateScore(BigDecimal A, BigDecimal B, BigDecimal C) {
        return A.multiply(
                BigDecimal.valueOf(
                        Math.pow(
                                (meters.subtract(B).abs().doubleValue()),
                                C.doubleValue()
                        )
                )
        ).intValue();
    }

    @Override
    public String value() {
        return meters.toPlainString();
    }
}
