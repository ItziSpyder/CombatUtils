package me.improper.combatutils.event;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.commands.hiddencommands.ChatCommand;
import me.improper.combatutils.commands.hiddencommands.ModuleCommands;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.server.ArgBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat implements Listener {

    private static final ModuleCommands moduleCommands = new ModuleCommands();
    private static final Character commandPrefix = Config.Plugin.getCmdPrefixChar();

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        if (msg.charAt(0) == commandPrefix) {
            if (Config.Plugin.getChatCmdPerm() && !p.hasPermission("combatutils.chatcommands")) return;
            e.setCancelled(true);
            ChatCommand cmd = new ChatCommand(msg);
            if (!moduleCommands.execute(p,cmd)) p.sendMessage(CombatUtils.starter +
                    ChatColor.RED + "Unknown command \"" + cmd.getName() + "\", \">help\" for help");
            else p.sendMessage(CombatUtils.starter +
                    ChatColor.WHITE + "Executed: " +
                    ChatColor.YELLOW + commandPrefix + cmd.getName() + " " +
                    ChatColor.GRAY + ArgBuilder.buildArgs(cmd.getArgs()));
        }
    }
}
