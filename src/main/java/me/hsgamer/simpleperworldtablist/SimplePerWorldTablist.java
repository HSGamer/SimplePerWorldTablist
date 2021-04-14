package me.hsgamer.simpleperworldtablist;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public final class SimplePerWorldTablist extends BasePlugin implements Listener {
    @Override
    public void enable() {
        registerListener(this);
    }

    private void updateTab(Player player) {
        World world = player.getWorld();
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            if (onlinePlayer.getWorld().equals(world)) {
                player.showPlayer(this, onlinePlayer);
                onlinePlayer.showPlayer(this, player);
            } else {
                player.hidePlayer(this, onlinePlayer);
                onlinePlayer.hidePlayer(this, player);
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        updateTab(event.getPlayer());
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        updateTab(event.getPlayer());
    }
}
