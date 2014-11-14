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
package org.esmerilprogramming.g_o_l_d.core;

import org.esmerilprogramming.g_o_l_d.graphics.GoldGraphics;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class Timer implements Runnable {

    private int maxX;
    public static int timer = 30;
    private GoldGraphics goldGraphics;

    public Timer(GoldGraphics goldGraphics, int maxX) {
        this.goldGraphics = goldGraphics;
        this.maxX = maxX;
    }

    @Override
    public void run() {
        while (timer != 0) {
            if (timer >= 0) {
                goldGraphics.drawTimeLeft(timer--, maxX);
                pause();
            }
            else {
                break;
            }
        }

        GoldRunner.running = false;

    }

    private void pause() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
