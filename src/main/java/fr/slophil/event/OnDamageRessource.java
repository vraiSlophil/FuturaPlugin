package fr.slophil.event;

import fr.slophil.FuturaPlugin;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageRessource implements Listener {
    private FuturaPlugin main;

    public OnDamageRessource(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    @EventHandler
    public void OnDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player)event.getEntity();
            String playerWorld = player.getWorld().getName();
            Iterator var4 = this.main.getConfig().getStringList("NoDamageAllowedWolrds").iterator();

            while(var4.hasNext()) {
                String worldName = (String)var4.next();
                if (worldName.equalsIgnoreCase(playerWorld)) {
                    event.setCancelled(true);
                }
            }
        }

    }
}
