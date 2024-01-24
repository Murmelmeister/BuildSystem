package de.murmelmeister.system.command;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.command.commands.ReloadCommand;
import org.bukkit.command.TabExecutor;

import java.util.Objects;

/**
 * Register all commands.
 *
 * @param instance Get the main
 */
public record CommandManager(BuildSystem instance) {
    public void register() {
        addCommand("buildreload", new ReloadCommand(instance));
    }

    private void addCommand(String name, TabExecutor executor) {
        Objects.requireNonNull(instance.getCommand(name)).setExecutor(executor);
        Objects.requireNonNull(instance.getCommand(name)).setTabCompleter(executor);
    }
}
