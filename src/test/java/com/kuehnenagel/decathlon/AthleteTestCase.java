package com.kuehnenagel.decathlon;

import com.kuehnenagel.decathlon.event.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.util.collections.Sets.newSet;

@DisplayName("An athlete")
public class AthleteTestCase {

    private final Random random = new Random();

    @DisplayName("has a name")
    @Test
    public void hasName() {
        String expectedName = getNewAthleteName();
        Athlete athlete = createAthleteWithName(expectedName);
        assertEquals(expectedName, athlete.getName());
    }

    @DisplayName("can participate in a competition")
    @Test
    public void canParticipateCompetition() {
        String expectedName = getNewAthleteName();
        Athlete athlete = createAthleteWithName(expectedName);
        Competition olympics = new Competition();
        olympics.addAthlete(athlete);
        assertTrue(olympics.getAthletes().contains(athlete));
    }

    @DisplayName("has a position in the podium")
    @Test
    public void podium() {
        Athlete athlete1 = mock(Athlete.class);
        when(athlete1.points()).thenReturn(0);
        when(athlete1.getName()).thenReturn(getNewAthleteName());

        Athlete athlete2_3 = mock(Athlete.class);
        Athlete athlete3_2 = mock(Athlete.class);
        when(athlete2_3.points()).thenReturn(2);
        when(athlete3_2.points()).thenReturn(2);
        when(athlete2_3.getName()).thenReturn(getNewAthleteName());
        when(athlete3_2.getName()).thenReturn(getNewAthleteName());

        Athlete athlete4 = mock(Athlete.class);
        when(athlete4.points()).thenReturn(4);
        when(athlete4.getName()).thenReturn(getNewAthleteName());

        Athlete athlete5_6 = mock(Athlete.class);
        Athlete athlete6_5 = mock(Athlete.class);
        when(athlete5_6.points()).thenReturn(5);
        when(athlete6_5.points()).thenReturn(5);
        when(athlete5_6.getName()).thenReturn(getNewAthleteName());
        when(athlete6_5.getName()).thenReturn(getNewAthleteName());

        Athlete athlete7 = mock(Athlete.class);
        when(athlete7.points()).thenReturn(7);
        when(athlete7.getName()).thenReturn(getNewAthleteName());

        Competition olympics = new Competition();
        olympics.addAthlete(athlete1);
        olympics.addAthlete(athlete2_3);
        olympics.addAthlete(athlete3_2);
        olympics.addAthlete(athlete4);
        olympics.addAthlete(athlete5_6);
        olympics.addAthlete(athlete6_5);
        olympics.addAthlete(athlete7);

        Map<String, List<Athlete>> expectedPodium = new HashMap<String, List<Athlete>>() {{
            put("1", new ArrayList<>(newSet(athlete7)));
            put("2-3", new ArrayList<>(newSet(athlete5_6, athlete6_5)));
            put("4", new ArrayList<>(newSet(athlete4)));
            put("5-6", new ArrayList<>(newSet(athlete2_3, athlete3_2)));
            put("7", new ArrayList<>(newSet(athlete1)));
        }};
        assertEquals(expectedPodium, olympics.getPodium());
    }

    @DisplayName("can see all events it participated")
    @Test
    public void events() {
        Map.Entry<Integer, List<Event>> test = new ExpectedEventTestResult.Builder().withRandomEvents().build();
        String expectedName = getNewAthleteName();
        Athlete athlete = createAthleteWithName(expectedName);
        test.getValue().forEach(athlete::participate);
        assertEquals(test.getValue(), athlete.getParticipatedEvents());
    }

    private String getNewAthleteName() {
        return random.nextInt() + "";
    }

    private Athlete createAthleteWithName(String name) {
        return new Athlete(name);
    }
}
