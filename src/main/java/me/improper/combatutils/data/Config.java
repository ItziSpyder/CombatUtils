package me.improper.combatutils.data;

import me.improper.combatutils.CombatUtils;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Config {

    private static FileConfiguration CONFIG = CombatUtils.getInstance().getConfig();

    public class PLUGIN {
        public static String getPrefix() {
            return CONFIG.getString("config.plugin.prefix");
        }
    }
}
