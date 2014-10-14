package org.esmerilprogramming.g_o_l_d;

import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.terminal.Shell;

public class TimerRunner implements Runnable {

    private int maxX;
    private int timer = 30;
    private Graphics graphics;
    
    public TimerRunner(Shell shell) {
        startTimer();
    }

    @Override
    public void run() {
        
    }
    
    private void startTimer() {
        while (true) {
            graphics.drawString("" + timer--, maxX - 2, 1);
            pause();
        }
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
