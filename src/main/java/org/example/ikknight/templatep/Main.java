package org.example.ikknight.templatep;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.ikknight.templatep.commands.WebRestart;
import org.example.ikknight.templatep.commands.Say;
import org.example.ikknight.templatep.listeners.PlayerJoin;
import org.example.ikknight.templatep.listeners.PlayerLeave;
import org.example.ikknight.templatep.utils.BasicUtils;
import org.example.ikknight.templatep.utils.Message;

import static org.example.ikknight.templatep.utils.WebServer.*;

public final class Main extends JavaPlugin {
    public static long webServerRunTime = 0;
    public static long ServerRunTime = webServerRunTime+10;
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
        this.getCommand("webrestart").setExecutor(new WebRestart());
        // init listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        this.getLogger()
                .info(this.pdfFile.getName() + " - Version " + this.pdfFile.getVersion() + " - has been enabled!");

        // Run the server setup in another thread
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    runServer();
                    System.out.println("server started successfully");
                } catch (Exception e) {
                    System.out.println("server failed to start "+e);
                }
            }
        }.runTaskAsynchronously(this); // Execute the task asynchronously

        // Schedule a repeating task in another thread

        new BukkitRunnable() {
            @Override
            public void run() {
                // Your task logic here

                try {

                    setMessage(msg.addFields("Web Server Running For "+webServerRunTime+" Seconds | And Server Running For "+ServerRunTime+" Seconds", players + ""));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } // runs every 5 seconds and init delay of 10

        }.runTaskTimerAsynchronously(this, 20L * 10L /*<-- the initial delay */, 20L * 5L /*<-- the interval */);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            stopServer();
        } catch (Exception e) {
            System.out.println("Error in stopping the server? Usually caused by reloading... error : \n"+e);
        }
    }
}
