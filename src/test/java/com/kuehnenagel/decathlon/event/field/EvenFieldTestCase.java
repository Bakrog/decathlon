package com.kuehnenagel.decathlon.event.field;

import com.kuehnenagel.decathlon.Athlete;
import com.kuehnenagel.decathlon.ExpectedEventTestResult;
import com.kuehnenagel.decathlon.event.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.kuehnenagel.decathlon.ExpectedEventTestResult.DEFAULT_ATHLETE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("An athlete running in a field event")
public class EvenFieldTestCase {

    @DisplayName("can run in many events and get the correct number of points")
    @Test
    public void run() {
        Athlete athlete = new Athlete(DEFAULT_ATHLETE_NAME);
        Map.Entry<Integer, List<Event>> test = new ExpectedEventTestResult.Builder()
                .withRandomFieldEvents()
                .build();
        test.getValue().forEach(athlete::participate);
        assertEquals(test.getKey(), athlete.points());
    }
}
