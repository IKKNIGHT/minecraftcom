package org.example.ikknight.templatep;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.ikknight.templatep.commands.Say;
import org.example.ikknight.templatep.listeners.PlayerJoin;
import org.example.ikknight.templatep.utils.BasicUtils;

public final class Main extends JavaPlugin {
    BasicUtils basicUtils = new BasicUtils();
    private static Main plugin; // This is a static plugin instance that is private. Use getPlugin() as seen
    // further below.

    PluginDescriptionFile pdfFile; // plugin.yml

    public static Main getPlugin() { // getter for the static plugin instance
        return plugin;
    }

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }


    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        this.pdfFile = getDescription();
        basicUtils.setSuffix("[TEMPLATE-PLUGIN] ");
        // init command
        this.getCommand("say").setExecutor(new Say());
        // init listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getLogger()
                .info(this.pdfFile.getName() + " - Version " + this.pdfFile.getVersion() + " - has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
