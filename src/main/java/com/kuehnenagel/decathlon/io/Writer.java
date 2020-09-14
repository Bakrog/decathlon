package com.kuehnenagel.decathlon.io;

import com.kuehnenagel.decathlon.Competition;
import com.kuehnenagel.decathlon.io.writer.Xml;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public abstract class Writer extends BufferedWriter {

    public Writer() {
        super(new OutputStreamWriter(System.out));
    }

    public static Writer getWriter(String type) {
        //noinspection SwitchStatementWithTooFewBranches
        switch (type.toLowerCase()) {
            case "xml":
                return new Xml();
            default:
                throw new WriterNotImplemented(type);
        }
    }

    public abstract void writeCompetition(Competition competition);

    private static class WriterNotImplemented extends RuntimeException {
        public WriterNotImplemented(String type) {
            super(String.format("The output file format '%s' has no implementation on the system", type));
        }
    }
}
