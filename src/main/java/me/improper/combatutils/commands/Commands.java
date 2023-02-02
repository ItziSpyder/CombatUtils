package me.improper.combatutils.commands;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "#toggle" -> {
                    if (sender instanceof Player p) {
                        Profile profile = Profile.getProfile(p);
                        Module module = profile.getModuleObject(args[0]);
                        if (module.isEnabled()) module.onDisable();
                        else module.onEnable();
                    }
                    return true;
                }
            }
        } catch (Exception ex) {
            String msg = CombatUtils.STARTER + ChatColor.RED + "Command Error: ";
            if (ex instanceof NullPointerException) msg += "Command contains a null value!";
            else if (ex instanceof IndexOutOfBoundsException) msg += "Incomplete command! Please provide more information!";
            else msg += ex.getMessage();
            sender.sendMessage(msg);
            return true;
        }

        return false;
    }
}
