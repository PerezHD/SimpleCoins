package de.germanelectronix.simplecoins;

import org.bukkit.entity.Player;


public class SimpleCoinsAPI {
	
	
	//Spieler Coins setzen
	public static void setCoins(Player p, Integer value){
		SimpleCoins.cache.put(p.getUniqueId().toString(), value); //Coins im Cache setzen
	}
	
	
	//Spieler Coins auslesen
	public static Integer getCoins(Player p){
		return SimpleCoins.cache.get(p.getUniqueId().toString()); //Coins aus Cache auslesen und returnen
	}
	
	
	//Spieler Coins hinzufügen
	public static void addCoins(Player p, Integer amount){
		SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) + amount); //Coins in Cache hinzufügen
	}
	
	
	//Spieler Coins abziehen
	public static void substractCoins(Player p, Integer amount){
		SimpleCoins.cache.put(p.getUniqueId().toString(), SimpleCoins.cache.get(p.getUniqueId().toString()) - amount); //Coins in Cache abziehen
	}
		
	
}
