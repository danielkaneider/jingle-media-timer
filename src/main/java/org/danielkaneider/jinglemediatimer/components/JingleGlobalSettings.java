package org.danielkaneider.jinglemediatimer.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ConfigurationProperties(prefix="jingle")
public class JingleGlobalSettings {

    private long volumeSteps = 20;
    private long waitBetweenVolumeChangeMillis = 2;
    private JingleItem startJingle;
    private JingleItem beforeEndJingle;

    public long getVolumeSteps() {
        return volumeSteps;
    }

    public void setVolumeSteps(long volumeSteps) {
        this.volumeSteps = volumeSteps;
    }

    public long getWaitBetweenVolumeChangeMillis() {
        return waitBetweenVolumeChangeMillis;
    }

    public void setWaitBetweenVolumeChangeMillis(long waitBetweenVolumeChangeMillis) {
        this.waitBetweenVolumeChangeMillis = waitBetweenVolumeChangeMillis;
    }

    public JingleItem getStartJingle() {
        return startJingle;
    }

    public void setStartJingle(JingleItem startJingle) {
        this.startJingle = startJingle;
    }

    public JingleItem getBeforeEndJingle() {
        return beforeEndJingle;
    }

    public void setBeforeEndJingle(JingleItem beforeEndJingle) {
        this.beforeEndJingle = beforeEndJingle;
    }

    public static class JingleItem {
        private boolean enabled;
        private File file;
        private long durationInSeconds = 10;
        private long sleepAfterJingleInSeconds = 10;
        private int volume = 80;
        private boolean pauseMedia = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public long getDurationInSeconds() {
            return durationInSeconds;
        }

        public void setDurationInSeconds(long durationInSeconds) {
            this.durationInSeconds = durationInSeconds;
        }

        public long getSleepAfterJingleInSeconds() {
            return sleepAfterJingleInSeconds;
        }

        public void setSleepAfterJingleInSeconds(long sleepAfterJingleInSeconds) {
            this.sleepAfterJingleInSeconds = sleepAfterJingleInSeconds;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public boolean isPauseMedia() {
            return pauseMedia;
        }

        public void setPauseMedia(boolean pauseMedia) {
            this.pauseMedia = pauseMedia;
        }
    }
}
