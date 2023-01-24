package me.improper.combatutils.geometry.shapes;

import me.improper.combatutils.geometry.Shape;
import org.bukkit.Location;

public class Cube extends Shape {

    public Cube(Location corner, Location corner1) {
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
                    super.add(loc.getBlock());
                }
            }
        }
    }

}
