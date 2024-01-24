package de.murmelmeister.system.command;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.configuration.DefaultConfig;
import de.murmelmeister.system.configuration.MessageConfig;
import de.murmelmeister.system.util.Color;
import de.murmelmeister.system.util.ListsUtil;
import de.murmelmeister.system.util.configuration.Configs;
import de.murmelmeister.system.util.configuration.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage the commands.
 */
public abstract class Commands implements TabExecutor {
    public final BuildSystem instance;
    public final DefaultConfig config;
    public final MessageConfig message;
    public final ListsUtil lists;

    public Commands(BuildSystem instance) {
        this.instance = instance;
        this.config = instance.getDefaultConfig();
        this.message = instance.getMessageConfig();
        this.lists = instance.getListsUtil();
    }

    public void sendMessage(CommandSender sender, String message) {
        if (config.getBoolean(Configs.PLUGIN_ENABLE_PREFIX))
            sender.sendMessage(Color.format(this.message.getPrefix() + message));
        else sender.sendMessage(Color.format(message));
    }

    public void sendUsageMessage(CommandSender sender, Command command) {
        sendMessage(sender, message.get(Messages.PLUGIN_SYNTAX).replace("[USAGE]", command.getUsage()));
    }

    public void sendPlayerNotExist(CommandSender sender, String[] args, int length) {
        sendMessage(sender, message.get(Messages.PLUGIN_NO_PLAYER_EXIST).replace("[PLAYER]", args[length]));
    }

    public boolean isCommandEnable(CommandSender sender, Configs configs) {
        if (!config.getBoolean(configs)) {
            sendMessage(sender, message.get(Messages.PLUGIN_COMMAND_DISABLE));
            return true;
        }
        return false;
    }

    public boolean hasPermission(CommandSender sender, Configs configs) {
        if (!sender.hasPermission(config.getString(configs))) {
            sendMessage(sender, message.get(Messages.PLUGIN_NO_PERMISSION));
            return true;
        }
        return false;
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

    public List<String> tabCompletePlayers(CommandSender sender, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        String lastWord = args[args.length - 1];
        Player senderPlayer = getPlayer(sender);
        for (Player player : sender.getServer().getOnlinePlayers()) {
            String name = player.getName();
            if ((senderPlayer == null || senderPlayer.canSee(player)) && StringUtil.startsWithIgnoreCase(name, lastWord))
                tabComplete.add(name);
        }
        tabComplete.sort(String.CASE_INSENSITIVE_ORDER);
        return tabComplete;
    }

    public List<String> tabCompletePLayers(CommandSender sender, String[] args, int length) {
        if (args.length == length) return tabCompletePlayers(sender, args);
        return Collections.emptyList();
    }
}
