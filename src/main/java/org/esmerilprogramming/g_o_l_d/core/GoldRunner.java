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

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.esmerilprogramming.g_o_l_d.graphics.GoldGraphics;
import org.esmerilprogramming.g_o_l_d.sounds.Sounds;
import org.esmerilprogramming.g_o_l_d.sprite.Gold;
import org.esmerilprogramming.g_o_l_d.sprite.Player;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.util.ANSI;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class GoldRunner implements Runnable {

    private ExecutorService timerService;
    
    private Gold g1 = new Gold(14, 8);
    private Gold g2 = new Gold(66, 8);
    private Gold g3 = new Gold(14, 18);
    private Gold g4 = new Gold(66, 18);

    private Player player;

    private Shell shell;

    private GoldGraphics goldGraphics;

    private int pastGold;

    public static boolean running = true;

    public GoldRunner(Shell shell, GoldGraphics goldGraphics) {
        this.shell = shell;

        this.goldGraphics = goldGraphics;
        this.goldGraphics.drawWorld(shell.getSize().getWidth(), shell.getSize().getHeight());

        randomGold();

        player = new Player((shell.getSize().getWidth() / 2) - 2, shell.getSize().getHeight() / 2);

        this.goldGraphics.drawPlayer(player);

        Sounds.playMusic();

        timerService = Executors.newSingleThreadExecutor();
        timerService.execute(new Timer(this.goldGraphics, shell.getSize().getWidth()));
    }

    @Override
    public void run() {

    }
    
    private void randomGold() {

        int raffle = new Random().nextInt(4) + 1;
        if (raffle == 1 && raffle != pastGold) {
            goldGraphics.repaintGold(g1);
            pastGold = 1;
        }
        else if (raffle == 2 && raffle != pastGold) {
            goldGraphics.repaintGold(g2);
            pastGold = 2;
        }
        else if (raffle == 3 && raffle != pastGold) {
            goldGraphics.repaintGold(g3);
            pastGold = 3;
        }
        else if (raffle == 4 && raffle != pastGold) {
            goldGraphics.repaintGold(g4);
            pastGold = 4;
        }
        else {
            randomGold();
        }
    }

    private void checkGetGold() {

        Gold currentGold;
        if (pastGold == 1) {
            currentGold = g1;
        }
        else if (pastGold == 2) {
            currentGold = g2;
        }
        else if (pastGold == 3) {
            currentGold = g3;
        }
        else {
            currentGold = g4;
        }

        if (player.getPositionX() == currentGold.getX() && player.getPositionY() == currentGold.getY()) {
            goldGraphics.getGraphics().drawString("" + player.increaseScore(), 7, 1);
            Sounds.playSound();
            goldGraphics.drawGoldPlaces();
            randomGold();
        }
    }

    public void moveUp() throws InterruptedException {
        if (player.getPositionY() != 3) {
            goldGraphics.drawMoveUp(player);
            checkGetGold();
        }
    }

    public void moveDown() throws InterruptedException {
        if (player.getPositionY() != 23) {
            goldGraphics.drawMoveDown(player);
            checkGetGold();
        }
    }

    public void moveLeft() throws InterruptedException {
        if (player.getPositionX() != 2) {
            goldGraphics.drawMoveLeft(player);
            checkGetGold();
        }
    }

    public void moveRight() throws InterruptedException {
        if (player.getPositionX() != 79) {
            goldGraphics.drawMoveRight(player);
            checkGetGold();
        }
    }

    public void gameOver(ExecutorService executorService) throws IOException {
        
        if (this.timerService != null) {
            this.timerService.shutdown();
        }
        
        goldGraphics.cleanup();
        shell.clear();
        shell.out().print(ANSI.restoreCursor());
        shell.out().print(ANSI.showCursor());
        shell.enableMainBuffer();
        shell.out().flush();

        if (executorService != null) {
            executorService.shutdown();
        }

    }

}
