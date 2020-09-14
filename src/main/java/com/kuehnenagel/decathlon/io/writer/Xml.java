package com.kuehnenagel.decathlon.io.writer;

import com.kuehnenagel.decathlon.Athlete;
import com.kuehnenagel.decathlon.Competition;
import com.kuehnenagel.decathlon.event.Event;
import com.kuehnenagel.decathlon.io.Writer;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Xml extends Writer {

    private static final String EVENT_TEMPLATE_XML = "<event name=\"%s\">%s</event>";
    private static final String ATHLETE_TEMPLATE_XML = "<athlete name=\"%s\" position=\"%s\"><events>%s</events></athlete>";
    private static final String DECATHLON_TEMPLATE_XML = "<decathlon><athletes>%s</athletes></decathlon>";
    private Competition competition;

    @Override
    public void writeCompetition(Competition competition) {
        this.competition = competition;
        try {
            write(printCompetition());
            flush();
        } catch (IOException e) {
            throw new StandardOutputNotAvailable(e);
        }
    }

    private String printCompetition() {
        return String.format(DECATHLON_TEMPLATE_XML, printAthletes(competition.getAthletes()));
    }

    private String printAthletes(Set<Athlete> athletes) {
        return athletes.stream()
                .map(this::printAthlete)
                .collect(Collectors.joining(""));
    }

    private String printAthlete(Athlete athlete) {
        return String.format(
                ATHLETE_TEMPLATE_XML,
                athlete.getName(),
                competition.getAthletePosition(athlete),
                printEvents(athlete.getParticipatedEvents())
        );
    }

    private String printEvents(List<Event> participatedEvents) {
        return participatedEvents.stream()
                .map(this::printEvent)
                .collect(Collectors.joining(""));
    }

    private String printEvent(Event event) {
        return String.format(EVENT_TEMPLATE_XML, event.name(), event.value());
    }

    private static class StandardOutputNotAvailable extends RuntimeException {
        public StandardOutputNotAvailable(Throwable throwable) {
            super("StdOut is not available!", throwable);
        }
    }
}
