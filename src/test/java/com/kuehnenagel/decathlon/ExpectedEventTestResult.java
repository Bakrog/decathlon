package com.kuehnenagel.decathlon;

import com.kuehnenagel.decathlon.event.Event;
import com.kuehnenagel.decathlon.event.field.*;
import com.kuehnenagel.decathlon.event.track.Run100Meters;
import com.kuehnenagel.decathlon.event.track.Run110MetersHurdles;
import com.kuehnenagel.decathlon.event.track.Run1500Meters;
import com.kuehnenagel.decathlon.event.track.Run400Meters;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class ExpectedEventTestResult {

    public static final String DEFAULT_ATHLETE_NAME = "RANDOM_NAME_HERE";

    private final Event event;
    private final int score;

    private ExpectedEventTestResult(Event event, int score) {
        this.event = event;
        this.score = score;
    }

    public Event getEvent() {
        return event;
    }

    public int getScore() {
        return score;
    }

    public static class Builder {

        private final List<Event> events = new ArrayList<>();
        private final AtomicInteger expectedScore = new AtomicInteger();
        private final int limitedNumberOfEvents;

        private final ExpectedEventTestResult[] knowTrackResults = new ExpectedEventTestResult[]{
                // 100 Meters
                new ExpectedEventTestResult(
                        new Run100Meters("10.395"), 1000
                ),
                new ExpectedEventTestResult(
                        new Run100Meters("10.827"), 900
                ),
                new ExpectedEventTestResult(
                        new Run100Meters("11.278"), 800
                ),
                new ExpectedEventTestResult(
                        new Run100Meters("11.756"), 700
                ),
                // 110 Meters Hurdles
                new ExpectedEventTestResult(
                        new Run110MetersHurdles("13.8"), 1000
                ),
                new ExpectedEventTestResult(
                        new Run110MetersHurdles("14.59"), 900
                ),
                new ExpectedEventTestResult(
                        new Run110MetersHurdles("15.419"), 800
                ),
                new ExpectedEventTestResult(
                        new Run110MetersHurdles("16.29"), 700
                ),
                // 400 Meters
                new ExpectedEventTestResult(
                        new Run400Meters("46.17"), 1000
                ),
                new ExpectedEventTestResult(
                        new Run400Meters("48.19"), 900
                ),
                new ExpectedEventTestResult(
                        new Run400Meters("50.32"), 800
                ),
                new ExpectedEventTestResult(
                        new Run400Meters("52.58"), 700
                ),
                // 1500 Meters
                new ExpectedEventTestResult(
                        new Run1500Meters("3:53.79"), 1000
                ),
                new ExpectedEventTestResult(
                        new Run1500Meters("4:07.42"), 900
                ),
                new ExpectedEventTestResult(
                        new Run1500Meters("4:21.77"), 800
                ),
                new ExpectedEventTestResult(
                        new Run1500Meters("4:36.96"), 700
                )
        };

        private final ExpectedEventTestResult[] knowFieldResults = new ExpectedEventTestResult[]{
                // Long Jump
                new ExpectedEventTestResult(
                        new LongJump("7.76"), 1000
                ),
                new ExpectedEventTestResult(
                        new LongJump("7.36"), 900
                ),
                new ExpectedEventTestResult(
                        new LongJump("6.94"), 799
                ),
                new ExpectedEventTestResult(
                        new LongJump("6.51"), 700
                ),
                // Shot Put
                new ExpectedEventTestResult(
                        new ShotPut("18.4"), 1000
                ),
                new ExpectedEventTestResult(
                        new ShotPut("16.79"), 900
                ),
                new ExpectedEventTestResult(
                        new ShotPut("15.16"), 800
                ),
                new ExpectedEventTestResult(
                        new ShotPut("13.53"), 700
                ),
                // High Jump
                new ExpectedEventTestResult(
                        new HighJump("2.20"), 992
                ),
                new ExpectedEventTestResult(
                        new HighJump("2.10"), 896
                ),
                new ExpectedEventTestResult(
                        new HighJump("1.99"), 794
                ),
                new ExpectedEventTestResult(
                        new HighJump("1.88"), 696
                ),
                // Discus Throw
                new ExpectedEventTestResult(
                        new DiscusThrow("56.17"), 1000
                ),
                new ExpectedEventTestResult(
                        new DiscusThrow("51.4"), 900
                ),
                new ExpectedEventTestResult(
                        new DiscusThrow("46.59"), 800
                ),
                new ExpectedEventTestResult(
                        new DiscusThrow("41.72"), 700
                ),
                // Pole Vault
                new ExpectedEventTestResult(
                        new PoleVault("5.28"), 998
                ),
                new ExpectedEventTestResult(
                        new PoleVault("4.96"), 898
                ),
                new ExpectedEventTestResult(
                        new PoleVault("4.63"), 799
                ),
                new ExpectedEventTestResult(
                        new PoleVault("4.29"), 699
                ),
                // Javelin Throw
                new ExpectedEventTestResult(
                        new JavelinThrow("77.19"), 1000
                ),
                new ExpectedEventTestResult(
                        new JavelinThrow("70.67"), 900
                ),
                new ExpectedEventTestResult(
                        new JavelinThrow("64.09"), 800
                ),
                new ExpectedEventTestResult(
                        new JavelinThrow("57.45"), 700
                )
        };

        public Builder() {
            this.limitedNumberOfEvents = new Random().nextInt(1000);
        }

        public Builder(int limitedNumberOfEvents) {
            this.limitedNumberOfEvents = limitedNumberOfEvents;
        }

        public Builder withRandomTrackEvents() {
            generateEvents(knowTrackResults);
            return this;
        }

        public Builder withRandomFieldEvents() {
            generateEvents(knowFieldResults);
            return this;
        }

        public Builder withRandomEvents() {
            generateEvents(
                    Stream.concat(Arrays.stream(knowTrackResults), Arrays.stream(knowFieldResults))
                            .toArray(ExpectedEventTestResult[]::new)
            );
            return this;
        }

        public Map.Entry<Integer, List<Event>> build() {
            return new AbstractMap.SimpleEntry<>(expectedScore.get(), events);
        }

        private void generateEvents(ExpectedEventTestResult[] knowTrackResults) {
            new Random()
                    .ints(limitedNumberOfEvents, 0, knowTrackResults.length - 1)
                    .mapToObj(trackIndex -> knowTrackResults[trackIndex])
                    .iterator()
                    .forEachRemaining(expectedResult -> {
                        expectedScore.addAndGet(expectedResult.getScore());
                        events.add(expectedResult.getEvent());
                    });
        }
    }
}
