package fr.slophil.commands;

import fr.slophil.FuturaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionCommand implements CommandExecutor {
    private FuturaPlugin main;

    public NightVisionCommand(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.color('&', this.main.getConfig().getString("ExecuteOnlyByAPlayer")));
            return false;
        } else {
            Player player = (Player)sender;
            if (player.hasPermission(this.main.getCommand("nv").getPermission())) {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    player.sendMessage(this.main.color('&', this.main.getConfig().getString("NightVisionDisabled")));
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, 2));
                    player.sendMessage(this.main.color('&', this.main.getConfig().getString("NightVisionActivated")));
                }

                return true;
            } else {
                return true;
            }
        }
    }
}
