package org.esmerilprogramming.g_o_l_d.ui;

import org.jboss.aesh.graphics.AeshGraphicsConfiguration;
import org.jboss.aesh.graphics.Graphics;
import org.jboss.aesh.terminal.Shell;

public class GameUI {

    private Graphics graphics;
    
    public GameUI(Shell shell) {
        graphics = new AeshGraphicsConfiguration(shell).getGraphics();
    }
    
    public Graphics getGraphics() {
        return graphics;
    }
    
}
