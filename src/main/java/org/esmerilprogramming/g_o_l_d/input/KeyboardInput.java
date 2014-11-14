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
package org.esmerilprogramming.g_o_l_d.input;

import java.util.concurrent.ExecutorService;

import org.esmerilprogramming.g_o_l_d.core.GoldRunner;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Key;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class KeyboardInput {

    private CommandInvocation ci;    
    private GoldRunner runner;
    private ExecutorService executorService;
    
    public KeyboardInput(CommandInvocation ci, GoldRunner runner, ExecutorService executorService) {
        this.ci = ci;
        this.runner = runner;
        this.executorService = executorService;
    }
    
    public void processInput() {
        try {
            while (GoldRunner.running) {
                CommandOperation commandOperation = ci.getInput();
                if (commandOperation.getInputKey() == Key.UP) {
                    runner.moveUp();
                }
                else if (commandOperation.getInputKey() == Key.DOWN) {
                    runner.moveDown();
                }
                else if (commandOperation.getInputKey() == Key.LEFT) {
                    runner.moveLeft();
                }
                else if (commandOperation.getInputKey() == Key.RIGHT) {
                    runner.moveRight();
                }
                else if (commandOperation.getInputKey() == Key.ESC || 
                    commandOperation.getInputKey() == Key.q ||
                    commandOperation.getInputKey() == Key.CTRL_C) {
                    runner.stop(executorService);
                }
            }
            
            runner.stop(executorService);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
