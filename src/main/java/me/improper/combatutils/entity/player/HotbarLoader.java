package me.improper.combatutils.entity.player;

import org.bukkit.entity.Player;

public abstract class HotbarLoader {

    public static void set(Player player, Hotbar hotbar) {
        for (int i = 0; i < 9; i ++)
            player.getInventory().setItem(i,hotbar.getContents()[i]);
        player.getInventory().setItemInOffHand(hotbar.getOffhandStack());
    }

    public static Hotbar from(Player player) {
        return new Hotbar(player);
    }
}
