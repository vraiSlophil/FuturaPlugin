package fr.slophil.event;

import fr.slophil.FuturaPlugin;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class OnMiningMoon implements Listener {
    private FuturaPlugin main;

    public OnMiningMoon(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    @EventHandler
    public void OnMining(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerWorld = player.getWorld().getName();
        World world = event.getPlayer().getWorld();
        Block block = event.getBlock();
        ItemStack cobble = new ItemStack(Material.COBBLESTONE);
        if (player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE)) {
            if (this.main.getConfig().getConfigurationSection("NerfMiningAllowedWorlds").contains(playerWorld) && this.main.getConfig().getConfigurationSection(String.format("NerfMiningAllowedWorlds.%s", playerWorld)).contains(String.valueOf(block.getType())) && Math.random() * 100.0D <= this.main.getConfig().getDouble(String.format("NerfMiningAllowedWorlds.%s.%s", playerWorld, block.getType()))) {
                event.setDropItems(false);
                event.setExpToDrop(0);
                world.dropItem(event.getBlock().getLocation(), cobble);
            }

        }
    }
}
