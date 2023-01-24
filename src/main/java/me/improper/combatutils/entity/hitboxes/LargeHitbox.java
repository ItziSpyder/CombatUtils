package me.improper.combatutils.entity.hitboxes;

import me.improper.combatutils.entity.Hitbox;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class LargeHitbox extends Hitbox {

    public LargeHitbox(LivingEntity entity) {
        Location loc = entity.getLocation();
        Location body = loc.clone().add(0.75,0,0.75);
        Location body1 = loc.clone().add(-0.75,2,-0.75);
        Location head = loc.clone().add(0.75,2,0.75);
        Location head1 = loc.clone().add(-0.75,3,-0.75);
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
