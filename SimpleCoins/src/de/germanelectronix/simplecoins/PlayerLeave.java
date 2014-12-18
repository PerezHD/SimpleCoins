package de.germanelectronix.simplecoins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerLeave implements Listener {

	private SimpleCoins plugin;
    public PlayerLeave(SimpleCoins plugin) {
       this.plugin = plugin;
    }
	
	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerLeave(final PlayerQuitEvent e){
		
		new BukkitRunnable() {
			@Override
			public void run() {
				SimpleCoins.sql.setPlayerCoins(e.getPlayer(), SimpleCoins.cache.get(e.getPlayer().getUniqueId().toString())); //Spieler aus Cache in Datenbank übertragen
				SimpleCoins.cache.remove(e.getPlayer().getUniqueId().toString()); //Spieler aus Cache entfernen
			}
		}.runTaskAsynchronously(plugin);
		
	}
	
}
