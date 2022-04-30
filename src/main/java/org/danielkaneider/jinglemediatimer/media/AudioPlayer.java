package org.danielkaneider.jinglemediatimer.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AudioPlayer {

    private static final Logger LOG = LoggerFactory.getLogger(AudioPlayer.class);

    private final File file;
    private final int volume;

    private Sequencer sequencer = null;

    public AudioPlayer(File file, int volume) {
        this.file = file;
        this.volume = volume;
    }

    public void play() {
        try {
            // Obtains the default Sequencer connected to a default device.
            sequencer = MidiSystem.getSequencer();

            // Opens the device, indicating that it should now acquire any
            // system resources it requires and become operational.
            sequencer.open();

            // create a stream from a file
            InputStream is = new BufferedInputStream(new FileInputStream(file));

            // Sets the current sequence on which the sequencer operates.
            // The stream must point to MIDI file data.
            sequencer.setSequence(is);

            Track[] tracks = sequencer.getSequence().getTracks();
            for (Track track : tracks) {
                track.add(new MidiEvent(
                        new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 7, volume), 0));
            }

            // Starts playback of the MIDI data in the currently loaded sequence.
            sequencer.start();

        } catch (Exception e) {
            LOG.warn("Unable to play sound", e);
        }
    }

    public void stop() {
        try {
            if (sequencer != null) {
                sequencer.stop();
                sequencer.close();
                sequencer = null;
            }
        } catch (Exception ignored) {
        }
    }
}
