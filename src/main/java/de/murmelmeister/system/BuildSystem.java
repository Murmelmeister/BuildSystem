package de.murmelmeister.system;

import de.murmelmeister.system.command.CommandManager;
import de.murmelmeister.system.configuration.DefaultConfig;
import de.murmelmeister.system.configuration.MessageConfig;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main instance.
 */
public final class BuildSystem extends JavaPlugin {
    private DefaultConfig defaultConfig;
    private MessageConfig messageConfig;

    private CommandManager commandManager;

    @Override
    public void onLoad() {
        this.defaultConfig = new DefaultConfig(this.getSLF4JLogger());
        this.messageConfig = new MessageConfig(this.getSLF4JLogger());

        this.commandManager = new CommandManager(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        defaultConfig.register();
        messageConfig.register();

        commandManager.register();
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
