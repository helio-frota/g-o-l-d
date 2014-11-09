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

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Key;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.util.ANSI;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@CommandDefinition(name = "gold", description = "")
public class GoldCommand implements Command<CommandInvocation> {

    private CommandInvocation commandInvocation;
    private Shell shell;
    private ExecutorService executorService;
    private GoldRunner runner;

    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {

        this.commandInvocation = commandInvocation;
        shell = this.commandInvocation.getShell();
        shell.out().print(ANSI.saveCursor());
        shell.out().print(ANSI.hideCursor());
        shell.enableAlternateBuffer();
        shell.out().flush();

        startGame(shell);
        processInput();

        return CommandResult.SUCCESS;
    }

    private void startGame(Shell shell) {
        runner = new GoldRunner(shell);
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runner);
        playMetal();
    }

    public void processInput() throws IOException, InterruptedException {
        try {
            while (true) {
                CommandOperation commandOperation = commandInvocation.getInput();
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
                else if (commandOperation.getInputKey() == Key.ESC || commandOperation.getInputKey() == Key.q) {
                    stop();
                }
            }
        }
        catch (InterruptedException e) {
            throw e;
        }
    }

    private void stop() throws IOException {
        runner.cleanup();
        if (executorService != null) {
            executorService.shutdown();
        }

        shell.clear();
        shell.out().print(ANSI.restoreCursor());
        shell.out().print(ANSI.showCursor());
        shell.enableMainBuffer();
        shell.out().flush();
    }

    private void playMetal() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("173248__zagi2__heavy-loop.wav")));
            clip.start();
            clip.loop(3);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
