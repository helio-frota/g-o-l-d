package org.esmerilprogramming.g_o_l_d;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Key;
import org.jboss.aesh.terminal.Shell;
import org.jboss.aesh.util.ANSI;

@CommandDefinition(name = "gold", description = "")
public class Gold implements Command<CommandInvocation> {

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
        processOperation();

        return CommandResult.SUCCESS;
    }

    private void startGame(Shell shell) {
        runner = new GoldRunner(shell);
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runner);
    }

    public void processOperation() throws IOException, InterruptedException {
        try {
            while (true) {
                CommandOperation commandOperation = commandInvocation.getInput();
                if (commandOperation.getInputKey() == Key.UP) {
                    System.out.println("UP");
                } else if (commandOperation.getInputKey() == Key.DOWN) {
                    System.out.println("DOWN");
                } else if (commandOperation.getInputKey() == Key.LEFT) {
                    System.out.println("LEFT");
                } else if (commandOperation.getInputKey() == Key.RIGHT) {
                    System.out.println("RIGHT");
                } else if (commandOperation.getInputKey() == Key.ESC || commandOperation.getInputKey() == Key.q) {
                    stop();
                }
            }
        }
        catch (InterruptedException ie) {
            throw ie;
        }
    }
    
    private void stop() throws IOException {
        if(executorService != null) {
            executorService.shutdown();
        }

        shell.clear();
        shell.out().print(ANSI.restoreCursor());
        shell.out().print(ANSI.showCursor());
        shell.enableMainBuffer();
        shell.out().flush();
    }

}
