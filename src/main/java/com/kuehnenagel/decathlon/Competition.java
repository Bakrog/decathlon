package com.kuehnenagel.decathlon;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Competition {
    private final Set<Athlete> athletes = new TreeSet<>();

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Map<String, List<Athlete>> getPodium() {
        AtomicInteger rank = new AtomicInteger(1);
        return athletes.stream()
                .collect(Collectors.groupingBy(Athlete::points))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .map(entry -> new AbstractMap.SimpleEntry<>(formatAthletePosition(rank, entry.getValue()), entry.getValue()))
                .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue
                ));
    }

    public String getAthletePosition(Athlete athlete) {
        return getPodium()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(athlete))
                .findFirst()
                .get()
                .getKey();
    }

    private String formatAthletePosition(AtomicInteger rank, List<Athlete> athletes) {
        Integer currentRank = rank.getAndIncrement();
        Integer bounding = currentRank + (athletes.size() - 1);
        if (!currentRank.equals(bounding)) {
            rank.set(bounding + 1);
            return String.format("%d-%d", currentRank, bounding);
        }
        return currentRank.toString();
    }
}
