package me.swagsteve.healthybeds;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HealthyBeds extends JavaPlugin implements Listener {

    private static HealthyBeds instance;
    public static HealthyBeds getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        System.out.println("[HealthyBeds] Has Been Enabled!");

        this.getCommand("hbreload").setExecutor(new ReloadCommand());
        getServer().getPluginManager().registerEvents(this, this);

        //Config
        this.getConfig().options().copyDefaults();
        this.getConfig().addDefault("HealAmount", 20);
        saveDefaultConfig();

    }

    public boolean day(Player p) {
        Server server = getServer();
        long time = p.getWorld().getTime();

        return time < 12300 || time > 23850;
    }

    @EventHandler
    public void onLeaveBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        if (!day(player)) {
            if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
                int number = this.getConfig().getInt("HealAmount");
                try {
                    player.setHealth(player.getHealth() + number);
                } catch (IllegalArgumentException e) {
                    player.setHealth(20);
                }
            }
        }
    }

    @Override
    public void onDisable() {

        System.out.println("[HealthyBeds] Has Been Disabled!");

        saveConfig();
    }
}
