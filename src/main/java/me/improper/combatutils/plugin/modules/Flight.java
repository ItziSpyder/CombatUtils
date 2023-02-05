package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.plugin.Module;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Flight extends Module {

    private float ogSpeed;

    public Flight() {
        super("Flight");
    }

    @Override
    public void onEnable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(true);
        p.sendMessage(super.getModuleStatus());

        this.ogSpeed = p.getFlySpeed();
        p.setAllowFlight(true);
        p.setVelocity(p.getVelocity().add(new Vector(0,1,0)));
        p.setFlySpeed(0.3F);
        p.setFlying(true);
    }

    @Override
    public void onDisable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(false);
        p.sendMessage(super.getModuleStatus());

        p.setFlying(false);
        if (!p.getGameMode().equals(GameMode.CREATIVE)) p.setAllowFlight(false);
        p.setFlySpeed(0.21F);
    }

    @Override
    public void onTick() {

    }
}
