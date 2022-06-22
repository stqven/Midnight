package me.inv.own;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Utilities {
    public static void midnightChecker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long worldTime = Bukkit.getServer().getWorld(Config.getWorldName()).getTime();
                int midnightTime = 18000;
                if (worldTime >= midnightTime && worldTime < (midnightTime + 20)) midEvent();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L); //Delays in ticks
    }

    public static void midEvent() {
        HashMap<PotionEffect, Sound> data = Config.getActivePotions();
        Set<PotionEffect> setPotions = data.keySet();
        ArrayList<PotionEffect> activePotions = new ArrayList<>();
        for (PotionEffect pe : setPotions) {
            activePotions.add(pe);
        }
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            int rand = givePlayerPotion(p, Config.getNumberOfPotions(),activePotions,0);
            Sound sound = data.get(activePotions.get(rand));
            if (sound != null) p.playSound(p.getLocation(), sound, 10, 10);
            String[] titles = Config.getTitles();
            p.sendTitle((titles[0] != null)? titles[0] : "", (titles[1] != null)? titles[1] : "", 10, Config.getTitleDuration(), 20);
            String bmsg = Config.getBroadcastMessage();
            if (bmsg != null && !bmsg.equals("")) Bukkit.broadcastMessage(bmsg);
        }
    }

    public static int givePlayerPotion(Player p, int times, ArrayList<PotionEffect> potions, int sound) {
        if (times == 0 || potions.size() == 0) return sound;
        int random = (new Random()).nextInt(potions.size());
//        p.addPotionEffect(new PotionEffect(potions.get(random).getType(), 10000, 1));
        p.addPotionEffect(potions.get(random));
        potions.remove(random);
        return givePlayerPotion(p, --times, potions, random);
    }
}
