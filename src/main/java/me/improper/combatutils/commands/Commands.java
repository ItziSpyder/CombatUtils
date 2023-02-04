package me.improper.combatutils.commands;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.commands.hiddencommands.ChatCommand;
import me.improper.combatutils.commands.hiddencommands.ModuleCommands;
import me.improper.combatutils.server.ArgBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private static final ModuleCommands moduleCommands = new ModuleCommands();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandString = command.getName() + " " + ArgBuilder.buildArgs(args);
        ChatCommand cmd = new ChatCommand(commandString);
        if (!moduleCommands.execute(sender,cmd)) sender.sendMessage(CombatUtils.STARTER +
                ChatColor.RED + "Unknown command \"" + cmd.getName() + "\", \">help\" for help");
        return true;
    }
}
