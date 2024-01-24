package de.murmelmeister.system;

import de.murmelmeister.system.command.CommandManager;
import de.murmelmeister.system.configuration.DefaultConfig;
import de.murmelmeister.system.configuration.MessageConfig;
import de.murmelmeister.system.listener.Listeners;
import de.murmelmeister.system.util.ListsUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main instance.
 */
public final class BuildSystem extends JavaPlugin {
    private ListsUtil listsUtil;
    private DefaultConfig defaultConfig;
    private MessageConfig messageConfig;

    private CommandManager commandManager;
    private Listeners listeners;

    @Override
    public void onLoad() {
        this.listsUtil = new ListsUtil();
        this.defaultConfig = new DefaultConfig(this.getSLF4JLogger());
        this.messageConfig = new MessageConfig(this.getSLF4JLogger());

        this.commandManager = new CommandManager(this);
        this.listeners = new Listeners(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        listsUtil.register();
        defaultConfig.register();
        messageConfig.register();

        commandManager.register();
        listeners.register();
    }

    public ListsUtil getListsUtil() {
        return listsUtil;
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
