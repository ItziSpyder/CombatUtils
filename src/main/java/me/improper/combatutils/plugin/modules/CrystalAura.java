package me.improper.combatutils.plugin.modules;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.entity.player.Hotbar;
import me.improper.combatutils.entity.player.HotbarLoader;
import me.improper.combatutils.geometry.shapes.Cube;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.ServerSound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CrystalAura extends Module {

    public CrystalAura() {
        super("CrystalAura");
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
        if (p == null) return;
        Hotbar hotbar = HotbarLoader.from(p);
        Location loc = p.getLocation();
        Cube cube = new Cube(loc.clone().add(6,6,6),loc.clone().add(-6,-6,-6));

        for (Block block : cube.blockList()) {
            Location bLoc = block.getLocation();
            for (Entity entity : bLoc.getWorld().getNearbyEntities(bLoc,2,2,2)) {
                if (entity != p && entity instanceof LivingEntity && !entity.isDead() && hotbar.containsItem(Material.END_CRYSTAL)) {
                    Block underBlock = entity.getLocation().clone().add(0,-1,0).getBlock();
                    Block feetBlock = entity.getLocation().clone().getBlock();
                    if (isCrystalable(underBlock.getType()) && feetBlock.getType().equals(Material.AIR)) {
                        EnderCrystal crystal = entity.getWorld().spawn(feetBlock.getLocation().clone().add(0.5,0,0.5),EnderCrystal.class);
                        crystal.setShowingBottom(false);
                        hotbar.deductItem(Material.END_CRYSTAL,false);
                        explode(crystal);
                        return;
                    }
                    if (block.getType().equals(Material.AIR) && hotbar.containsItem(Material.OBSIDIAN)) {
                        block.setType(Material.OBSIDIAN);
                        hotbar.deductItem(Material.OBSIDIAN,false);
                        ServerSound place = new ServerSound(bLoc,Sound.BLOCK_STONE_PLACE,1,0.7F);
                        place.playWithin(200);
                    }
                    if (isCrystalable(block.getType()) && bLoc.clone().add(0,1,0).getBlock().getType().equals(Material.AIR)) {
                        EnderCrystal crystal = bLoc.getWorld().spawn(bLoc.clone().add(0.5,1,0.5),EnderCrystal.class);
                        crystal.setShowingBottom(false);
                        hotbar.deductItem(Material.END_CRYSTAL,false);
                        explode(crystal);
                        return;
                    }
                }
            }
        }
    }

    private static boolean isCrystalable(Material type) {
        String name = type.name();
        return (name.contains("OBSIDIAN") && !name.contains("CRYING_")) ||
                name.contains("BEDROCK");
    }

    public void explode(EnderCrystal crystal) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
            Location location = crystal.getLocation();
            location.getWorld().createExplosion(location,5,false,
                    Config.Gameplay.getExplosionBlockDamage(),
                    super.getEventPlayer());
            ServerSound sound = new ServerSound(location, Sound.ENTITY_GENERIC_EXPLODE,1,0.7F);
            sound.playWithin(5000);
            crystal.remove();
        },1);
    }
}
