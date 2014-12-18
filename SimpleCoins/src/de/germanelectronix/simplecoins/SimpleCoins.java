package de.germanelectronix.simplecoins;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.germanelectronix.simplecoins.cmd.AddCoins;
import de.germanelectronix.simplecoins.cmd.GetCoins;
import de.germanelectronix.simplecoins.cmd.SetCoins;
import de.germanelectronix.simplecoins.cmd.TakeCoins;


public class SimpleCoins extends JavaPlugin implements Listener {
	
	//Main Class Instance
	private SimpleCoins plugin;
	
	//MySQL Class Instance
	protected static MySQL sql = new MySQL();
	
	//Database Cache
	protected static HashMap<String, Integer> cache = new HashMap<String, Integer>();
	
	//Database Credentials
	protected static String host;
	protected static String user;
	protected static String password;
	protected static String database;

	
	
	//Plugin activated
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		this.registerEvents();
		this.loadConfig();
		
		//Async: Connect and create tables if not existing
		new BukkitRunnable() {
			@Override
			public void run() {
				sql.openConnection();
				sql.createTables();
			}
		}.runTaskAsynchronously(this);
		
	}
	
	
	//Register Events
	private void registerEvents(){
		getServer().getPluginManager().registerEvents(new PlayerJoin(plugin), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(plugin), this);
	}
	
	
	//Load config
	private void loadConfig(){
		this.getConfig().addDefault("MySQL.Host", "localhost");
		this.getConfig().addDefault("MySQL.Username", "root");
		this.getConfig().addDefault("MySQL.Password", "password");
		this.getConfig().addDefault("MySQL.Database", "simple_coins");
		this.getConfig().addDefault("Commands.Enabled.Coins", true);
		this.getConfig().addDefault("Commands.Enabled.SetCoins", true);
		this.getConfig().addDefault("Commands.Enabled.AddCoins", true);
		this.getConfig().addDefault("Commands.Enabled.TakeCoins", true);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		host = this.getConfig().getString("MySQL.Host");
		user = this.getConfig().getString("MySQL.Username");
		password = this.getConfig().getString("MySQL.Password");
		database = this.getConfig().getString("MySQL.Database");
		
		//Register enabled commands
		if(this.getConfig().getBoolean("Commands.Enabled.Coins")){
			this.getCommand("coins").setExecutor(new GetCoins(this));
		}
		
		if(this.getConfig().getBoolean("Commands.Enabled.SetCoins")){
			this.getCommand("setcoins").setExecutor(new SetCoins(this));
		}
		
		if(this.getConfig().getBoolean("Commands.Enabled.AddCoins")){
			this.getCommand("addcoins").setExecutor(new AddCoins(this));
		}
		
		if(this.getConfig().getBoolean("Commands.Enabled.TakeCoins")){
			this.getCommand("takecoins").setExecutor(new TakeCoins(this));
		}
		
	}
	
}
