package me.improper.combatutils.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {

    public static List<String> listPlayers() {
        List<String> list = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> list.add(player.getName()));
        return list;
    }

    public static List<String> listStaff() {
        List<String> list = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {if (player.isOp()) list.add(player.getName());});
        return list;
    }

    public static String buildArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        return builder.toString().trim();
    }

    public static String buildArgs(String[] args, int beginIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = beginIndex; i < args.length; i ++) builder.append(args[i]).append(" ");
        return builder.toString().trim();
    }

    public static String generateLargeString() {
        return "\n ".repeat(690);
    }

    public static LivingEntity getClosestEntity(Location location, int radius) {
        LivingEntity closest = null;
        double distance = radius;
        for (Entity entity : location.getWorld().getNearbyEntities(location,radius,radius,radius)) {
            double curDist = location.distance(entity.getLocation());
            if (entity instanceof LivingEntity living && curDist < distance && !entity.isDead()) {
                closest = living;
                distance = curDist;
            }
        }
        return closest;
    }

    public static LivingEntity getClosestEntity(Entity center, int radius) {
        LivingEntity closest = null;
        double distance = radius;
        for (Entity entity : center.getWorld().getNearbyEntities(center.getLocation(),radius,radius,radius)) {
            double curDist = center.getLocation().distance(entity.getLocation());
            if (entity instanceof LivingEntity living && entity != center && curDist < distance && !entity.isDead()) {
                closest = living;
                distance = curDist;
            }
        }
        return closest;
    }
}
