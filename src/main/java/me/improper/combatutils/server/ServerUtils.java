package me.improper.combatutils.server;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {

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

    public static String buildArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        return builder.toString().trim();
    }

    public static String buildArgs(String[] args, int beginIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = beginIndex; i < args.length; i ++) builder.append(args[i]).append(" ");
        return builder.toString().trim();
    }

    public static String generateLargeString() {
        return "\n ".repeat(690);
    }
}
