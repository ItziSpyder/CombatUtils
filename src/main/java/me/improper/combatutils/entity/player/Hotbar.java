package me.improper.combatutils.entity.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Hotbar {

    public static void setHotbar(Player player, Hotbar hotbar) {
        for (int i = 0; i < 9; i ++) {
            player.getInventory().setItem(i,hotbar.getContents()[i]);
        }
    }

    public static Hotbar getHotbar(Player player) {
        return new Hotbar(player);
    }

    private ItemStack[] contents;

    public Hotbar(Player player) {
        this.contents = new ItemStack[9];
        Inventory inv = player.getInventory();
        for (int i = 0; i < 9; i ++) {
            ItemStack item = inv.getItem(i);
            if (item != null) this.contents[i] = item;
            else this.contents[i] = new ItemStack(Material.AIR);
        }
    }

    public Hotbar(ItemStack[] contents) {
        this.contents = new ItemStack[9];
        for (int i = 0; i < 9; i ++) {
            ItemStack item = contents[i];
            if (item != null) this.contents[i] = item;
            else this.contents[i] = new ItemStack(Material.AIR);
        }
    }

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public boolean containsItem(ItemStack item) {
        for (int i = 0; i < 9; i ++) {
            if (this.contents[i] == item) return true;
        }
        return false;
    }

    public boolean containsItem(Material type) {
        for (int i = 0; i < 9; i ++) {
            if (this.contents[i].getType().equals(type)) return true;
        }
        return false;
    }

    public void deductItem(ItemStack item) {
        for (int i = 0; i < 9; i ++) {
            if (this.contents[i] == item && item.getAmount() > 0) {
                item.setAmount(item.getAmount() - 1);
                break;
            }
        }
    }

    public void deductItem(Material type) {
        for (int i = 0; i < 9; i ++) {
            ItemStack item = this.contents[i];
            if (item.getType().equals(type) && item.getAmount() > 0) {
                item.setAmount(item.getAmount() - 1);
                break;
            }
        }
    }

    public void deductItem(ItemStack item, int amount) {
        for (int i = 0; i < 9; i ++) {
            if (this.contents[i] == item && item.getAmount() > 0) {
                amount = (item.getAmount() - amount) > 0 ? amount : (item.getAmount() - 1);
                item.setAmount(item.getAmount() - amount);
                break;
            }
        }
    }

    public void deductItem(Material type, int amount) {
        for (int i = 0; i < 9; i ++) {
            ItemStack item = this.contents[i];
            if (item.getType().equals(type) && item.getAmount() > 0) {
                amount = (item.getAmount() - amount) > 0 ? amount : (item.getAmount() - 1);
                item.setAmount(item.getAmount() - amount);
                break;
            }
        }
    }
}
