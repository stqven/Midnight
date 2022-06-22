package me.inv.own;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.List;

public class Config {
    public static HashMap<PotionEffect, Sound> getActivePotions() {
        HashMap<PotionEffect, Sound> activePotions = new HashMap<>();
        List<String> configPotions = Main.getInstance().getConfig().getStringList("active-potions");
        for (String str : configPotions) {
            try {
                String[] info = str.split(", ");
                PotionEffectType type = null;
                Sound sound = null;
                for (PotionEffectType potionI : PotionEffectType.values()) {
                    if (potionI.toString().contains(info[0])) type = potionI;
                }
                for (Sound soundI : Sound.values()) {
                    if (soundI.toString().contains(info[3])) sound = soundI;
                }
                int duration = Integer.parseInt(info[1])*20;
                int amplifier = Integer.parseInt(info[2]);
                if (type == null) {
                    printError("[Midnight] (" + str + ") Potion Effect should have a value");
                }
                activePotions.put(new PotionEffect(type, duration, amplifier), sound);
            } catch (NumberFormatException e) {
                printError("[Midnight] (" + str + ") Duration and Amplifier should be numbers");
            }
        }
        return activePotions;
    }

    public static int getNumberOfPotions() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        return (cfg.contains("potions-per-time"))? cfg.getInt("potions-per-time") : 1;
    }

    public static void printError(String msg) {
        Main.getInstance().getLogger().warning(msg);
    }

    public static String getWorldName() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        return (cfg.contains("world-name"))? cfg.getString("world-name") : null;
    }

    public static String getPermission() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        return (cfg.contains("command-permission"))? cfg.getString("command-permission") : "";
    }

    public static String getBroadcastMessage() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        return (cfg.contains("broadcast-message"))? cfg.getString("broadcast-message").replaceAll("&", "§") : "";
    }

    public static int getTitleDuration() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        return (cfg.contains("title-duration"))? cfg.getInt("title-duration")*20 : 1;
    }

    public static String[] getTitles() {
        FileConfiguration cfg = Main.getInstance().getConfig();
        String[] titles = new String[2];
        titles[0] = (cfg.contains("broadcast-title"))? cfg.getString("broadcast-title").replaceAll("&", "§") : "";
        titles[1] = (cfg.contains("broadcast-subtitle"))? cfg.getString("broadcast-subtitle").replaceAll("&", "§") : "";
        return titles;
    }
}
