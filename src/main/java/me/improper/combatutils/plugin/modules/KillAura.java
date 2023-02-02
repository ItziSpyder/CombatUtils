package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.plugin.IModule;
import me.improper.combatutils.plugin.Module;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KillAura extends Module implements IModule {

    private double damage;

    public KillAura() {
        super("KillAura");
        this.damage = 1;
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
        if (!super.isEnabled()) return;
        Player p = super.getEventPlayer();
        if (p == null) return;
        Location loc = p.getLocation();

        if (p.getAttackCooldown() < 0.8) return;
        int hits = 0;
        for (Entity entity : loc.getWorld().getNearbyEntities(loc,7,7,7)) {
            if (entity instanceof LivingEntity victim && entity != p && !entity.isDead()) {
                p.attack(victim);
                Vector dir = entity.getLocation().toVector().subtract(p.getLocation().toVector());
                Location newLoc = loc.clone();
                newLoc.setDirection(dir);
                p.teleport(newLoc);
                if (hits ++ >= 5) break;
            }
        }
    }
}
