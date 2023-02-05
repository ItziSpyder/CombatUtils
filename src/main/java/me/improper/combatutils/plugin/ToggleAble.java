package me.improper.combatutils.plugin;

public interface ToggleAble {

    boolean isEnabled();

    void setEnabled(boolean enabled);

    void onEnable();

    void onDisable();

    void onTick();
}
