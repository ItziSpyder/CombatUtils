package me.improper.combatutils.event;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.data.Config;
import me.improper.combatutils.entity.player.Hotbar;
import me.improper.combatutils.entity.player.HotbarLoader;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ProfileLoader;
import me.improper.combatutils.plugin.ServerSound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
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
                },1);
            }
        } catch (Exception ex) {}
    }

    public static void detonateCrystal(EnderCrystal crystal, Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> {
            Location location = crystal.getLocation();
            location.getWorld().createExplosion(location,6,false,
                    Config.Gameplay.getExplosionBlockDamage());
            ServerSound sound = new ServerSound(location, Sound.ENTITY_GENERIC_EXPLODE,1,0.7F);
            sound.playWithin(5000);
            crystal.remove();
        },1);
    }
}
