package de.murmelmeister.system.command.commands;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.command.Commands;
import de.murmelmeister.system.util.configuration.Configs;
import de.murmelmeister.system.util.configuration.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class ReloadCommand extends Commands {
    public ReloadCommand(BuildSystem instance) {
        super(instance);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (isCommandEnable(sender, Configs.COMMAND_ENABLE_RELOAD)) return true;
        if (hasPermission(sender, Configs.PERMISSION_RELOAD)) return true;

        config.create();
        message.create();
        sendMessage(sender, message.get(Messages.COMMAND_RELOAD));
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
