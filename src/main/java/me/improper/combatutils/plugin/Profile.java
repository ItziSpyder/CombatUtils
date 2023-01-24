package me.improper.combatutils.plugin;

import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile implements Serializable {

    String name;
    UUID uuid;
    List<Module> modules;

    public Profile(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.modules = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
