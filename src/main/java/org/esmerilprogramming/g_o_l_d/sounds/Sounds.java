/*
 * Copyright 2014 EsmerilProgramming.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.esmerilprogramming.g_o_l_d.sounds;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class Sounds {

    private static final String MUSIC = "173248__zagi2__heavy-loop.wav";
    private static final String SOUND = "209578__zott820__cash-register-purchase.wav";

    public static void playMusic() {
        Clip music = null;
        try {
            music = AudioSystem.getClip();
            music.open(AudioSystem.getAudioInputStream(Sounds.class.getResourceAsStream(MUSIC)));
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        music.start();
        music.loop(3);
    }

    public static void playSound() {
        Clip sound = null;
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(Sounds.class.getResourceAsStream(SOUND)));
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        sound.start();
    }

}
