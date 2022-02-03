package me.swagsteve.healthybeds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            HealthyBeds.getInstance().reloadConfig();
            p.sendMessage("&a&lHealthyBeds Config Reloaded Successfully!");

        } else {
            System.out.println("[HealthyBeds] Only Players Can Reload The Plugin!");
        }
        return false;
    }
}
