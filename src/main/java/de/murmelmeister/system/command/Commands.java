package de.murmelmeister.system.command;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.configuration.DefaultConfig;
import de.murmelmeister.system.configuration.MessageConfig;
import de.murmelmeister.system.util.Color;
import de.murmelmeister.system.util.configuration.Configs;
import de.murmelmeister.system.util.configuration.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public abstract class Commands implements TabExecutor {
    public final BuildSystem instance;
    public final DefaultConfig config;
    public final MessageConfig message;

    public Commands(BuildSystem instance) {
        this.instance = instance;
        this.config = instance.getDefaultConfig();
        this.message = instance.getMessageConfig();
    }

    public void sendMessage(CommandSender sender, String message) {
        if (config.getBoolean(Configs.PLUGIN_ENABLE_PREFIX))
            sender.sendMessage(Color.format(this.message.getPrefix() + message));
        else sender.sendMessage(Color.format(message));
    }

    public boolean isCommandEnable(CommandSender sender, Configs configs) {
        if (!config.getBoolean(configs)) {
            sendMessage(sender, message.get(Messages.PLUGIN_COMMAND_DISABLE));
            return false;
        }
        return true;
    }

    public boolean hasPermission(CommandSender sender, Configs configs) {
        if (!sender.hasPermission(config.getString(configs))) {
            sendMessage(sender, message.get(Messages.PLUGIN_NO_PERMISSION));
            return false;
        }
        return true;
    }

    public Player getPlayer(CommandSender sender) {
        return sender instanceof Player ? (Player) sender : null;
    }

    public boolean existPlayer(CommandSender sender) {
        Player player = getPlayer(sender);
        if (player == null) {
            sendMessage(sender, message.get(Messages.PLUGIN_NO_CONSOLE));
            return false;
        }
        return true;
    }
}
