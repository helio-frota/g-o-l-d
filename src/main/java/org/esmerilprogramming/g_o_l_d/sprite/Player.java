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
package org.esmerilprogramming.g_o_l_d.sprite;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class Player {

    public static final String CHARACTER = "X";
    
    private int positionX;
    private int positionY;
    
    private int score;
    private int steps;
    
    public Player(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    public int getScore() {
        return score;
    }

    public int increaseScore() {
        return ++score;
    }
    
    public int increaseSteps() {
        return ++steps;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    
    public int decreasePositionX() {
        return --positionX;
    }
    
    public int decreasePositionY() {
        return --positionY;
    }
    
    public int increasePositionX() {
        return ++positionX;
    }
    
    public int increasePositionY() {
        return ++positionY;
    }
}
