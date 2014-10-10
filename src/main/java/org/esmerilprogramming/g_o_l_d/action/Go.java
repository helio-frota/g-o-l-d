package org.esmerilprogramming.g_o_l_d.action;

import java.io.IOException;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandOperation;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.terminal.Shell;

@CommandDefinition(name = "go", description = "")
public class Go implements Command<CommandInvocation> {

    private CommandInvocation commandInvocation;
    private Shell shell;
    private CommandOperation commandOperation;
    
    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws IOException, InterruptedException {

        this.commandInvocation = commandInvocation;
        shell = this.commandInvocation.getShell();
        shell.enableAlternateBuffer();
        //commandOperation = commandInvocation.getInput();
        //GameUI gameUI = new GameUI(this.commandInvocation.getShell());
        int x = 0;
        
        System.out.println("start the game ?");
        
        do {
            System.out.println("---");
        } while(x < 1);
        
        return CommandResult.SUCCESS;
    }
    
}
