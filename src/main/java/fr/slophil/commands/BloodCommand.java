package fr.slophil.commands;

import fr.slophil.FuturaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

public class BloodCommand implements TabExecutor {
    private FuturaPlugin main;

    public BloodCommand(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    public Map<UUID, Boolean> bloodVision = new HashMap();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.color('&', this.main.getConfig().getString("ExecuteOnlyByAPlayer")));
        } else {
            Player player = (Player) sender;
            String configPath = "BloodVision." + player.getUniqueId() + ".Enabled";

            if (!(args.length == 1)){
                player.sendMessage(this.main.color('&', this.main.getConfig().getString("BloodCommandUsage")));
            }

            if (args[0].equalsIgnoreCase("true")) {
                main.getConfig().set(configPath, true);
                main.saveDefaultConfig();
            }
            if (args[0].equalsIgnoreCase("false")) {
                main.getConfig().set(configPath, false);
                main.saveDefaultConfig();
            }

        }
        return false;
    }

    @Override
    public List<String> onTabComplete (CommandSender sender, Command command, String alias, String[]args){

        List<String> result = new ArrayList<>();

        List<String> toReturn = new ArrayList<>(Arrays.asList("true", "false"));
        if (args.length == 1) {

            for (String stat : toReturn) {
                if (stat.startsWith(args[0])) {
                    result.add(stat);
                }
            }
        }
        return result;
    }
}