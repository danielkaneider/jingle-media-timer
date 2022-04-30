Simple program to play some jingles at the beginning and end
of [Ultimate Frisbee](https://en.wikipedia.org/wiki/Ultimate_(sport)) games.

# Usage

**NOTE**: Make sure you have at least Java 17 installed!

Example

```
java -jar <...>.jar -start 09:00 -duration 27 -break 3
```

## Options

```
usage: -start 09:00 -duration 30 -break 3
 -break,--break <arg>         Break time between games [in minutes]
 -duration,--duration <arg>   Duration of games [in minutes]
 -start,--start-time <arg>    First start time [HH:mm]
```

Also jingles and durations can be customized. For this copy the relavant paths of
the [application.yaml](src/main/resources/application.yaml) file and add them to the one near the `.jar` file

# Development

...

# References

- https://github.com/nsnave/java-media-keys