package org.example.ikknight.templatep.listeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.example.ikknight.templatep.utils.BasicUtils;

public class PlayerJoin implements Listener{
    BasicUtils basicUtils = new BasicUtils();
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.sendMessage(basicUtils.getSuffix()+ChatColor.GREEN + "Welcome to the server!");
    }
}
