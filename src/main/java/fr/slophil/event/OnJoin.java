package fr.slophil.event;

import fr.slophil.FuturaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class OnJoin implements Listener {

    private FuturaPlugin main;

    public OnJoin(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String configPath = "BloodVision." + player.getUniqueId() + ".Enabled";
        int onlinePlayers = 0;

        if (!(player.hasPlayedBefore()) || !(main.getConfig().contains(configPath))){
            main.getConfig().set(configPath, main.getConfig().getBoolean("DefaultBloodVision"));
            main.saveConfig();
        }

        for (Player forPlayer : Bukkit.getOnlinePlayers()){
            onlinePlayers++;
        }

        if (onlinePlayers == 1){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc reload");
            return;
        }
    }
}