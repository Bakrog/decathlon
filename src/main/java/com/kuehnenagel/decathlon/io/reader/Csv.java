package com.kuehnenagel.decathlon.io.reader;

import com.kuehnenagel.decathlon.Athlete;
import com.kuehnenagel.decathlon.event.Event;
import com.kuehnenagel.decathlon.io.Reader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Csv extends Reader {
    public Csv() {
        super();
    }

    @Override
    public Athlete readAthlete() {
        AtomicReference<Athlete> athlete = new AtomicReference<>(null);
        try {
            String[] values = readLineWithValidation();
            if (values == null) return null;
            AtomicInteger index = new AtomicInteger();
            Arrays.stream(values).forEachOrdered(value -> {
                int currentIndex = index.getAndIncrement();
                if (currentIndex == 0) {
                    athlete.set(new Athlete(value));
                } else {
                    try {
                        athlete.get().participate(eventOrder[currentIndex - 1].getConstructor(String.class).newInstance(value));
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
                        throw new CantCreateEventInstance(eventOrder[currentIndex - 1], exception);
                    }
                }
            });
        } catch (IOException exception) {
            throw new StandardInputNotAvailable(exception);
        }
        return athlete.get();
    }

    private String[] readLineWithValidation() throws IOException {
        String line = readLine();
        if (line == null) {
            return null;
        }
        String[] values = line.split(";");
        if (values.length < 11) {
            return null;
        }
        return values;
    }

    private static class StandardInputNotAvailable extends RuntimeException {
        public StandardInputNotAvailable(Throwable throwable) {
            super("StdIn is not available!", throwable);
        }
    }

    private static class CantCreateEventInstance extends RuntimeException {
        public CantCreateEventInstance(Class<? extends Event> clasz, Throwable throwable) {
            super(String.format("Can't creat instance for the event: %s", clasz.getName()), throwable);
        }
    }
}
