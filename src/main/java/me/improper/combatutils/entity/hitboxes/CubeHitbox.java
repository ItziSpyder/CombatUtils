package me.improper.combatutils.entity.hitboxes;

import me.improper.combatutils.entity.Hitbox;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class CubeHitbox extends Hitbox {

    public CubeHitbox(Entity entity) {
        Location loc = entity.getLocation();
        Location body = loc.clone().add(0.5,0,0.5);
        Location body1 = loc.clone().add(-0.5,1,-0.5);
        Location head = loc.clone().add(0.5,0,0.5);
        Location head1 = loc.clone().add(-0.5,1,-0.5);
        super.setX(body.getX());
        super.setY(body.getY());
        super.setZ(body.getZ());
        super.setX1(body1.getX());
        super.setY1(body1.getY());
        super.setZ1(body1.getZ());
        super.setHx(head.getX());
        super.setHy(head.getY());
        super.setHz(head.getZ());
        super.setHx1(head1.getX());
        super.setHy1(head1.getY());
        super.setHz1(head1.getZ());
    }

}
