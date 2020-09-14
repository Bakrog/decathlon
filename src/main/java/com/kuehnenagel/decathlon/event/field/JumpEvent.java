package com.kuehnenagel.decathlon.event.field;

import java.math.BigDecimal;

public abstract class JumpEvent extends FieldEvent {
    public JumpEvent(String meters) {
        super(
                convertToCentimeters(meters)
        );
    }

    public static BigDecimal convertToCentimeters(String meters) {
        return new BigDecimal(meters).multiply(new BigDecimal("100"));
    }
}
