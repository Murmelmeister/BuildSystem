package de.murmelmeister.system.util.configuration;

/**
 * Register all messages of the plugin.
 */
public enum Messages {
    PLUGIN_PREFIX("Plugin.Prefix", "&8[&3Build&8] &r"),
    PLUGIN_COMMAND_DISABLE("Plugin.Command.Disable", "&cThe command is disable."),
    PLUGIN_NO_PERMISSION("Plugin.NoPermission", "&cYou don't have the right permission to do that."),
    PLUGIN_NO_CONSOLE("Plugin.NoConsole", "&cThe command does not work in the console."),
    COMMAND_RELOAD("Command.Reload", "&aThe configuration was reloaded.");

    public static final Messages[] VALUES = values();

    private final String path;
    private final String message;

    Messages(String path, String message) {
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
