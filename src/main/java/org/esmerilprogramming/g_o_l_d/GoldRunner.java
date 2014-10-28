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
package org.esmerilprogramming.g_o_l_d;

import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.graphics.GraphicsConfiguration;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.terminal.TerminalColor;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class GoldRunner implements Runnable {

    GoldItem g1 = new GoldItem(14, 8);
    GoldItem g2 = new GoldItem(66, 8);
    GoldItem g3 = new GoldItem(14, 28);
    GoldItem g4 = new GoldItem(66, 18);

    private int maxX;
    private int maxY;

    private Player player;
    
    private Shell shell;
    private Graphics graphics;

    private int spriteX;
    private int spriteY;

    private int lastGoldItem;

    static final TerminalColor WORLD_COLOR = new TerminalColor(Color.BLUE, Color.DEFAULT);
    private TerminalColor goldColor;

    private static final String SPRITE = "X";

    public GoldRunner(Shell shell) {
        this.shell = shell;

        goldColor = new TerminalColor(Color.DEFAULT, Color.YELLOW);

        GraphicsConfiguration gc = new AeshGraphicsConfiguration(this.shell);
        graphics = gc.getGraphics();
        graphics.setColor(WORLD_COLOR);

        maxX = shell.getSize().getWidth();
        maxY = shell.getSize().getHeight();

        drawWorld();
        new Thread(new Timer(graphics, maxX)).start();
    }

    @Override
    public void run() {

    }

    public void drawWorld() {
        graphics.drawString("SCORE:", 0, 1);
        graphics.drawString("STEPS:", 13, 1);
        graphics.drawString("TIME REMAINING:", maxX - 17, 1);
        graphics.drawLine(0, 2, maxX, 2);
        drawPlaces();
        randomGold();
        spriteX = maxX / 2 - 2;
        spriteY = maxY / 2;
        graphics.drawString(SPRITE, spriteX, spriteY);
    }

    private void randomGold() {

        Random rand = new Random();
        int raffle = rand.nextInt(4) + 1;
        if (raffle == 1 && raffle != lastGoldItem) {
            applyRaffle(g1, 1);
        }
        else if (raffle == 2 && raffle != lastGoldItem) {
            applyRaffle(g2, 2);
        }
        else if (raffle == 3 && raffle != lastGoldItem) {
            applyRaffle(g3, 3);
        }
        else if (raffle == 4 && raffle != lastGoldItem) {
            applyRaffle(g4, 4);
        }
        else {
            randomGold();
        }
    }

    private void checkGetGold() {

        GoldItem currentGold;
        if (lastGoldItem == 1) {
            currentGold = g1;
        }
        else if (lastGoldItem == 2) {
            currentGold = g2;
        }
        else if (lastGoldItem == 3) {
            currentGold = g3;
        }
        else {
            currentGold = g4;
        }

        if (spriteX == currentGold.getX() && spriteY == currentGold.getY()) {
            graphics.drawString("" + player.increaseScore(), 7, 1);
            playGold();
            drawPlaces();
            randomGold();
        }
    }

    private void playGold() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("209578__zott820__cash-register-purchase.wav")));
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyRaffle(GoldItem g, int lastGoldItemValue) {
        graphics.setColor(goldColor);
        graphics.fillRect(g.getX(), g.getY(), GoldItem.WIDTH, GoldItem.HEIGHT);
        lastGoldItem = lastGoldItemValue;
        graphics.setColor(WORLD_COLOR);
    }

    class GoldItem {

        static final int HEIGHT = 1;
        static final int WIDTH = 1;
        private int x;
        private int y;

        public GoldItem(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private void drawPlaces() {
        graphics.drawRect(8, 5, 14, 5);
        graphics.drawRect(60, 5, 14, 5);
        graphics.drawRect(8, 15, 14, 5);
        graphics.drawRect(60, 15, 14, 5);
    }

    private void countMove() {
        graphics.drawString("" + player.increaseSteps(), 19, 1);
    }

    public void moveUp() throws InterruptedException {
        if (spriteY != 3) {
            int pathClear = spriteY;
            graphics.drawString(SPRITE, spriteX, --spriteY);
            graphics.drawString(" ", spriteX, pathClear);
            countMove();
            checkGetGold();
        }
    }

    public void moveDown() throws InterruptedException {
        if (spriteY != 23) {
            int pathClear = spriteY;
            graphics.drawString(SPRITE, spriteX, ++spriteY);
            graphics.drawString(" ", spriteX, pathClear);
            countMove();
            checkGetGold();
        }
    }

    public void moveLeft() throws InterruptedException {
        if (spriteX != 2) {
            int pathClear = spriteX;
            graphics.drawString(SPRITE, --spriteX, spriteY);
            graphics.drawString(" ", pathClear, spriteY);
            countMove();
            checkGetGold();
        }
    }

    public void moveRight() throws InterruptedException {
        if (spriteX != 79) {
            int pathClear = spriteX;
            graphics.drawString(SPRITE, ++spriteX, spriteY);
            graphics.drawString(" ", pathClear, spriteY);
            countMove();
            checkGetGold();
        }
    }

    public void cleanup() {
        graphics.cleanup();
    }

}
