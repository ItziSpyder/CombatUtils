package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.geometry.shapes.Sphere;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.ServerSound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class AnchorAura extends Module {


    public AnchorAura() {
        super("AnchorAura");
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onTick() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        Location loc = p.getLocation();
        Sphere sphere = new Sphere(loc,30);
        for (Block block : sphere.blockList()) {
            Location bLoc = block.getLocation();
            for (Entity entity : bLoc.getWorld().getNearbyEntities(bLoc, 2, 2, 2)) {
                if (entity != null && entity != p && !entity.isDead() && block.isEmpty()) {
                    block.setType(Material.RESPAWN_ANCHOR);
                    ServerSound sound = new ServerSound(bLoc, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE,1,0.7F);
                    sound.playWithin(100);
                    explode(block);
                    return;
                }
            }
        }
    }

    public void explode(Block block) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
            block.setType(Material.AIR);
            block.getWorld().createExplosion(block.getLocation(),5,true,true,super.getEventPlayer());
            ServerSound sound = new ServerSound(block.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,1,0.7F);
            sound.playWithin(5000);
        },4);
    }
}
