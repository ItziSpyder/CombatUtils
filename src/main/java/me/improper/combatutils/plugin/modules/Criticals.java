package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.plugin.Module;
import org.bukkit.entity.Player;

public class Criticals extends Module {

    public Criticals() {
        super("Criticals");
    }

    @Override
    public void onEnable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(true);
        p.sendMessage(super.getModuleStatus());
    }

    @Override
    public void onDisable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(false);
        p.sendMessage(super.getModuleStatus());
    }

    @Override
    public void onTick() {

    }
}
