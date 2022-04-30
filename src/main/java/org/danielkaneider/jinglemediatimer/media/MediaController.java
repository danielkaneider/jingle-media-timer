package org.danielkaneider.jinglemediatimer.media;

import commands.MediaKeys;
import org.danielkaneider.jinglemediatimer.components.JingleGlobalSettings;
import org.springframework.stereotype.Component;

@Component
public class MediaController {

    private final JingleGlobalSettings jingleGlobalSettings;

    public MediaController(JingleGlobalSettings jingleGlobalSettings) {
        this.jingleGlobalSettings = jingleGlobalSettings;
    }

    public void playPause() {
        MediaKeys.songPlayPause();
   }

   public void reduceVolume() {
       for (int i = 0; i < jingleGlobalSettings.getVolumeSteps(); i++) {
           MediaKeys.volumeDown();
           shortWait();
       }
   }

    public void increaseVolume() {
       for (int i = 0; i < jingleGlobalSettings.getVolumeSteps(); i++) {
           MediaKeys.volumeUp();
           shortWait();
       }
   }

    private void shortWait() {
        try {
            Thread.sleep(jingleGlobalSettings.getWaitBetweenVolumeChangeMillis());
        } catch (InterruptedException ignored) {
        }
    }
}
