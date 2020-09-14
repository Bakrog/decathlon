# Decathlon Homework
The decathlon is a combined event in athletics consisting of ten track and field events. The word "decathlon" was formed, in analogy to the word "pentathlon", from Greek δέκα (déka, meaning "ten") and ἄθλος (áthlos, or ἄθλον, áthlon, meaning "contest" or “prize”). Events are held over two consecutive days and the winners are determined by the combined performance in all. Performance is judged on a points system in each event, not by the position achieved. The decathlon is contested mainly by male athletes, while female athletes typically compete in the heptathlon.

## Index

## Format of the Decathlon

### Day 1
* 100 metres
* Long jump
* Shot put
* High jump
* 400 metres
### Day 2
* 110 metres hurdles
* Discus throw
* Pole vault
* Javelin throw
* 1500 metres

### Points
The 2001 IAAF points tables use the following formulae:

Points = INT(A(B — P)C) for track events (faster time produces a higher score)

Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)

A, B and C are parameters that vary by discipline, as shown in the table bellow, while P is the performance by the athlete, measured in seconds (running), metres (throwing), or centimetres (jumping).

| Event              |    A    |   B  |   C  |
|--------------------|:-------:|:----:|:----:|
| 100 metres         | 25.4347 | 18   | 1.81 |
| Long jump          | 0.14354 | 220  | 1.4  |
| Shot put           | 51.39   | 1.5  | 1.05 |
| High jump          | 0.8465  | 75   | 1.42 |
| 400 metres         | 1.53775 | 82   | 1.81 |
| 110 metres hurdles | 5.74352 | 28.5 | 1.92 |
| Discus throw       | 12.91   | 4    | 1.1  |
| Pole vault         | 0.2797  | 100  | 1.35 |
| Javelin throw      | 10.14   | 7    | 1.08 |
| 1500 metres        | 0.03768 | 480  | 1.85 |

## Project

### Build

You can build the program using maven command:
```
mvn package
```
After you can find the generated jar at `target/kn-se-homework-1.0.0.jar`

### Tests

You can run the tests of the program using maven command:
```
mvn test
```
### Run

You can run the program after building using the follow command:
```
java -jar target/kn-se-homework-1.0.0.jar ${input_format} ${output_format} < ${input_file} > ${output_file}
```
Currently, the variables accept the following values:
* input_format:
  * csv
* output_format:
  * xml
* input_file: Any absolute path to a file in your filesystem
* output_file: Any absolute path in your filesystem

Example of command: `java -jar target/kn-se-homework-1.0.0.jar csv xml < test.csv > result.xml`

### Extending
For events just implement TrackEvent or FieldEvent.
For files type for processing/exporting just add the new implementation on the factory.

## Sources

[Wikipedia](https://en.wikipedia.org/wiki/Decathlon)
