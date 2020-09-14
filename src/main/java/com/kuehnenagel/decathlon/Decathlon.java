package com.kuehnenagel.decathlon;

import com.kuehnenagel.decathlon.io.Reader;
import com.kuehnenagel.decathlon.io.Writer;

public class Decathlon extends Competition {

    private final Reader reader;
    private final Writer writer;

    public Decathlon(String inputFileType, String outputFileType) {
        this.reader = Reader.getReader(inputFileType);
        this.writer = Writer.getWriter(outputFileType);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Application expects two arguments: INPUT_FILE_TYPE and OUTPUT_FILE_TYPE. Example: java -jar kn-se-homework-1.0.0.jar csv xml");
            throw new RuntimeException("Invalid number of Arguments");
        }
        Decathlon decathlon = new Decathlon(args[0], args[1]);
        decathlon.readResults();
        decathlon.writeResults();
    }

    public void readResults() {
        Athlete athlete = reader.readAthlete();
        while (athlete != null) {
            addAthlete(athlete);
            athlete = reader.readAthlete();
        }
    }

    public void writeResults() {
        writer.writeCompetition(this);
    }
}
