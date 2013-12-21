package com.warkwarior.kitviewer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClick
  implements Listener
{
  ServerHub plugin;
  
  public ItemClick(ServerHub plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (player.getItemInHand().getTypeId() == this.plugin.getConfig().getInt("click-item-id"))) {
      this.plugin.getIconMenu().open(player);
    }
  }
}
