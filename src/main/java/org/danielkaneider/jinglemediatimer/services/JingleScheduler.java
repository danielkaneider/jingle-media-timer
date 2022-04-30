package org.danielkaneider.jinglemediatimer.services;

import org.danielkaneider.jinglemediatimer.components.JingleGlobalSettings;
import org.danielkaneider.jinglemediatimer.components.JingleStartArguments;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JingleScheduler {

    private final JingleStartArguments startArguments;
    private final JingleGlobalSettings globalArguments;

    public JingleScheduler(JingleStartArguments startArguments, JingleGlobalSettings globalArguments) {
        this.globalArguments = globalArguments;
        this.startArguments = startArguments;
    }

    public Date getNextStartExecutionTime() {
        Date now = new Date();
        Date startTime = startArguments.startTime();
        if (startTime.after(now)) {
            return startTime;
        }

        long slotTimeInMillis = getSlotTimeInMillis();
        long wholeSlots = (now.getTime() - startTime.getTime()) / getSlotTimeInMillis();

        return new Date(startTime.getTime() + (wholeSlots + 1) * slotTimeInMillis);
    }

    public Date getNextBeforeEndExecutionTime() {
        Date now = new Date();
        long nextStartTimeMillis = getNextStartExecutionTime().getTime();

        long slotTimeInMillis = getSlotTimeInMillis();
        long jingleDuration = TimeUnit.SECONDS.toMillis(globalArguments.getBeforeEndJingle().getDurationInSeconds()
                + globalArguments.getBeforeEndJingle().getSleepAfterJingleInSeconds());

        long plannedBeforeEndStartMillis = nextStartTimeMillis
                - TimeUnit.MINUTES.toMillis(startArguments.breakInMinutes())
                - jingleDuration;

        if (now.getTime() > plannedBeforeEndStartMillis) {
            plannedBeforeEndStartMillis += slotTimeInMillis;
        }

        return new Date(plannedBeforeEndStartMillis);
    }

    private long getSlotTimeInMillis() {
        return TimeUnit.MINUTES.toMillis(startArguments.durationInMinutes() + startArguments.breakInMinutes());
    }

}
