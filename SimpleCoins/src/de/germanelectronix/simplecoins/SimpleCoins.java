package de.germanelectronix.simplecoins;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.germanelectronix.simplecoins.sql.MySQL;


public class SimpleCoins extends JavaPlugin implements Listener {
	
	// The instance of our plugin
	private SimpleCoins plugin;
	
	//MySQL Class Instance
	public static MySQL sql = new MySQL();
	
	//Database Cache
	public static HashMap<String, Integer> cache = new HashMap<String, Integer>();
	
	//Database Credentials
	public static String host;
	public static String user;
	public static String password;
	public static String database;

	
	
	//Plugin aktiviert
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		this.registerEvents();
		this.loadConfig();
		
		//Async: Verbindung herstellen und Tabellen erstellen, falls nicht vorhanden
		new BukkitRunnable() {
			@Override
			public void run() {
				sql.openConnection();
				sql.createTables();
			}
		}.runTaskAsynchronously(this);
		
	}
	
	
	//Events registrieren
	private void registerEvents(){
		getServer().getPluginManager().registerEvents(new PlayerJoin(plugin), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(plugin), this);
	}
	
	
	//Config laden
	private void loadConfig(){
		this.getConfig().addDefault("MySQL.Host", "localhost");
		this.getConfig().addDefault("MySQL.Username", "root");
		this.getConfig().addDefault("MySQL.Password", "password");
		this.getConfig().addDefault("MySQL.Database", "simple_coins");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		host = this.getConfig().getString("MySQL.Host");
		user = this.getConfig().getString("MySQL.Username");
		password = this.getConfig().getString("MySQL.Password");
		database = this.getConfig().getString("MySQL.Database");
	}
	
}
