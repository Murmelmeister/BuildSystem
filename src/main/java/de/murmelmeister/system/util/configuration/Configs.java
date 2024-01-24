package de.murmelmeister.system.util.configuration;

/**
 * Register all configuration of the plugin.
 */
public enum Configs {
    PLUGIN_ENABLE_PREFIX("Plugin.Enable.Prefix", true),
    COMMAND_ENABLE_RELOAD("Command.Enable.Reload", true),
    PERMISSION_RELOAD("Permission.Reload", "build.command.reload"),
    ;

    public static final Configs[] VALUES = values();

    private final String path;
    private final Object value;

    Configs(String path, Object value) {
        this.path = path;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }
}
