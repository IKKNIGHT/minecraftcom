package org.example.ikknight.templatep.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.example.ikknight.templatep.utils.BasicUtils;

public class Say implements CommandExecutor {
    BasicUtils basicUtils = new BasicUtils();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(basicUtils.getSuffix() + ChatColor.DARK_RED + "You cannot run this command from the console! Bozo admin get dunked on");
            return false;
        }
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage(basicUtils.getSuffix() + ChatColor.RED + "Wrong Usage! Use /say <something>");
            return true;
        }
        if (strings.length == 1) {
            player.sendMessage(basicUtils.getSuffix() + ChatColor.GREEN + strings[1]);
            return true;
        }
        player.sendMessage(basicUtils.getSuffix() + ChatColor.RED + "Wrong Usage! Use /say <something>");
        return false;
    }
}
