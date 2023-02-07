package me.improper.combatutils.event;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.entity.player.Hotbar;
import me.improper.combatutils.entity.player.HotbarLoader;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnCrystal implements Listener {

    @EventHandler
    public static void onClick(EntityDamageByEntityEvent e) {
        Entity victim = e.getEntity();
        Entity damager = e.getDamager();
        try {
            if (damager instanceof Player p && victim instanceof EnderCrystal crystal) {
                Hotbar hotbar = HotbarLoader.from(p);
                Profile profile = ProfileLoader.loadProfile(p);
                Module fastCrystal = profile.getModuleObject("FastCrystal");
                if (crystal.getScoreboardTags().contains("§8combatutils:cw-crystal")) return;
                if (!hotbar.containsItem(Material.END_CRYSTAL)) return;
                if (!(p.getPing() >= 150 && Config.Gameplay.getPingFastCrystal()) && !fastCrystal.isEnabled()) return;
                hotbar.deductItem(Material.END_CRYSTAL,false);
                Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
                    Location spawnLoc = crystal.getLocation().clone();
                    EnderCrystal newCrystal = p.getWorld().spawn(spawnLoc,EnderCrystal.class);
                    newCrystal.setShowingBottom(false);
                    newCrystal.addScoreboardTag("§8combatutils:cw-crystal");
                    detonateCrystal(newCrystal,p);
                },3);
            }
        } catch (Exception ex) {}
    }

    private static void detonateCrystal(EnderCrystal crystal, HumanEntity entity) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
            if (crystal == null || crystal.isDead()) return;
            if (entity == null || entity.isDead()) return;
            entity.attack(crystal);
        },1);
    }
}
