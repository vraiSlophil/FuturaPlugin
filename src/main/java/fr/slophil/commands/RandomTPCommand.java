package fr.slophil.commands;

import fr.slophil.FuturaPlugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class RandomTPCommand implements TabExecutor {
    Map<String, Long> cooldown = new HashMap();
    private final FuturaPlugin main;

    public RandomTPCommand(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (args.length > 1) {
                player.sendMessage(this.main.color('&', this.main.getConfig().getString("RtpUsage")));
            }

            Location loc;
            float x;
            int y;
            float z;
            String playerWorld;
            if (args.length == 0) {
                if (this.cooldown.containsKey(player.getName()) && (Long)this.cooldown.get(player.getName()) > System.currentTimeMillis()) {
                    long timeLeft = ((Long)this.cooldown.get(player.getName()) - System.currentTimeMillis()) / 60000L;
                    player.sendMessage(this.main.color('&', this.main.getConfig().getString("CooldownMessage")).replace("%timeleft%", String.valueOf(timeLeft)));
                    return false;
                }

                this.cooldown.put(player.getName(), System.currentTimeMillis() + this.main.getConfig().getLong("Cooldown") * 60000L);
                playerWorld = player.getWorld().getName();
                if (this.main.getConfig().getConfigurationSection("AllowedWorlds").contains(playerWorld)) {
                    x = (float)(new Random()).nextInt(this.main.getConfig().getInt("AllowedWorlds." + playerWorld + ".MaxRtp"));
                    if (Math.random() * 100.0D <= 50.0D) {
                        x *= -1.0F;
                    }

                    z = (float)(new Random()).nextInt(this.main.getConfig().getInt("AllowedWorlds." + playerWorld + ".MaxRtp"));
                    if (Math.random() * 100.0D <= 50.0D) {
                        z *= -1.0F;
                    }

                    loc = new Location(player.getWorld(), (double)x, 0.0D, (double)z);
                    y = loc.getWorld().getHighestBlockYAt(loc);
                    loc.setY((double)(y + 2));
                    loc.getChunk().load();
                    player.teleport(loc);
                    player.sendMessage(this.main.color('&', this.main.getConfig().getString("YouHaveBeenTeleportedMessage")));
                    return true;
                } else {
                    player.sendMessage(this.main.color('&', this.main.getConfig().getString("YourNotInAllowedWorld")));
                    return false;
                }
            }

            if (args.length == 1) {
                Player playerArg = Bukkit.getPlayer(args[0]);
                playerWorld = playerArg.getWorld().getName();
                if (sender.hasPermission("admin.forcertp")) {

                    if (playerArg.getName().equals(player.getName())){
                        x = (float)(new Random()).nextInt(this.main.getConfig().getInt("AdminMaxRtp"));
                        if (Math.random() * 100.0D <= 50.0D) {
                            x *= -1.0F;
                        }

                        z = (float)(new Random()).nextInt(this.main.getConfig().getInt("AdminMaxRtp"));
                        if (Math.random() * 100.0D <= 50.0D) {
                            z *= -1.0F;
                        }

                        loc = new Location(playerArg.getWorld(), (double)x, 0.0D, (double)z);
                        y = loc.getWorld().getHighestBlockYAt(loc);
                        loc.setY((double)(y + 2));
                        loc.getChunk().load();
                        playerArg.teleport(loc);
                        return true;
                    }

                    if (this.main.getConfig().getConfigurationSection("AllowedWorlds").contains(playerWorld)) {
                        x = (float)(new Random()).nextInt(this.main.getConfig().getInt("AllowedWorlds." + playerWorld + ".MaxRtp"));
                        if (Math.random() * 100.0D <= 50.0D) {
                            x *= -1.0F;
                        }

                        z = (float)(new Random()).nextInt(this.main.getConfig().getInt("AllowedWorlds." + playerWorld + ".MaxRtp"));
                        if (Math.random() * 100.0D <= 50.0D) {
                            z *= -1.0F;
                        }

                        loc = new Location(playerArg.getWorld(), (double)x, 0.0D, (double)z);
                        y = loc.getWorld().getHighestBlockYAt(loc);
                        loc.setY((double)(y + 2));
                        loc.getChunk().load();
                        playerArg.teleport(loc);
                        return true;
                    } else {
                        sender.sendMessage(this.main.color('&', this.main.getConfig().getString("PlayerIsNotInAllowedWorld")));
                        return false;
                    }
                }
            }
        } else {
            sender.sendMessage(this.main.color('&', this.main.getConfig().getString("ExecuteOnlyByAPlayer")));
            return false;
        }


        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> result = new ArrayList();

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().startsWith(alias)) {
                    result.add(player.getName());
                }
            }
        }

        return null;
    }
}
