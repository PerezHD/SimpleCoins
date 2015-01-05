package de.germanelectronix.simplecoins;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class SimpleCoinsAPI {
	
	
	//Set player coins
	public static void setCoins(Player p, Integer value){
		SimpleCoins.cache.put(p.getUniqueId().toString(), value); //Set coins in cache
	}
	
	//Set offline player coins
	public static void setCoins(final OfflinePlayer p, final Integer value){
		SimpleCoins.cache.put(p.getUniqueId().toString(), value); //Set coins in cache
	}
	
	
	//Get player coins
	public static Integer getCoins(Player p){
		return SimpleCoins.cache.get(p.getUniqueId().toString()); //Get and return coins from cache
	}
	
	
	//Get offline player coins
	public static Integer getCoins(final OfflinePlayer p){
		
		if(SimpleCoins.cache.containsKey(p.getUniqueId().toString())){
			return SimpleCoins.cache.get(p.getUniqueId().toString()); //Get and return coins from cache
		} else {
			
			new BukkitRunnable() {
				@Override
				public void run() {
					if(SimpleCoins.sql.containsPlayer(p.getUniqueId().toString())){
						SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.sql.getColumn((Player) p));
					} else {
						SimpleCoins.sql.setPlayerCoins((Player) p, 0);
						SimpleCoins.cache.put(p.getUniqueId().toString(), 0);
					}
				}
			}.runTaskAsynchronously(SimpleCoins.plugin);
			
			return SimpleCoins.cache.get(p.getUniqueId().toString()); //Get and return coins from cache
		}
		
	}
	
	
	//Add player coins
	public static void addCoins(Player p, Integer amount){
		SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) + amount); //Add coins into cache
	}
	
	//Add offline player coins
	public static void addCoins(final OfflinePlayer p, final Integer amount){
		
		if(SimpleCoins.cache.containsKey(p.getUniqueId().toString())){
			SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) + amount);
		} else {
			new BukkitRunnable() {
				@Override
				public void run(){
					SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.sql.getColumn((Player) p) + amount);
				}
			}.runTaskAsynchronously(SimpleCoins.plugin);
		}
		
	}
	
	
	//Substract player coins
	public static boolean substractCoins(Player p, Integer amount){
		if(SimpleCoins.cache.get(p.getUniqueId().toString()) >= amount){
			SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) - amount); //Substract coins in cache
			return true;
		} else {
			return false;
		}
	}
	
	//Substract offline player coins
	public static boolean substractCoins(final OfflinePlayer p, final Integer amount){
		
		//Check if cache contains player
		if(SimpleCoins.cache.containsKey(p.getUniqueId().toString())){
			if(SimpleCoins.cache.get(p.getUniqueId().toString()) >= amount){
				SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) - amount);
				return true;
			} else {
				return false;
			}
		} else {
			
			//Get coins from database
			new BukkitRunnable() {
				@Override
				public void run() {
					SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.sql.getColumn((Player) p));
				}
			};
			
			//Check if player has enough coins
			if(SimpleCoins.cache.get(p.getUniqueId().toString()) >= amount){
				SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) - amount);
				return true;
			} else {
				return false;
			}
			
		}
	}
	
}
