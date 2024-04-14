package org.example.ikknight.templatep.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.example.ikknight.templatep.Main.players;

public class PlayerLeave implements Listener {
    @EventHandler
    public void PlayerLeaveEvent(PlayerJoinEvent event) {
        players = players-1;
    }
}
