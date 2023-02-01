package me.improper.combatutils.event;

import me.improper.combatutils.plugin.modules.AnchorAura;
import me.improper.combatutils.plugin.modules.CrystalAura;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnClick implements Listener {

    @EventHandler
    public static void onPlayerClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            ItemStack item = e.getItem();
            switch (item.getType()) {
                case RESPAWN_ANCHOR -> {
                    AnchorAura aa = new AnchorAura();
                    aa.setEnabled(true);
                    aa.setEventPlayer(p);
                    aa.onTick();
                }
                case END_CRYSTAL -> {
                    CrystalAura aa = new CrystalAura();
                    aa.setEnabled(true);
                    aa.setEventPlayer(p);
                    aa.onTick();
                }
            }
        } catch (Exception exception) { }
    }
}
