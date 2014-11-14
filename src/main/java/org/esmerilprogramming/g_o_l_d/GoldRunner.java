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

import org.esmerilprogramming.g_o_l_d.graphics.GoldGraphics;
import org.esmerilprogramming.g_o_l_d.sounds.Sounds;
import org.esmerilprogramming.g_o_l_d.sprite.Gold;
import org.esmerilprogramming.g_o_l_d.sprite.Player;
import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.GraphicsConfiguration;
import org.jboss.aesh.terminal.Shell;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class GoldRunner implements Runnable {

    private Gold g1 = new Gold(14, 8);
    private Gold g2 = new Gold(66, 8);
    private Gold g3 = new Gold(14, 28);
    private Gold g4 = new Gold(66, 18);

    private Player player;

    private Shell shell;
    
    private GoldGraphics goldGraphics;
    
    private int lastGoldItem;

    public GoldRunner(Shell shell) {
        this.shell = shell;

        GraphicsConfiguration gc = new AeshGraphicsConfiguration(this.shell);
        goldGraphics = new GoldGraphics(gc.getGraphics());
        goldGraphics.drawWorld(shell.getSize().getWidth(), shell.getSize().getHeight());
        
        randomGold();
        
        player = new Player();
        player.setPositionX(shell.getSize().getWidth() / 2 - 2);
        player.setPositionY(shell.getSize().getHeight() / 2);
        
        goldGraphics.drawPlayer(player);
        
        new Thread(new Timer(goldGraphics.getGraphics(), shell.getSize().getWidth())).start();
    }

    @Override
    public void run() {

    }

    private void randomGold() {

        Random rand = new Random();
        int raffle = rand.nextInt(4) + 1;
        if (raffle == 1 && raffle != lastGoldItem) {
            goldGraphics.repaintGold(g1);
            lastGoldItem = 1;
        }
        else if (raffle == 2 && raffle != lastGoldItem) {
            goldGraphics.repaintGold(g2);
            lastGoldItem = 2;
        }
        else if (raffle == 3 && raffle != lastGoldItem) {
            goldGraphics.repaintGold(g3);
            lastGoldItem = 3;
        }
        else if (raffle == 4 && raffle != lastGoldItem) {
            goldGraphics.repaintGold(g4);
            lastGoldItem = 4;
        }
        else {
            randomGold();
        }
    }

    private void checkGetGold() {

        Gold currentGold;
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

        if (player.getPositionX() == currentGold.getX() && player.getPositionY() == currentGold.getY()) {
            goldGraphics.getGraphics().drawString("" + player.increaseScore(), 7, 1);
            Sounds.playSound();
            goldGraphics.drawGoldPlaces();
            randomGold();
        }
    }

    private void countMove() {
        goldGraphics.getGraphics().drawString("" + player.increaseSteps(), 19, 1);
    }

    public void moveUp() throws InterruptedException {
        if (player.getPositionY() != 3) {
            int pathClear = player.getPositionY();
            goldGraphics.getGraphics().drawString(Player.CHARACTER, player.getPositionX(), player.decreasePositionY());
            goldGraphics.getGraphics().drawString(" ", player.getPositionX(), pathClear);
            countMove();
            checkGetGold();
        }
    }

    public void moveDown() throws InterruptedException {
        if (player.getPositionY() != 23) {
            int pathClear = player.getPositionY();
            goldGraphics.getGraphics().drawString(Player.CHARACTER, player.getPositionX(), player.increasePositionY());
            goldGraphics.getGraphics().drawString(" ", player.getPositionX(), pathClear);
            countMove();
            checkGetGold();
        }
    }

    public void moveLeft() throws InterruptedException {
        if (player.getPositionX() != 2) {
            int pathClear = player.getPositionX();
            goldGraphics.getGraphics().drawString(Player.CHARACTER, player.decreasePositionX(), player.getPositionY());
            goldGraphics.getGraphics().drawString(" ", pathClear, player.getPositionY());
            countMove();
            checkGetGold();
        }
    }

    public void moveRight() throws InterruptedException {
        if (player.getPositionX() != 79) {
            int pathClear = player.getPositionX();
            goldGraphics.getGraphics().drawString(Player.CHARACTER, player.increasePositionX(), player.getPositionY());
            goldGraphics.getGraphics().drawString(" ", pathClear, player.getPositionY());
            countMove();
            checkGetGold();
        }
    }

    public void cleanup() {
        goldGraphics.getGraphics().cleanup();
    }

}
