package de.murmelmeister.system.util.configuration;

/**
 * Register all messages of the plugin.
 */
public enum Messages {
    PLUGIN_PREFIX("Plugin.Prefix", "&8[&3Build&8] &r"),
    PLUGIN_COMMAND_DISABLE("Plugin.Command.Disable", "&cThe command is disable."),
    PLUGIN_NO_PERMISSION("Plugin.NoPermission", "&cYou don't have the right permission to do that."),
    PLUGIN_NO_CONSOLE("Plugin.NoConsole", "&cThe command does not work in the console."),
    PLUGIN_SYNTAX("Plugin.Syntax", "&7Syntax &c[USAGE]"),
    PLUGIN_NO_PLAYER_EXIST("Plugin.NoPlayerExist", "&7The player &e[PLAYER] &7is&c not&7 on the&c server&7."),
    COMMAND_RELOAD("Command.Reload", "&aThe configuration was reloaded."),
    COMMAND_BUILD_MODE_USE_ON("Command.BuildMode.Use.On", "&7You are in the&a build mode&7."),
    COMMAND_BUILD_MODE_USE_OFF("Command.BuildMode.Use.Off", "&7You are out of the&c build mode&7."),
    COMMAND_BUILD_MODE_OTHERS_ON("Command.BuildMode.Others.On", "&7The player &e[PLAYER]&7 is in the&a build mode&7."),
    COMMAND_BUILD_MODE_OTHERS_OFF("Command.BuildMode.Others.Off", "&7The player &e[PLAYER]&7 is out of the&c build mode&7."),
    ;

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
