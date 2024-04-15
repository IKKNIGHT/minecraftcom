package org.example.ikknight.templatep.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.example.ikknight.templatep.utils.BasicUtils;

import static org.example.ikknight.templatep.utils.WebServer.runServer;
import static org.example.ikknight.templatep.utils.WebServer.stopServer;

public class WebRestart implements CommandExecutor {
    BasicUtils basicUtils = new BasicUtils();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            try {
                stopServer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                runServer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            commandSender.sendMessage(basicUtils.getSuffix() + ChatColor.DARK_AQUA + "RESTARTED");
            return true;
        }else{
            commandSender.sendMessage(basicUtils.getSuffix()+ChatColor.RED+"Only the console can send ");
        }
        return false;
    }
}
