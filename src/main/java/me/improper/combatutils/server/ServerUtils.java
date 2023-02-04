package me.improper.combatutils.server;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerUtils {

    public static List<String> listPlayers() {
        List<String> list = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> list.add(player.getName()));
        return list;
    }

    public static List<String> listStaff() {
        List<String> list = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {if (player.isOp()) list.add(player.getName());});
        return list;
    }
}
