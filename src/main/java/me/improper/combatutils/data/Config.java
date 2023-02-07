package me.improper.combatutils.data;

import me.improper.combatutils.CombatUtils;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Config {

    private static final FileConfiguration CONFIG = CombatUtils.getInstance().getConfig();

    public class Plugin {
        public static String getPrefix() {
            return CONFIG.getString("config.plugin.prefix");
        }
        public static Character getCmdPrefixChar() {
            return CONFIG.getString("config.plugin.custom-command-prefix-character").charAt(0);
        }
        public static boolean getChatCmdPerm() {
            return CONFIG.getBoolean("config.plugin.custom-command-requires-permission");
        }
    }

    public class Gameplay {
        public static boolean getExplosionBlockDamage() {
            return CONFIG.getBoolean("config.gameplay.explosion-block-damage");
        }
        public static boolean getExplosionFire() {
            return CONFIG.getBoolean("config.gameplay.explosion-fire");
        }
        public static boolean getPingFastCrystal() {
            return CONFIG.getBoolean("config.gameplay.fast-crystal-for-high-ping");
        }
        public static int getPingFastCrystalPing() {
            return CONFIG.getInt("config.gameplay.fast-crystal-for-high-ping-ping", 200);
        }
        public static int getPingFastCrystalAmount() {
            return CONFIG.getInt("config.gameplay.fast-crystal-for-high-ping-amount", 1);
        }
        public static int getPingFastCrystalDelay() {
            return CONFIG.getInt("config.gameplay.fast-crystal-for-high-ping-delay", 3);
        }
    }
}
