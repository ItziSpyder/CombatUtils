package me.improper.combatutils.geometry;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private List<Block> blocks;

    public Shape() {
        this.blocks = new ArrayList<>();
    }

    public List<Block> blockList() {
        return this.blocks;
    }

    public void add(Block block) {
        this.blocks.add(block);
    }

    public void remove(Block block) {
        this.blocks.remove(block);
    }

    public void setType(Material type) {
        for (Block block : this.blocks) block.setType(type);
    }
}
