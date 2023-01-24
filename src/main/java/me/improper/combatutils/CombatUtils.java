package me.improper.combatutils;

import me.improper.combatutils.event.OnClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CombatUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Events
        Bukkit.getPluginManager().registerEvents(new OnClick(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("CombatUtils");
    }
}
