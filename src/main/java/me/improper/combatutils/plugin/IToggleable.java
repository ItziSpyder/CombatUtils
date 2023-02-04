package me.improper.combatutils.plugin;

public interface IToggleable {

    boolean isEnabled();

    void setEnabled(boolean enabled);

    void onEnable();

    void onDisable();

    void onTick();
}
