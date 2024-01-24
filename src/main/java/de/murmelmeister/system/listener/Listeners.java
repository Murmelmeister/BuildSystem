package de.murmelmeister.system.listener;

import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.configuration.DefaultConfig;
import de.murmelmeister.system.listener.listeners.ProtectionListener;
import de.murmelmeister.system.util.ListsUtil;
import org.bukkit.event.Listener;

/**
 * Register and manage the listeners.
 */
public class Listeners implements Listener {
    public final BuildSystem instance;
    public final DefaultConfig config;
    public final ListsUtil lists;

    public Listeners(BuildSystem instance) {
        this.instance = instance;
        this.config = instance.getDefaultConfig();
        this.lists = instance.getListsUtil();
    }

    public void register() {
        addListener(new ProtectionListener(instance));
    }

    private void addListener(Listener listener) {
        instance.getServer().getPluginManager().registerEvents(listener, instance);
    }
}
