package com.he4xi.flappybird;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Class for audio player.
 * Created by rando on 11.12.16.
 */
public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String s) {

        try {
            File soundfile = new File(s);
            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream(s)
                    );
            AudioFormat baseFormat = ais.getFormat();
            AudioInputStream soundIn = AudioSystem.getAudioInputStream(soundfile);
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        if(clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if(clip.isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }

}
