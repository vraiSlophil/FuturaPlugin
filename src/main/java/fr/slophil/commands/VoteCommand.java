package fr.slophil.commands;

import fr.slophil.FuturaPlugin;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
    private FuturaPlugin main;

    public VoteCommand(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.color('&', this.main.getConfig().getString("ExecuteOnlyByAPlayer")));
        } else {
            Player player = (Player)sender;
            TextComponent message = new TextComponent(this.main.color('&', this.main.getConfig().getString("VoteMessage")));
            message.setClickEvent(new ClickEvent(Action.OPEN_URL, this.main.getConfig().getString("VoteLink")));
            message.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new Content[]{new Text(this.main.color('&', this.main.getConfig().getString("VoteHoverMessage")))}));
            player.spigot().sendMessage(message);
        }

        return false;
    }
}
