package me.improper.combatutils.event;

import me.improper.combatutils.CombatUtils;
import me.improper.combatutils.plugin.Module;
import me.improper.combatutils.plugin.Profile;
import me.improper.combatutils.plugin.ServerSound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class OnDamage implements Listener {

    @EventHandler
    public static void onDamage(EntityDamageEvent e) {
        try {
            Entity entity = e.getEntity();

            if (entity instanceof Player p ) {
                Profile profile = Profile.getProfile(p);
                switch (e.getCause()) {
                    case FALL -> {
                       Module noFall = profile.getModuleObject("NoFall");
                       if (noFall.isEnabled()) e.setCancelled(true);
                    }
                }

                Module autoTotem = profile.getModuleObject("AutoTotem");
                if (autoTotem.isEnabled() && p.getInventory().contains(Material.TOTEM_OF_UNDYING) && p.getHealth() - e.getFinalDamage() <= 0) {
                    replenishTotem(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(CombatUtils.getInstance(),() -> replenishTotem(p),1);
                }
            }
        } catch (Exception ex) {}
    }

    private static void replenishTotem(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType().equals(Material.TOTEM_OF_UNDYING)) {
                if (p.getInventory().getItemInOffHand().getType().isAir() && !p.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                    p.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
                    item.setAmount(item.getAmount() - 1);
                    p.updateInventory();
                    break;
                }
                else if (p.getInventory().getItemInMainHand().getType().isAir() && !p.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                    p.getInventory().setItemInMainHand(new ItemStack(Material.TOTEM_OF_UNDYING));
                    item.setAmount(item.getAmount() - 1);
                    p.updateInventory();
                    break;
                }
            }
        }
    }

    @EventHandler
    public static void onDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            double damage = e.getDamage();
            Entity victim = e.getEntity();
            Entity damager = e.getDamager();

            if (damager instanceof Player p && victim instanceof LivingEntity) {
                Profile profile = Profile.getProfile(p);
                Module criticals = profile.getModuleObject("Criticals");
                ServerSound crit = new ServerSound(victim.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
                if (criticals.isEnabled() && p.getAttackCooldown() >= 0.8) {
                    e.setDamage(damage * 1.5);
                    crit.playWithin(100);
                    victim.getWorld().spawnParticle(Particle.CRIT, victim.getLocation().clone().add(0, 1.2, 0), 25, 0, 0, 0, 0.55);
                }
            }
            else if (damager instanceof LivingEntity && victim instanceof Player p) {
                Profile profile = Profile.getProfile(p);
                Module dodge = profile.getModuleObject("Dodge");
                if (dodge.isEnabled()) {
                    if (Math.floor(ran(0,10)) >= 7) e.setCancelled(true);
                    p.setVelocity(p.getVelocity().add(new Vector(ran(-2,2),ran(0,1),ran(-2,2))));
                }
            }
        } catch (Exception ex) {
        }
    }

    private static double ran(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    @EventHandler
    public static void onVelocity(PlayerVelocityEvent e) {
        Player p = e.getPlayer();
        Profile profile = Profile.getProfile(p);
        Module antiKb = profile.getModuleObject("AntiKb");
        if (antiKb.isEnabled()) e.setCancelled(true);
    }
}
