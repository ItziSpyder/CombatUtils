package me.improper.combatutils.plugin;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.data.SavedPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Module implements Cloneable {

    private boolean enabled;
    private String name;
    private SavedPlayer eventPlayer;

    public Module(String name) {
        this.name = name;
    }

    public Module(Player eventPlayer) {
        this.name = "NewModule";
        this.eventPlayer = new SavedPlayer(eventPlayer);
    }

    public String getModuleStatus() {
        return CombatUtils.STARTER +
                ChatColor.WHITE + this.name + " " +
                ChatColor.GRAY + "set to " +
                ChatColor.WHITE + this.enabled;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Player getEventPlayer() {
        return eventPlayer.getPlayer();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEventPlayer(Player eventPlayer) {
        this.eventPlayer = new SavedPlayer(eventPlayer);
    }

    public void setEventPlayer(SavedPlayer eventPlayer) {
        this.eventPlayer = eventPlayer;
    }

    @Override
    public Module clone() {
        try {
            return (Module) super.clone();
        } catch (Exception ex) {
            return null;
        }
    }
}
