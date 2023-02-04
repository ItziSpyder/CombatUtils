package me.improper.combatutils.data;

import me.improper.combatutils.CombatUtils;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Config {

    private static FileConfiguration CONFIG = CombatUtils.getInstance().getConfig();

    public class PLUGIN {
        public static String getPrefix() {
            return CONFIG.getString("config.plugin.prefix");
        }
        public static Character getCmdPrefixChar() {
            return CONFIG.getString("config.plugin.custom-command-prefix-character").charAt(0);
        }
    }

    public class GAMEPLAY {
        public static boolean getExplosionBlockDamage() {
            return CONFIG.getBoolean("config.gameplay.explosion-block-damage");
        }
        public static boolean getExplosionFire() {
            return CONFIG.getBoolean("config.gameplay.explosion-fire");
        }
    }
}
