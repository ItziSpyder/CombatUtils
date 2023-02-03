package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.entity.player.Hotbar;
import me.improper.combatutils.geometry.shapes.Sphere;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.ServerSound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AnchorAura extends Module {


    public AnchorAura() {
        super("AnchorAura");
    }

    @Override
    public void onEnable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(true);
        p.sendMessage(super.getModuleStatus());
    }

    @Override
    public void onDisable() {
        Player p = super.getEventPlayer();
        if (p == null) return;
        super.setEnabled(false);
        p.sendMessage(super.getModuleStatus());
    }

    @Override
    public void onTick() {
        if (!super.isEnabled()) return;
        Player p = super.getEventPlayer();
        Hotbar hotbar = Hotbar.getHotbar(p);
        if (p == null) return;
        Location loc = p.getLocation();
        Sphere sphere = new Sphere(loc,50);

        for (Block block : sphere.blockList()) {
            Location bLoc = block.getLocation();
            for (Entity entity : bLoc.getWorld().getNearbyEntities(bLoc,2,2,2)) {
                if (entity instanceof LivingEntity && entity != p && !entity.isDead() && block.isEmpty() && hotbar.containsItem(Material.GLOWSTONE) && hotbar.containsItem(Material.RESPAWN_ANCHOR)) {
                    block.setType(Material.RESPAWN_ANCHOR);
                    RespawnAnchor anchor = (RespawnAnchor) block.getBlockData();
                    anchor.setCharges(1);
                    block.setBlockData(anchor);
                    ServerSound sound = new ServerSound(bLoc, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE,1,0.7F);
                    sound.playWithin(100);
                    explode(block);
                    hotbar.deductItem(Material.GLOWSTONE,false);
                    hotbar.deductItem(Material.RESPAWN_ANCHOR,false);
                    return;
                }
            }
        }
    }

    public void explode(Block block) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
            block.setType(Material.AIR);
            block.getWorld().createExplosion(block.getLocation(),5,
                    Config.GAMEPLAY.getExplosionFire(),
                    Config.GAMEPLAY.getExplosionBlockDamage(),
                    super.getEventPlayer());
            ServerSound sound = new ServerSound(block.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,1,0.7F);
            sound.playWithin(5000);
        },4);
    }
}
