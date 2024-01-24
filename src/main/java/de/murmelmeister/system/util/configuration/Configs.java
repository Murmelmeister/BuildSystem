package de.murmelmeister.system.util.configuration;

/**
 * Register all configuration of the plugin.
 */
public enum Configs {
    PLUGIN_ENABLE_PREFIX("Plugin.Enable.Prefix", true),
    COMMAND_ENABLE_RELOAD("Command.Enable.Reload", true),
    COMMAND_ENABLE_BUILD("Command.Enable.Build", true),
    PERMISSION_RELOAD("Permission.Reload", "build.command.reload"),
    PERMISSION_BUILD_USE("Permission.Build.Use", "build.command.build.use"),
    PERMISSION_BUILD_OTHERS("Permission.Build.Others", "build.command.build.others"),
    EVENT_CLEAR_PLAYER_DEATH("Event.Clear.PlayerDeath", true),
    EVENT_PROTECTED_BLOCK_BREAK("Event.Protected.Block.Break", true),
    EVENT_PROTECTED_BLOCK_PLACE("Event.Protected.Block.Place", true),
    EVENT_PROTECTED_BLOCK_EXPLODE("Event.Protected.Block.Explode", true),
    EVENT_PROTECTED_ENTITY_EXPLODE("Event.Protected.Entity.Explode", true),
    EVENT_PROTECTED_FARM_LAND("Event.Protected.FarmLand", true),
    EVENT_PROTECTED_INVENTORY_CLICK("Event.Protected.InventoryClick", true),
    EVENT_PROTECTED_FALL_DAMAGE("Event.Protected.FallDamage", true),
    EVENT_PROTECTED_ATTACK_DAMAGE("Event.Protected.AttackDamage", true),
    EVENT_PROTECTED_ALL_DAMAGE("Event.Protected.AllDamage", true),
    EVENT_PROTECTED_PLAYER_ITEM_PICKUP("Event.Protected.Player.Item.Pickup", true),
    EVENT_PROTECTED_PLAYER_ITEM_DROP("Event.Protected.Player.Item.Drop", true),
    EVENT_PROTECTED_CHANGE_FOOD_LEVEL("Event.Protected.Change.FoodLevel", true),
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
