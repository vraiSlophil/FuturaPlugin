package fr.slophil.other;

import fr.slophil.FuturaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;

public class DailyCCReload extends BukkitRunnable {

    private FuturaPlugin main;
    public DailyCCReload(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    LocalTime configTime = LocalTime.parse(main.getConfig().getString("Time"));
    LocalTime nowTime = LocalTime.now();

    @Override
    public void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                if (nowTime.compareTo(configTime) == 0){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc reload");
                }
            }
        },0,20*60);
    }
}
