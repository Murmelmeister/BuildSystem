package de.murmelmeister.system.command.commands;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.command.Commands;
import de.murmelmeister.system.util.configuration.Configs;
import de.murmelmeister.system.util.configuration.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class BuildCommand extends Commands {
    public BuildCommand(BuildSystem instance) {
        super(instance);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (isCommandEnable(sender, Configs.COMMAND_ENABLE_BUILD)) return true;

        switch (args.length) {
            case 0 -> {
                if (hasPermission(sender, Configs.PERMISSION_BUILD_USE)) return true;
                Player player = getPlayer(sender);
                if (!existPlayer(sender)) return true;
                UUID uuid = player.getUniqueId();
                if (lists.getBuildMode().contains(uuid)) {
                    lists.getBuildMode().remove(uuid);
                    sendMessage(player, message.get(Messages.COMMAND_BUILD_MODE_USE_OFF));
                } else {
                    lists.getBuildMode().add(uuid);
                    sendMessage(player, message.get(Messages.COMMAND_BUILD_MODE_USE_ON));
                }
            }
            case 1 -> {
                Player target = sender.getServer().getPlayer(args[0]);
                if (target == null) {
                    sendPlayerNotExist(sender, args, 0);
                    break;
                }
                UUID uuid = target.getUniqueId();
                if (lists.getBuildMode().contains(uuid)) {
                    lists.getBuildMode().remove(uuid);
                    sendMessage(target, message.get(Messages.COMMAND_BUILD_MODE_USE_OFF));
                    sendMessage(sender, message.get(Messages.COMMAND_BUILD_MODE_OTHERS_OFF).replace("[PLAYER]", target.getName()));
                } else {
                    lists.getBuildMode().add(uuid);
                    sendMessage(target, message.get(Messages.COMMAND_BUILD_MODE_USE_ON));
                    sendMessage(sender, message.get(Messages.COMMAND_BUILD_MODE_OTHERS_ON).replace("[PLAYER]", target.getName()));
                }
            }
            default -> sendUsageMessage(sender, command);
        }
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return tabCompletePLayers(sender, args, 1);
    }
}
