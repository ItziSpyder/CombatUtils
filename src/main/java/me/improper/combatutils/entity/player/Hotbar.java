package me.improper.combatutils.entity.player;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Hotbar {

    private ItemStack[] contents;
    private ItemStack offhandStack;
    private Player owner;

    public Hotbar(Player player) {
        this.contents = new ItemStack[9];
        this.offhandStack = player.getInventory().getItemInOffHand();
        this.owner = player;
        Inventory inv = player.getInventory();
        for (int i = 0; i < 9; i ++) {
            ItemStack item = inv.getItem(i);
            if (item != null) this.contents[i] = item;
            else this.contents[i] = new ItemStack(Material.AIR);
        }
    }

    public Hotbar(ItemStack[] contents) {
        this.contents = new ItemStack[9];
        this.owner = null;
        this.offhandStack = new ItemStack(Material.AIR);
        for (int i = 0; i < 9; i ++) {
            ItemStack item = contents[i];
            if (item != null) this.contents[i] = item;
            else this.contents[i] = new ItemStack(Material.AIR);
        }
    }

    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setOffhandStack(ItemStack offhandStack) {
        this.offhandStack = offhandStack;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public Player getOwner() {
        return owner;
    }

    public ItemStack getOffhandStack() {
        return offhandStack;
    }

    public boolean containsItem(ItemStack item) {
        for (int i = 0; i < 9; i ++)
            if (this.contents[i] == item || this.offhandStack == item) return true;
        return false;
    }

    public boolean containsItem(Material type) {
        for (int i = 0; i < 9; i ++)
            if (this.contents[i].getType().equals(type) || this.offhandStack.getType().equals(type)) return true;
        return false;
    }

    public void deductItem(ItemStack item, boolean ignoreGamemode) {
        if (this.owner != null && !ignoreGamemode && this.owner.getGameMode().equals(GameMode.CREATIVE)) return;
        for (int i = 0; i < 9; i ++) {
            if ((this.contents[i] == item || this.offhandStack == item) && item.getAmount() > 0) {
                item.setAmount(item.getAmount() - 1);
                break;
            }
        }
    }

    public void deductItem(Material type, boolean ignoreGamemode) {
        if (this.owner != null && !ignoreGamemode && this.owner.getGameMode().equals(GameMode.CREATIVE)) return;
        for (int i = 0; i < 9; i ++) {
            ItemStack item = this.contents[i];
            if ((item.getType().equals(type) || this.offhandStack.getType().equals(type)) && item.getAmount() > 0) {
                item.setAmount(item.getAmount() - 1);
                break;
            }
        }
    }

    public void deductItem(ItemStack item, int amount, boolean ignoreGamemode) {
        if (this.owner != null && !ignoreGamemode && this.owner.getGameMode().equals(GameMode.CREATIVE)) return;
        for (int i = 0; i < 9; i ++) {
            if ((this.contents[i] == item || this.offhandStack == item) && item.getAmount() > 0) {
                amount = (item.getAmount() - amount) >= 0 ? amount : item.getAmount();
                item.setAmount(item.getAmount() - amount);
                break;
            }
        }
    }

    public void deductItem(Material type, int amount, boolean ignoreGamemode) {
        if (this.owner != null && !ignoreGamemode && this.owner.getGameMode().equals(GameMode.CREATIVE)) return;
        for (int i = 0; i < 9; i ++) {
            ItemStack item = this.contents[i];
            if ((item.getType().equals(type) || this.offhandStack.getType().equals(type)) && item.getAmount() > 0) {
                amount = (item.getAmount() - amount) >= 0 ? amount : item.getAmount();
                item.setAmount(item.getAmount() - amount);
                break;
            }
        }
    }
}
