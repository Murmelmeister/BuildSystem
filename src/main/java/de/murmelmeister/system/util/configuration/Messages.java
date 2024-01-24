package de.murmelmeister.system.util.configuration;

/**
 * Register all messages of the plugin.
 */
public enum Messages {
    PLUGIN_PREFIX("Plugin.Prefix", "&8[&3Build&8] &r"),
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
