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

    private int maxX;
    private int maxY;

    private int score;
    private int steps;

    private final Shell shell;
    private Graphics graphics;

    private int spriteX;
    private int spriteY;

    private int goldX;
    private int goldY;

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
        graphics.drawRect(8, 5, 14, 5);
        graphics.drawRect(60, 5, 14, 5);
        graphics.drawRect(8, 15, 14, 5);
        graphics.drawRect(60, 15, 14, 5);
        graphics.setColor(goldColor);
        graphics.fillRect(14, 8, 2, 1);
        graphics.fillRect(66, 8, 2, 1);
        graphics.fillRect(14, 18, 2, 1);
        graphics.fillRect(66, 18, 2, 1);
        graphics.setColor(WORLD_COLOR);
        spriteX = maxX / 2 - 2;
        spriteY = maxY / 2;
        graphics.drawString(SPRITE, spriteX, spriteY);
    }

    public void updateScore() {
        if (spriteX == goldX && spriteY == goldY) {
            graphics.drawString("" + ++score, 7, 1);

        }
    }

    public void countMove() {
        graphics.drawString("" + ++steps, 19, 1);
    }

    public void moveUp() throws InterruptedException {
        if (spriteY != 3) {
            int pathClear = spriteY;
            graphics.drawString(SPRITE, spriteX, --spriteY);
            graphics.drawString(" ", spriteX, pathClear);
        }
    }

    public void moveDown() throws InterruptedException {
        if (spriteY != 23) {
            int pathClear = spriteY;
            graphics.drawString(SPRITE, spriteX, ++spriteY);
            graphics.drawString(" ", spriteX, pathClear);
        }
    }

    public void moveLeft() throws InterruptedException {
        if (spriteX != 2) {
            int pathClear = spriteX;
            graphics.drawString(SPRITE, --spriteX, spriteY);
            graphics.drawString(" ", pathClear, spriteY);
        }
    }

    public void moveRight() throws InterruptedException {
        if (spriteX != 79) {
            int pathClear = spriteX;
            graphics.drawString(SPRITE, ++spriteX, spriteY);
            graphics.drawString(" ", pathClear, spriteY);
        }
    }

    public void cleanup() {
        graphics.cleanup();
    }

}
