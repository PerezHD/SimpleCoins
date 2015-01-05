package de.germanelectronix.simplecoins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoin implements Listener{

    private SimpleCoins plugin;
    public PlayerJoin(SimpleCoins plugin) {
       this.plugin = plugin;
    }
	
	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerJoin(final PlayerJoinEvent e){
		
		new BukkitRunnable() {
			@Override
			public void run() {
				SimpleCoins.sql.addPlayer(e.getPlayer()); //Spieler in Datenbank eintragen
				SimpleCoins.cache.put(e.getPlayer().getUniqueId().toString(), SimpleCoins.sql.getColumn(e.getPlayer())); //Spieler in Cache laden
			}
		}.runTaskAsynchronously(plugin);
		
	}
	
}
