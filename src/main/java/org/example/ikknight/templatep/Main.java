package org.example.ikknight.templatep;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.ikknight.templatep.commands.Say;
import org.example.ikknight.templatep.listeners.PlayerJoin;
import org.example.ikknight.templatep.listeners.PlayerLeave;
import org.example.ikknight.templatep.utils.BasicUtils;
import org.example.ikknight.templatep.utils.Message;

import static org.example.ikknight.templatep.utils.WebServer.*;

public final class Main extends JavaPlugin {
    public static int players = 0;
    BasicUtils basicUtils = new BasicUtils();
    Message msg = new Message("");
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
        basicUtils.setSuffix("[Minecraft.COM] ");
        // init command
        this.getCommand("say").setExecutor(new Say());
        // init listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        this.getLogger()
                .info(this.pdfFile.getName() + " - Version " + this.pdfFile.getVersion() + " - has been enabled!");
        try {
            runServer();
            this.getLogger().info(basicUtils.getSuffix()+"WEBSITE RUNNING");
        } catch (Exception e) {
            this.getLogger().info(basicUtils.getSuffix()+"WEBSITE FAILED THROWING ERROR NOW : \n "+e);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                msg.addFields("Online",players+"");
                try {
                    setMessage(msg.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskTimer(this, 20L * 10L /*<-- the initial delay */, 20L * 5L /*<-- the interval */);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            stopServer();
        } catch (Exception e) {
            this.getLogger().info(basicUtils.getSuffix()+"WEBSITE FAILED TO STOP THROWING ERROR NOW : \n "+e);
        }
    }
}
