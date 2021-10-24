package fr.slophil;

import fr.slophil.commands.*;
import fr.slophil.event.OnDamageRessource;
import fr.slophil.event.OnJoin;
import fr.slophil.event.OnMiningMoon;
import fr.slophil.other.BloodEffect;
import fr.slophil.other.DailyCCReload;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class FuturaPlugin extends JavaPlugin {

    private BloodCommand bloodCommand;
    private DailyCCReload dailyCCReload;

    public FuturaPlugin() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        this.commands();
        this.getServer().getPluginManager().registerEvents(new BloodEffect(this), this);
        this.getServer().getPluginManager().registerEvents(new OnDamageRessource(this), this);
        this.getServer().getPluginManager().registerEvents(new OnMiningMoon(this), this);
        this.getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        this.bloodCommand = new BloodCommand(this);
        this.dailyCCReload = new DailyCCReload(this);
        System.out.println(ChatColor.DARK_GREEN + "+------------------+");
        System.out.println(ChatColor.DARK_GREEN + "+   FuturaPlugin   +");
        System.out.println(ChatColor.DARK_GREEN + "+     Enabled !    +");
        System.out.println(ChatColor.DARK_GREEN + "+------------------+");
    }

    public void onDisable() {
        System.out.println(ChatColor.DARK_RED + "+------------------+");
        System.out.println(ChatColor.DARK_RED + "+   FuturaPlugin   +");
        System.out.println(ChatColor.DARK_RED + "+    Disabled !    +");
        System.out.println(ChatColor.DARK_RED + "+------------------+");
    }

    private void commands() {
        this.getCommand("nightvision").setExecutor(new NightVisionCommand(this));
        this.getCommand("vote").setExecutor(new VoteCommand(this));
        this.getCommand("site").setExecutor(new SiteCommand(this));
        this.getCommand("boutique").setExecutor(new BoutiqueCommand(this));
        this.getCommand("randomteleport").setExecutor(new RandomTPCommand(this));
        this.getCommand("discord").setExecutor(new DiscordCommand(this));
        this.getCommand("wikisf").setExecutor(new WikiSfCommand(this));
        this.getCommand("blood").setExecutor(new BloodCommand(this));
    }

    public String color(char altColorChar, String path) {
        return ChatColor.translateAlternateColorCodes(altColorChar, path);
    }
    public BloodCommand getBloodCommand(){
        return bloodCommand;
    }

    public DailyCCReload getDailyCCReload(){
        return dailyCCReload;
    }
}
