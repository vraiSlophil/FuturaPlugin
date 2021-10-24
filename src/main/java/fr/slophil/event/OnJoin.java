package fr.slophil.event;

import fr.slophil.FuturaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    private FuturaPlugin main;

    public OnJoin(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String configPath = "BloodVision." + player.getUniqueId() + ".Enabled";

        if (!(player.hasPlayedBefore()) || !(main.getConfig().contains(configPath))){
            main.getConfig().set(configPath, main.getConfig().getBoolean("DefaultBloodVision"));
        }
    }




}

