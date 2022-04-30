package org.danielkaneider.jinglemediatimer.components;

import java.util.Date;

public record JingleStartArguments(Date startTime, Long durationInMinutes, Long breakInMinutes) {
}
