package me.improper.combatutils;

import me.improper.combatutils.commands.Commands;
import me.improper.combatutils.commands.Tabs;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.event.OnClick;
import me.improper.combatutils.event.OnDamage;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CombatUtils extends JavaPlugin {

    public static String STARTER = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        STARTER = Config.PLUGIN.getPrefix().trim() + " ";

        // Events
        Bukkit.getPluginManager().registerEvents(new OnClick(),this);
        Bukkit.getPluginManager().registerEvents(new OnDamage(),this);

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Commands
        getCommand("#toggle").setExecutor(new Commands());
        getCommand("#toggle").setTabCompleter(new Tabs());

        // Loop
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Profile profile = Profile.getProfile(p);
                    profile.getModules().forEach(Module::onTick);
                }
            }
        }.runTaskTimer(this,0,5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Shutdown
        for (Player p : Bukkit.getOnlinePlayers()) {
            Profile profile = Profile.getProfile(p);
            for (Module mod : profile.getModules()) mod.onDisable();
        }
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("CombatUtils");
    }
}
