package me.improper.combatutils;

import me.improper.combatutils.commands.Commands;
import me.improper.combatutils.commands.Tabs;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.event.OnChat;
import me.improper.combatutils.event.OnClick;
import me.improper.combatutils.event.OnDamage;
import me.improper.combatutils.event.OnProjectile;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import me.improper.combatutils.server.ArgBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CombatUtils extends JavaPlugin {

    public static String STARTER = "";
    public static String ESSAY = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        STARTER = Config.PLUGIN.getPrefix().trim() + " ";
        ESSAY = ArgBuilder.generateLargeString();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnClick(),this);
        Bukkit.getPluginManager().registerEvents(new OnDamage(),this);
        Bukkit.getPluginManager().registerEvents(new OnProjectile(),this);
        Bukkit.getPluginManager().registerEvents(new OnChat(),this);

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
        }.runTaskTimer(this,0,5);
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
