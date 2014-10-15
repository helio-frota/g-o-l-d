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
    
    private final Shell shell;
    private Graphics graphics;

    private TerminalColor tc;

    public GoldRunner(Shell shell) {
        this.shell = shell;

        tc = new TerminalColor(Color.BLUE, Color.DEFAULT);

        GraphicsConfiguration gc = new AeshGraphicsConfiguration(this.shell);
        graphics = gc.getGraphics();
        graphics.setColor(tc);

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
    }

    public void updateScore() {
        graphics.drawString("" + score++, 7, 1);
    }

    public void countMove() {
        graphics.drawString("" + score++, 19, 1);
    }

}
