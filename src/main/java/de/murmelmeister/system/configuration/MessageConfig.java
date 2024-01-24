package de.murmelmeister.system.configuration;

import de.murmelmeister.system.util.Color;
import de.murmelmeister.system.util.configuration.FileUtil;
import de.murmelmeister.system.util.configuration.Messages;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

/**
 * Manage {@link Messages}
 */
public final class MessageConfig {
    private final Logger logger;

    private YamlConfiguration configuration;

    public MessageConfig(Logger logger) {
        this.logger = logger;
    }

    public void register() {
        create();
        load();
        save();
    }

    public void create() {
        this.configuration = FileUtil.load(logger, "Message");
    }

    public void load() {
        for (Messages messages : Messages.VALUES) if (get(messages) == null) set(messages);
    }

    public void save() {
        FileUtil.save(configuration);
    }

    public void set(Messages messages) {
        configuration.set(messages.getPath(), messages.getMessage());
    }

    public String get(Messages messages) {
        return configuration.getString(Color.format(messages.getPath()));
    }

    public String getPrefix() {
        return get(Messages.PLUGIN_PREFIX);
    }
}
