package me.improper.combatutils.plugin;

import org.bukkit.entity.Player;

public class Module {

    boolean enabled;
    String name;
    Player eventPlayer;

    public Module(String name) {
        this.name = name;
    }

    public Module(Player eventPlayer) {
        this.eventPlayer = eventPlayer;
    }

    public void enableModule() {

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
        return eventPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEventPlayer(Player eventPlayer) {
        this.eventPlayer = eventPlayer;
    }
}
