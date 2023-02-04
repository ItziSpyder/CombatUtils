package me.improper.combatutils.plugin;

import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class ProfileLoader {

    public static HashMap<String,Profile> profiles = new HashMap<>();

    public static Profile loadProfile(Player player) {
        Profile profile = profiles.get(player.getName());
        if (profile != null) return profile;
        profile = new Profile(player);
        profiles.put(player.getName(),profile);
        return profile;
    }
}
