package me.improper.combatutils.plugin;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.io.Serializable;
import java.util.*;

public class Profile implements Serializable {

    private String name;
    private UUID uuid;
    private Set<Module> modules;
    private boolean paused;
    private Set<Permission> permissions;

    public Profile(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.modules = new HashSet<>();
        this.paused = false;
        this.permissions = new HashSet<>();
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

    public void addPermission(Permission perm) {
        permissions.add(perm);
    }

    public void removePermission(Permission perm) {
        permissions.remove(perm);
    }

    public boolean hasPermission(Permission perm) {
        return permissions.contains(perm);
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }


    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }

    public Set<Module> getModules() {
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

    public void setModules(Set<Module> modules) {
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
