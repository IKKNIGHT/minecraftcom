package org.example.ikknight.templatep.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.example.ikknight.templatep.Main;
import org.example.ikknight.templatep.utils.BasicUtils;

import static org.example.ikknight.templatep.Main.safeStartServer;
import static org.example.ikknight.templatep.Main.safeStopServer;

public class WebRestart implements CommandExecutor {
    Main p;
    public WebRestart(Main plugin){
        p=plugin;
    }
    BasicUtils basicUtils = new BasicUtils();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            try {
                safeStopServer(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                safeStartServer(p);
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
