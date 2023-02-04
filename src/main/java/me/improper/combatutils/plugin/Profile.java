package me.improper.combatutils.plugin;

import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Profile implements Serializable {

    private String name;
    private UUID uuid;
    private List<Module> modules;
    private boolean paused;

    public Profile(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.modules = new ArrayList<>();
        this.paused = false;
        for (Modules mods : Modules.values()) {
            Module module = mods.getModule().clone();
            module.setEventPlayer(player);
            module.setEnabled(false);
            modules.add(module);
        }
    }

    public Module getModuleObject(String name) {
        for (Module mod : this.modules) {
            String[] segments = mod.getClass().getName().split("\\.");
            if (segments[segments.length - 1].equalsIgnoreCase(name)) return mod;
        }
        return null;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<String> listModules() {
        List<String> list = new ArrayList<>();
        for (Module mod : this.modules) {
            String[] segments = mod.getClass().getName().split("\\.");
            list.add(segments[segments.length - 1]);
        }
        return list;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
