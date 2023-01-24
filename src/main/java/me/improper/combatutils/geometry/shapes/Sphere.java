package me.improper.combatutils.geometry.shapes;

import me.improper.combatutils.geometry.Shape;
import org.bukkit.Location;

public class Sphere extends Shape {

    public Sphere(Location center, int radius) {
        Location corner = center.clone().add(radius,radius,radius);
        Location corner1 = center.clone().add(-radius,-radius,-radius);
        int[] min = {
                Math.min(corner.getBlockX(),corner1.getBlockX()),
                Math.min(corner.getBlockY() ,corner1.getBlockY()),
                Math.min(corner.getBlockZ(),corner1.getBlockZ())
        };
        int[] max = {
                Math.max(corner.getBlockX(),corner1.getBlockX()),
                Math.max(corner.getBlockY() ,corner1.getBlockY()),
                Math.max(corner.getBlockZ(),corner1.getBlockZ())
        };
        for (int x = min[0]; x <= max[0]; x++) {
            for (int y = min[1]; y <= max[1]; y++) {
                for (int z = min[2]; z <= max[2]; z++) {
                    Location loc = new Location(corner.getWorld(),x,y,z);
                    if (loc.distanceSquared(center) <= radius) super.add(loc.getBlock());
                }
            }
        }
    }

}
