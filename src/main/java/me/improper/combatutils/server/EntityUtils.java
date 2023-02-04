package me.improper.combatutils.server;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public abstract class EntityUtils {

    public static LivingEntity getClosestEntity(Location location, int radius) {
        LivingEntity closest = null;
        double distance = radius;
        for (Entity entity : location.getWorld().getNearbyEntities(location,radius,radius,radius)) {
            double curDist = location.distance(entity.getLocation());
            if (!(entity instanceof LivingEntity)) continue;
            if (curDist >= distance || entity.isDead()) continue;
            closest = (LivingEntity) entity;
            distance = curDist;
        }
        return closest;
    }

    public static LivingEntity getClosestEntity(Entity center, int radius) {
        LivingEntity closest = null;
        double distance = radius;
        for (Entity entity : center.getWorld().getNearbyEntities(center.getLocation(),radius,radius,radius)) {
            double curDist = center.getLocation().distance(entity.getLocation());
            if (!(entity instanceof LivingEntity)) continue;
            if (curDist >= distance || entity.isDead()) continue;
            closest = (LivingEntity) entity;
            distance = curDist;
        }
        return closest;
    }
}
