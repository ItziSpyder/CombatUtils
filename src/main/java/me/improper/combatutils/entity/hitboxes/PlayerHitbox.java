package me.improper.combatutils.entity.hitboxes;

import me.improper.combatutils.entity.Hitbox;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHitbox extends Hitbox {

    public PlayerHitbox(Player player) {
        Location loc = player.getLocation();
        Location body = loc.clone().add(0.3,0,0.3);
        Location body1 = loc.clone().add(-0.3,1.4,-0.3);
        Location head = loc.clone().add(0.3,1.4,0.3);
        Location head1 = loc.clone().add(-0.3,1.8,-0.3);
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
