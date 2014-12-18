package de.germanelectronix.simplecoins.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import de.germanelectronix.simplecoins.SimpleCoins;

public class MySQL {

	// Represents the MySQL Connection
	private Connection connection;
	
	
	// Open the MySQL Connection
	public synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + SimpleCoins.host + ":3306/" + SimpleCoins.database, SimpleCoins.user, SimpleCoins.password);
			System.out.println("[SC-API] Successfully connected to MySQL Database!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Spieler in Tabelle hinzufügen
	public void addPlayer(Player player){
		try {
			String query = "INSERT IGNORE INTO coins (uuid,coins) VALUES(?, ?);";
			PreparedStatement p = connection.prepareStatement(query);
			p.setString(1, player.getUniqueId().toString());
			p.setInt(2, 0);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Check if MySQL contains an element
	public boolean containsElement(String field, String element) {
		String query = "SELECT `" + field + "` FROM `coins` WHERE `" + field + "`='" + element + "`";
		try {
			ResultSet res = connection.prepareStatement(query).executeQuery();
			boolean temp = res.next();
			res.close();
			return temp;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	
	//Spieler Coins auslesen
	@SuppressWarnings("finally")
	public Integer getColumn(Player p){
		Integer temp = 0;
		String query = "SELECT * FROM coins WHERE `uuid` = '" + p.getUniqueId() + "'";
		try {
			ResultSet res = connection.prepareStatement(query).executeQuery();
			while (res.next()) {
				temp = res.getInt("coins");
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return temp;
		}
	}
	
	
	//Alle Tabellen anlegen
	public void createTables(){
		try {
			String query = "CREATE TABLE IF NOT EXISTS `coins` (`uuid` varchar(36) UNIQUE, `coins` int(1));";
			PreparedStatement p = connection.prepareStatement(query);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Spieler Coins in Datenbank setzen
	public void setPlayerCoins(Player pl, Integer value){
		try {
			String query = "UPDATE coins SET coins=? WHERE uuid=?;";
			PreparedStatement p = connection.prepareStatement(query);
			p.setInt(1, value);
			p.setString(2, pl.getUniqueId().toString());
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
