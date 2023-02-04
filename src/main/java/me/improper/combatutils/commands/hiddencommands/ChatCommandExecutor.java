package me.improper.combatutils.commands.hiddencommands;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface ChatCommandExecutor {

    boolean execute(CommandSender sender, ChatCommand command);
}
