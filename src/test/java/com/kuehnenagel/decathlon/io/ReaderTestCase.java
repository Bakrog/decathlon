package com.kuehnenagel.decathlon.io;

import com.kuehnenagel.decathlon.Athlete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("A athlete in a competition should be read")
public class ReaderTestCase {

    private final Random random = new Random();
    private String athleteName;
    private String[] eventsResults;

    @BeforeEach
    public void setup() {
        generateAthleteName();
        generateAthleteDecathlonResults();
    }

    @DisplayName("by csv file")
    @Test
    public void csv() {
        prepareStdInForCsvLine();
        Reader reader = Reader.getReader("csv");
        Athlete athlete = reader.readAthlete();
        assertEquals(athleteName, athlete.getName());
        assertNull(reader.readAthlete());
    }

    private void generateAthleteName() {
        athleteName = "" + random.nextInt();
    }

    private void generateAthleteDecathlonResults() {
        eventsResults = new String[10];
        AtomicInteger index = new AtomicInteger();
        random.doubles(10)
                .forEach(value -> eventsResults[index.getAndIncrement()] = "" + value);
    }

    private void prepareStdInForCsvLine() {
        System.setIn(new ByteArrayInputStream(generateCsvLine().getBytes()));
    }

    private String generateCsvLine() {
        return String.format("%s;%s", athleteName, String.join(";", eventsResults));
    }
}
