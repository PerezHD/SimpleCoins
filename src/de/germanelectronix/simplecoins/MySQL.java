package de.germanelectronix.simplecoins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class MySQL {

	//MySQL connection
	private Connection connection;
	
	
	//Open the MySQL connection
	public synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + SimpleCoins.host + ":" + SimpleCoins.port + "/" + SimpleCoins.database + "?user=" + SimpleCoins.user + "&password=" + SimpleCoins.password + "&autoReconnect=true");
			System.out.println("[SC-API] Successfully connected to MySQL Database!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Add player to table
	public void addPlayer(Player player){
		try {
			String query = "INSERT IGNORE INTO coins (uuid,coins) VALUES(?, 0);";
			PreparedStatement p = this.connection.prepareStatement(query);
			p.setString(1, player.getUniqueId().toString());
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Check if database contains a player
	public boolean containsPlayer(String uuid) {
		String query = "SELECT * FROM coins WHERE uuid=?";
		try {
			PreparedStatement st = this.connection.prepareStatement(query);
			st = this.connection.prepareStatement("SELECT * FROM coins WHERE uuid=?");
			st.setString(1, uuid);
			ResultSet rs = st.executeQuery();
			return rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//Get player coins
	public Integer getColumn(Player p){
		Integer temp = 0;
		String query = "SELECT * FROM coins WHERE `uuid` = '" + p.getUniqueId() + "'";
		try {
			ResultSet res = this.connection.prepareStatement(query).executeQuery();
			while (res.next()) {
				temp = res.getInt("coins");
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}	
	
	//Create all tables
	public void createTables(){
		try {
			String query = "CREATE TABLE IF NOT EXISTS `coins` (`uuid` varchar(36) UNIQUE, `coins` int(1));";
			PreparedStatement p = this.connection.prepareStatement(query);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Set player coins in database
	public void setPlayerCoins(Player pl, Integer value){
		try {
			String query = "INSERT INTO coins (uuid,coins) VALUES(?, ?) ON DUPLICATE KEY UPDATE uuid=?, coins=?;";
			PreparedStatement p = this.connection.prepareStatement(query);
			p.setString(1, pl.getUniqueId().toString());
			p.setInt(2, value);
			p.setString(3, pl.getUniqueId().toString());
			p.setInt(4, value);
			p.execute();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
