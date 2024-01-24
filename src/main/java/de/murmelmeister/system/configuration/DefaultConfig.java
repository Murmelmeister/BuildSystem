package de.murmelmeister.system.configuration;

import de.murmelmeister.system.util.configuration.Configs;
import de.murmelmeister.system.util.configuration.FileUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

/**
 * Manage {@link Configs}.
 */
public final class DefaultConfig {
    private final Logger logger;

    private YamlConfiguration configuration;

    public DefaultConfig(Logger logger) {
        this.logger = logger;
    }

    public void register() {
        create();
        load();
        save();
    }

    public void create() {
        this.configuration = FileUtil.load(logger, "Config");
    }

    public void load() {
        for (Configs configs : Configs.VALUES) if (get(configs) == null) set(configs);
    }

    public void save() {
        FileUtil.save(configuration);
    }

    public void set(Configs configs) {
        configuration.set(configs.getPath(), configs.getValue());
    }

    public Object get(Configs configs) {
        return configuration.get(configs.getPath());
    }

    public String getString(Configs configs) {
        return configuration.getString(configs.getPath());
    }

    public boolean getBoolean(Configs configs) {
        return configuration.getBoolean(configs.getPath());
    }
}
