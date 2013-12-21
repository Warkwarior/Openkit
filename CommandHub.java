package com.warkwarior.kitviewer;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub
  implements CommandExecutor
{
  ServerHub plugin;
  
  public CommandHub(ServerHub plugin)
  {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("openkit")) {
      if ((sender instanceof Player))
      {
        Player player = (Player)sender;
        if (player.hasPermission("openkit.open"))
        {
          if (args.length == 0) {
            this.plugin.getIconMenu().open(player);
          } else if (args.length == 1)
          {
            if (args[0].equalsIgnoreCase("reload"))
            {
              if (player.hasPermission("openkit.reload"))
              {
                this.plugin.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "reload du config effectue");
              }
              else
              {
                player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande");
              }
            }
            else {
              player.sendMessage(ChatColor.RED + "Commande incorrect faite: /openkit");
            }
          }
          else {
            player.sendMessage(ChatColor.RED + "Commande incorrect faite: /openkit");
          }
        }
        else {
          player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande");
        }
      }
      else
      {
        this.plugin.getLogger().info("Vous n'avez pas la permission d'utiliser cette commande");
      }
    }
    return false;
  }
}
