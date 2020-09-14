package com.kuehnenagel.decathlon.io;

import com.kuehnenagel.decathlon.Athlete;
import com.kuehnenagel.decathlon.Competition;
import com.kuehnenagel.decathlon.event.track.Run100Meters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.kuehnenagel.decathlon.ExpectedEventTestResult.DEFAULT_ATHLETE_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("The output of the program should be")
public class WriteTestCase {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }

    @Nested
    @DisplayName("be in XML")
    class XML {

        @DisplayName("and has an athlete tag")
        @Test
        public void xmlAthlete() {
            Athlete athlete = new Athlete(DEFAULT_ATHLETE_NAME);
            Competition olympics = new Competition();
            olympics.addAthlete(athlete);
            athlete.participate(new Run100Meters("10.395"));
            Writer writer = Writer.getWriter("xml");
            writer.writeCompetition(olympics);
            assertTrue(
                    outContent.toString()
                            .contains(
                                    String.format(
                                            "<athlete name=\"%s\" position=\"1\"><events><event name=\"100 metres\">10.395</event></events></athlete>",
                                            DEFAULT_ATHLETE_NAME
                                    )
                            )
            );
        }
    }
}
