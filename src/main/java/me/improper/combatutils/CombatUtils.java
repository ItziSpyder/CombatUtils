package me.improper.combatutils;

import me.improper.combatutils.commands.Commands;
import me.improper.combatutils.commands.Tabs;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.event.*;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CombatUtils extends JavaPlugin {

    public static String starter = "";
    public static final PluginManager plManager = Bukkit.getPluginManager();
    public static final Permission chatCmdPerm = plManager.getPermission("combatutils.chatcommands");

    public CombatUtils() {

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        starter = Config.Plugin.getPrefix().trim() + " ";

        // Events
        plManager.registerEvents(new OnClick(),this);
        plManager.registerEvents(new OnDamage(),this);
        plManager.registerEvents(new OnProjectile(),this);
        plManager.registerEvents(new OnChat(),this);
        plManager.registerEvents(new OnCrystal(),this);

        // Files

        // Commands
        getCommand("#toggle").setExecutor(new Commands());
        getCommand("#toggle").setTabCompleter(new Tabs());
        getCommand("#spam").setExecutor(new Commands());
        getCommand("#spam").setTabCompleter(new Tabs());
        getCommand("#crash").setExecutor(new Commands());
        getCommand("#crash").setTabCompleter(new Tabs());
        getCommand("#pause").setExecutor(new Commands());
        getCommand("#pause").setTabCompleter(new Tabs());
        getCommand("#perm").setExecutor(new Commands());
        getCommand("#perm").setTabCompleter(new Tabs());

        // Loop
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Profile profile = ProfileLoader.loadProfile(p);
                    if (profile.isPaused()) continue;
                    profile.getModules().forEach(module -> {
                        if (module.isEnabled()) module.onTick();
                    });
                }
            }
        }.runTaskTimer(this,0,3);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Shutdown
        for (Player p : Bukkit.getOnlinePlayers()) {
            Profile profile = ProfileLoader.loadProfile(p);
            profile.getModules().forEach(module -> {
                if (module.isEnabled()) module.onDisable();
            });
        }
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("CombatUtils");
    }
}
