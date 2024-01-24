package de.murmelmeister.system.listener.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.murmelmeister.system.BuildSystem;
import de.murmelmeister.system.listener.Listeners;
import de.murmelmeister.system.util.configuration.Configs;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class ProtectionListener extends Listeners {
    public ProtectionListener(BuildSystem instance) {
        super(instance);
    }

    @EventHandler
    public void handleFoodLevelChange(FoodLevelChangeEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_CHANGE_FOOD_LEVEL)) event.setCancelled(true);
    }

    @EventHandler
    public void handlePlayerDropItem(PlayerDropItemEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_PLAYER_ITEM_DROP))
            event.setCancelled(!lists.getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void handlePlayerPickupItem(PlayerPickupItemEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_PLAYER_ITEM_PICKUP))
            event.setCancelled(!lists.getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void handleEntityExplode(EntityExplodeEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_ENTITY_EXPLODE))
            event.setCancelled(!(event.getEntity() instanceof Player));
    }

    @EventHandler
    public void handleBlockExplode(BlockExplodeEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_BLOCK_EXPLODE)) {
            event.setCancelled(true);
            event.setYield(0F);
        }
    }

    @EventHandler
    public void handleAttackDamage(EntityDamageByEntityEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_ATTACK_DAMAGE))
            event.setCancelled(!lists.getBuildMode().contains(event.getDamager().getUniqueId()));
    }

    @EventHandler
    public void handleFallDamage(EntityDamageEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_FALL_DAMAGE)) if (event.getEntity() instanceof Player)
            event.setCancelled(event.getCause().equals(EntityDamageEvent.DamageCause.FALL));
        if (config.getBoolean(Configs.EVENT_PROTECTED_ALL_DAMAGE)) event.setCancelled(!lists.getBuildMode().contains(event.getEntity().getUniqueId()));
    }

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_BLOCK_BREAK)) {
            event.setCancelled(!lists.getBuildMode().contains(event.getPlayer().getUniqueId()));
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        if (config.getBoolean(Configs.EVENT_PROTECTED_BLOCK_PLACE))
            event.setCancelled(!lists.getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        if (config.getBoolean(Configs.EVENT_CLEAR_PLAYER_DEATH)) {
            event.setDroppedExp(0);
            event.getDrops().clear();
        }
    }

    @EventHandler
    public void handlePlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (config.getBoolean(Configs.EVENT_PROTECTED_INVENTORY_CLICK))
            if (lists.getBuildMode().contains(player.getUniqueId()))
                event.setCancelled(!event.getView().getPlayer().equals(player));
            else event.setCancelled(event.getView().getPlayer().equals(player));
    }

    @EventHandler
    public void handleFarmLand(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();
        Material[] blocked = {Material.FARMLAND, Material.BEETROOT_SEEDS, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.WHEAT_SEEDS};
        if (config.getBoolean(Configs.EVENT_PROTECTED_FARM_LAND))
            for (Material blocking : blocked)
                if (block.getType().equals(blocking)) // Block not really
                    if (player.isJumping()) event.setCancelled(!lists.getBuildMode().contains(player.getUniqueId()));
                    else event.setCancelled(!lists.getBuildMode().contains(player.getUniqueId()));
    }

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        lists.getBuildMode().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void handlePlayerKick(PlayerKickEvent event) {
        lists.getBuildMode().remove(event.getPlayer().getUniqueId());
    }
}
