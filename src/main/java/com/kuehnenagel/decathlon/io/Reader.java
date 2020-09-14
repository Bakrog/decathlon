package com.kuehnenagel.decathlon.io;

import com.kuehnenagel.decathlon.Athlete;
import com.kuehnenagel.decathlon.event.Event;
import com.kuehnenagel.decathlon.event.field.*;
import com.kuehnenagel.decathlon.event.track.Run100Meters;
import com.kuehnenagel.decathlon.event.track.Run110MetersHurdles;
import com.kuehnenagel.decathlon.event.track.Run1500Meters;
import com.kuehnenagel.decathlon.event.track.Run400Meters;
import com.kuehnenagel.decathlon.io.reader.Csv;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Reader extends BufferedReader {

    @SuppressWarnings("unchecked")
    protected static final Class<? extends Event>[] eventOrder = new Class[]{
            Run100Meters.class,
            LongJump.class,
            ShotPut.class,
            HighJump.class,
            Run400Meters.class,
            Run110MetersHurdles.class,
            DiscusThrow.class,
            PoleVault.class,
            JavelinThrow.class,
            Run1500Meters.class
    };

    protected Reader() {
        super(new InputStreamReader(System.in));
    }

    public static Reader getReader(String type) {
        //noinspection SwitchStatementWithTooFewBranches
        switch (type.toLowerCase()) {
            case "csv":
                return new Csv();
            default:
                throw new ReaderNotImplemented(type);
        }
    }

    public abstract Athlete readAthlete();

    private static class ReaderNotImplemented extends RuntimeException {
        public ReaderNotImplemented(String type) {
            super(String.format("The input file format '%s' has no implementation on the system", type));
        }
    }
}
