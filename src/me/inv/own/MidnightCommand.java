package me.inv.own;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MidnightCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("midnight")) {
            Player p = (Player) sender;
            String perm = Config.getPermission();
            if ((perm != null && !perm.equals("")) && (p.hasPermission(perm) || p.isOp())) {
                long rt = 18000 - Bukkit.getWorld(Config.getWorldName()).getTime();
                if (rt < 0) rt += 24000;
                long seconds = rt/20;
                p.sendMessage("§eRemaining Time: §f" + ((seconds != 0)? (seconds > 1)? (seconds + " seconds") : (seconds + " second") : "Now!" ));
            } else {
                p.sendMessage("§cYou don't have enough permissions to execute this command");
            }
        }
        return false;
    }
}
