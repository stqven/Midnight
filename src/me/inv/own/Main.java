package me.inv.own;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public void onEnable() {
        saveDefaultConfig();
        System.out.println("[Midnight] Plugin has been enabled!");
        Utilities.midnightChecker();
        getCommand("midnight").setExecutor(new MidnightCommand());
    }

    public void onDisable() {
        System.out.println("[Midnight] Plugin has been disabled!");
    }

    public void onLoad() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }
}
