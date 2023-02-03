package me.improper.combatutils.commands;

import me.improper.combatutils.plugin.Modules;
import me.improper.combatutils.server.ServerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        String commandName = command.getName().toLowerCase().trim();

        switch (commandName) {
            case "#toggle" -> {
                switch (args.length) {
                    case 1 -> list.addAll(Modules.stringValues());
                }
            }
            case "#spam" -> {
                switch (args.length) {
                    case 1 -> list.add(ChatColor.GRAY + "<amount>");
                    case 2 -> list.add(ChatColor.GRAY + "[<message>]");
                }
            }
            case "#crash" -> {
                switch (args.length) {
                    case 1 -> {
                        list.add("particle");
                        list.add("message");
                    }
                    case 2 -> list.addAll(ServerUtils.listPlayers());
                }
            }
        }

        list.removeIf(i -> !i.toLowerCase().contains(args[args.length - 1].toLowerCase()));
        return list;
    }
}
