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
package org.esmerilprogramming.g_o_l_d.graphics;

import org.esmerilprogramming.g_o_l_d.sprite.Gold;
import org.esmerilprogramming.g_o_l_d.sprite.Player;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.terminal.TerminalColor;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class GoldGraphics {

    private Graphics graphics;

    public static final TerminalColor WORLD_COLOR = new TerminalColor(Color.BLUE, Color.DEFAULT);
    private static final TerminalColor GOLD_COLOR = new TerminalColor(Color.DEFAULT, Color.YELLOW);

    public GoldGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawReadyScreen(Shell shell) {
        graphics.setColor(WORLD_COLOR);
        int middleScreenWidth = (shell.getSize().getWidth() / 2) - 2;
        int middleScreenHeight = shell.getSize().getHeight() / 2;
        graphics.drawString("Use keyboard UP, DOWN, LEFT, RIGHT.", middleScreenWidth, middleScreenHeight);
        graphics.drawString("While playing, press 'q' or 'ESC' to quit.", middleScreenWidth, middleScreenHeight + 2);
        graphics.drawString("Ready ? (y/n)", middleScreenWidth, middleScreenHeight + 4);
    }
    
    public void drawScoreScreen(Shell shell) {
        graphics.setColor(WORLD_COLOR);
        int middleScreenWidth = (shell.getSize().getWidth() / 2) - 2;
        int middleScreenHeight = shell.getSize().getHeight() / 2;
        graphics.drawString("Best score: 9999.", middleScreenWidth, middleScreenHeight);
    }
    
    public void drawWorld(int screenWidth, int screenHeight) {
        graphics.setColor(WORLD_COLOR);
        graphics.drawString("SCORE:", 0, 1);
        graphics.drawString("STEPS:", 13, 1);
        graphics.drawString("TIME REMAINING:", screenWidth - 17, 1);
        graphics.drawLine(0, 2, screenWidth, 2);
        graphics.drawLine(0, screenHeight - 1, screenWidth, screenHeight - 1);
        drawGoldPlaces();
    }

    public void drawPlayer(Player player) {
        graphics.drawString(Player.CHARACTER, player.getPositionX(), player.getPositionY());
    }

    public void drawGoldPlaces() {
        graphics.drawRect(8, 5, 14, 5);
        graphics.drawRect(60, 5, 14, 5);
        graphics.drawRect(8, 15, 14, 5);
        graphics.drawRect(60, 15, 14, 5);
    }

    public void drawMoveRight(Player player) {
        int pathClear = player.getPositionX();
        graphics.drawString(Player.CHARACTER, player.increasePositionX(), player.getPositionY());
        graphics.drawString(" ", pathClear, player.getPositionY());
        graphics.drawString("" + player.increaseSteps(), 19, 1);
    }

    public void drawMoveLeft(Player player) {
        int pathClear = player.getPositionX();
        graphics.drawString(Player.CHARACTER, player.decreasePositionX(), player.getPositionY());
        graphics.drawString(" ", pathClear, player.getPositionY());
        graphics.drawString("" + player.increaseSteps(), 19, 1);
    }

    public void drawMoveDown(Player player) {
        int pathClear = player.getPositionY();
        graphics.drawString(Player.CHARACTER, player.getPositionX(), player.increasePositionY());
        graphics.drawString(" ", player.getPositionX(), pathClear);
        graphics.drawString("" + player.increaseSteps(), 19, 1);
    }

    public void drawMoveUp(Player player) {
        int pathClear = player.getPositionY();
        graphics.drawString(Player.CHARACTER, player.getPositionX(), player.decreasePositionY());
        graphics.drawString(" ", player.getPositionX(), pathClear);
        graphics.drawString("" + player.increaseSteps(), 19, 1);
    }

    public void drawTimeLeft(int timeLeft, int position) {
        graphics.setColor(WORLD_COLOR);
        if (timeLeft >= 10) {
            graphics.drawString("" + timeLeft, position - 2, 1);
        }
        else {
            graphics.drawString(" " + timeLeft, position - 2, 1);
        }
    }

    public void repaintGold(Gold g) {
        paintGoldColor();
        graphics.fillRect(g.getX(), g.getY(), Gold.WIDTH, Gold.HEIGHT);
        paintWorldColor();
    }

    public void paintWorldColor() {
        graphics.setColor(WORLD_COLOR);
    }

    public void paintGoldColor() {
        graphics.setColor(GOLD_COLOR);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void cleanup() {
        graphics.cleanup();
    }

}
