package com.warkwarior.kitviewer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config
{
  ServerHub plugin;
  public FileConfiguration config;
  public File configFile;
  
  public Config(ServerHub plugin)
  {
    this.plugin = plugin;
  }
  
  public void createConfig()
  {
    this.configFile = new File("plugins" + File.separator + "KitViewer", "config.yml");
    if (!this.configFile.exists()) {
      try
      {
        this.configFile.createNewFile();
        generateConfig();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private void generateConfig()
  {
    try
    {
      this.configFile = new File("plugins" + File.separator + "KitViewer", "config.yml");
      FileWriter w = new FileWriter(this.configFile);
      w(w, "gui-rows: 1");
      w(w, "");
      w(w, "gui-name: Kits");
      w(w, "click-item-id: 338");
      w(w, "items:");
      w(w, "  slot-0:");
      w(w, "    item-id: 56");
      w(w, "    name: Tools");
      w(w, "    lore:");
      w(w, "      line-0: kit tools");
      w(w, "    command: kit tools");
      w.close();
      reloadConfig();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private void w(FileWriter writer, String string)
    throws IOException
  {
    writer.write(string + "\n");
  }
  
  public void reloadConfig()
  {
    if (!this.configFile.exists()) {
      this.configFile = new File("plugins" + File.separator + "KitViewer", "config.yml");
    }
    this.config = YamlConfiguration.loadConfiguration(this.configFile);
    
    InputStream defConfigStream = this.plugin.getResource("plugins" + File.separator + "KitViewer" + File.separator + "config.yml");
    if (defConfigStream != null)
    {
      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
      this.config.setDefaults(defConfig);
    }
  }
  
  public FileConfiguration getConfig()
  {
    if (this.config == null) {
      reloadConfig();
    }
    return this.config;
  }
  
  public void saveConfig()
  {
    if ((this.config == null) || (this.configFile == null)) {
      return;
    }
    try
    {
      this.config.save(this.configFile);
    }
    catch (IOException e)
    {
      Logger.getLogger("Minecraft").severe("Could not save the config file to the disk!");
    }
  }
}
