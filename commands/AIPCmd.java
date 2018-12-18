package net.dopedealers.spigot.commands;

import net.dopedealers.spigot.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class AIPCmd implements CommandExecutor
{
    public Registry plugin;
    public boolean Update;
    public String newVersion;

    public AIPCmd(final Registry passedplugin) {
        this.newVersion = "x.x.x";
        this.plugin = passedplugin;
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (!cmd.getName().equalsIgnoreCase("aip") || args.length != 0) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!sender.hasPermission("aip.reload")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.Commands.NoPermission")));
                        return true;
                    }
                    this.plugin.reloadConfig();
                    this.plugin.saveDefaultConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.Commands.ReloadCommand").replaceAll("&", "§")));
                    return true;
                }
                else if (args[0].equalsIgnoreCase("updater")) {
                    if (!sender.hasPermission("aip.updatelogin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.Commands.NoPermission")));
                        return true;
                    }
                    final String oldVersion = this.plugin.getDescription().getVersion();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8 "));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l   AntiInvisPotion"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are running an outdated version"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are running version: " + ChatColor.DARK_RED + ChatColor.UNDERLINE + oldVersion));
                    p.sendMessage(ChatColor.RED + "New version: " + ChatColor.GOLD + ChatColor.UNDERLINE + this.newVersion);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "https://www.spigotmc.org/coming soon"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8 "));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m--------------------"));
                    return false;
                }
            }
            return false;
        }
        if (!sender.hasPermission("aip.info")) {
            sender.sendMessage(this.plugin.getConfig().getString("Messages.Commands.NoPermission").replaceAll("&", "§"));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&m--------------"));
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b&l  AntiInvisPot"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cDev&r: &6DopeDealers"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cCommands§r"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/aip"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/aip reload"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c/aip updater"));
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&m--------------"));
        return true;
    }
}
