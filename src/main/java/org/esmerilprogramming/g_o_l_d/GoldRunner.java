package org.esmerilprogramming.g_o_l_d;

import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.graphics.GraphicsConfiguration;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.terminal.TerminalColor;

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
    }

    @Override
    public void run() {

    }

    public void drawWorld() {
        graphics.drawString("SCORE:", 0, 1);
        graphics.drawString("STEPS:", 13, 1);
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
