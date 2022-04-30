package org.danielkaneider.jinglemediatimer.services;

import org.danielkaneider.jinglemediatimer.components.JingleGlobalSettings;
import org.danielkaneider.jinglemediatimer.components.JingleStartArguments;
import org.danielkaneider.jinglemediatimer.media.AudioPlayer;
import org.danielkaneider.jinglemediatimer.media.MediaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JingleService {

    private static final Logger LOG = LoggerFactory.getLogger(JingleService.class);

    private final MediaController mediaController;
    private final JingleStartArguments jingleStartArguments;
    private final JingleGlobalSettings jingleGlobalSettings;

    public JingleService(MediaController mediaController, JingleStartArguments jingleStartArguments, JingleGlobalSettings jingleGlobalSettings) {
        this.mediaController = mediaController;
        this.jingleStartArguments = jingleStartArguments;
        this.jingleGlobalSettings = jingleGlobalSettings;

        if (jingleGlobalSettings.getStartJingle().getFile() == null) {
            LOG.warn("Start jingle is not set!");
        }
        if (jingleGlobalSettings.getBeforeEndJingle().getFile() == null) {
            LOG.warn("Before-end jingle is not set!");
        }
    }

    public void runStartJingle() {
        runJingle(jingleGlobalSettings.getStartJingle());
    }

    public void runBeforeEndJingle() {
        runJingle(jingleGlobalSettings.getBeforeEndJingle());
    }

    private void runJingle(JingleGlobalSettings.JingleItem jingle) {
        if (jingle.isEnabled()) {
            if (jingle.isPauseMedia()) {
                mediaController.playPause();
            } else {
                mediaController.reduceVolume();
            }

            AudioPlayer audioPlayer = new AudioPlayer(jingle.getFile(), jingle.getVolume());
            audioPlayer.play();
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(jingle.getDurationInSeconds()));
            } catch (InterruptedException ignored) {
            }

            audioPlayer.stop();
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(jingle.getSleepAfterJingleInSeconds()));
            } catch (InterruptedException ignored) {
            }

            if (jingle.isPauseMedia()) {
                mediaController.playPause();
            } else {
                mediaController.increaseVolume();
            }
        }
    }

}
