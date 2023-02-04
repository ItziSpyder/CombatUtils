package me.improper.combatutils.event;

import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import me.improper.combatutils.server.EntityUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class OnProjectile implements Listener {

    @EventHandler
    public static void onShoot(ProjectileLaunchEvent e) {
        Entity proj = e.getEntity();

        try {
            ProjectileSource src = e.getEntity().getShooter();
            if (src == null) return;
            if (src instanceof Player p) {
                Profile profile = ProfileLoader.loadProfile(p);
                Module aimBot = profile.getModuleObject("AimBot");
                if (aimBot.isEnabled()) {
                    Entity ent = EntityUtils.getClosestEntity(p,30);
                    if (ent instanceof LivingEntity living && !ent.isDead() && ent != src && ent != proj) {
                        Vector dir = living.getEyeLocation().toVector().subtract(p.getEyeLocation().toVector()).normalize().multiply(4.5);
                        proj.setVelocity(dir);
                    }
                }
            }
        } catch (Exception ex) {}
    }
}
