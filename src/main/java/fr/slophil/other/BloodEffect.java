package fr.slophil.other;

import fr.slophil.FuturaPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BloodEffect implements Listener {
    private FuturaPlugin main;

    public BloodEffect(FuturaPlugin futuraPlugin) {this.main = futuraPlugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        Location eventLocation = entity.getLocation();
        EntityType entType = entity.getType();

        if (entity instanceof Player){
            Player player = (Player) entity;
            String configPath = "BloodVision." + player.getUniqueId() + ".Enabled";

            if (main.getConfig().contains(configPath)){
                if (main.getConfig().getBoolean(configPath)){
                    spawnParticles(eventLocation, entity);
                    if (Math.random() < 0.20) {
                        String message = this.main.color('&', this.main.getConfig().getString("DoBlood"));
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        return;
                    }
                    return;
                }
            }
        } else if (!entType.equals(EntityType.ITEM_FRAME) && !entType.equals(EntityType.ARMOR_STAND) && !entType.equals(EntityType.ARROW) && !entType.equals(EntityType.BOAT) && !entType.equals(EntityType.DRAGON_FIREBALL) && !entType.equals(EntityType.DROPPED_ITEM) && !entType.equals(EntityType.ENDER_CRYSTAL) && !entType.equals(EntityType.FALLING_BLOCK) && !entType.equals(EntityType.PAINTING) && !entType.equals(EntityType.MINECART_TNT) && !entType.equals(EntityType.MINECART_CHEST) && !entType.equals(EntityType.MINECART_COMMAND) && !entType.equals(EntityType.MINECART_FURNACE) && !entType.equals(EntityType.MINECART_HOPPER) && !entType.equals(EntityType.MINECART_MOB_SPAWNER)) {
            spawnParticles(eventLocation, entity);
            return;
        }
    }

    private void spawnParticles(Location eventLocation, Entity entity){
        eventLocation.setY(eventLocation.getY() + 1.0D);
        entity.getWorld().spawnParticle(Particle.REDSTONE, eventLocation, 3, 0.001D, 0.5D, 0.0D, 0.2D, new DustOptions(Color.RED, 3.0F));
    }
}
