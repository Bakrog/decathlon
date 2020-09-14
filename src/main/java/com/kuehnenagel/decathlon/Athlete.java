package com.kuehnenagel.decathlon;

import com.kuehnenagel.decathlon.event.Event;

import java.util.ArrayList;
import java.util.List;

public class Athlete implements Comparable<Athlete> {
    private final String name;
    private int score = 0;
    private final List<Event> participatedEvents = new ArrayList<>();

    public Athlete(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer points() {
        return score;
    }

    public void participate(Event event) {
        participatedEvents.add(event);
        this.score += event.points();
    }

    public List<Event> getParticipatedEvents() {
        return participatedEvents;
    }

    @Override
    public int compareTo(Athlete other) {
        return other.points().compareTo(points());
    }
}
