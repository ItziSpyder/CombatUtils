package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.plugin.IModule;
import me.improper.combatutils.plugin.Module;
import org.bukkit.entity.Player;

public class Speed extends Module implements IModule {

    private float ogSpeed;

    public Speed() {
        super("Speed");
    }

    @Override
    public void onEnable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(true);
        p.sendMessage(super.getModuleStatus());

        this.ogSpeed = p.getWalkSpeed();
        p.setWalkSpeed(0.6F);
    }

    @Override
    public void onDisable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(false);
        p.sendMessage(super.getModuleStatus());

        p.setWalkSpeed(0.21F);
    }

    @Override
    public void onTick() {

    }
}
