package me.improper.combatutils.event;

import me.improper.combatutils.entity.hitboxes.LargeHitbox;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class OnClick implements Listener {

    @EventHandler
    public static void onPlayerClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            ItemStack item = e.getItem();
            Profile profile = Profile.getProfile(p);
            switch (e.getAction()) {
                case LEFT_CLICK_AIR -> {
                    Module reach = profile.getModuleObject("Reach");
                    if (reach.isEnabled()) {
                        LivingEntity entity = getRaycasted(p);
                        p.attack(entity);
                    }
                }
            }
        } catch (Exception exception) { }
    }

    public static LivingEntity getRaycasted(Player p) {
        Location loc = p.getEyeLocation();
        Vector dir = p.getLocation().getDirection();
        for (double distance = 0; distance < 10; distance += 0.3) {
            double x = dir.getX() * distance;
            double y = dir.getY() * distance;
            double z = dir.getZ() * distance;
            Location newLoc = loc.clone().add(x,y,z);
            for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc,2,2,2))
                if (entity instanceof LivingEntity living && entity != p && new LargeHitbox(living).inBounds(newLoc))
                    return living;
        }
        return null;
    }
}
