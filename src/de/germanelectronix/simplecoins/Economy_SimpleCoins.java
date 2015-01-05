package de.germanelectronix.simplecoins;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Economy_SimpleCoins implements Economy {

	@Override
	public EconomyResponse bankBalance(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createPlayerAccount(String arg0) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		return true;
	}

	@Override
	public String currencyNamePlural() {
		return "Coins";
	}

	@Override
	public String currencyNameSingular() {
		return "Coin";
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		SimpleCoinsAPI.addCoins(Bukkit.getPlayerExact(arg0), (int) arg1);
		return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		SimpleCoinsAPI.addCoins(arg0, (int) arg1);
		return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		SimpleCoinsAPI.addCoins(Bukkit.getPlayerExact(arg0), (int) arg2);
		return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		SimpleCoinsAPI.addCoins(arg0, (int) arg2);
		return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public String format(double arg0) {
		return String.valueOf(arg0);
	}

	@Override
	public int fractionalDigits() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String arg0) {
		return (double) SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(arg0));
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		return (double) SimpleCoinsAPI.getCoins(arg0);
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		return (double) SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(arg0));
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		return (double) SimpleCoinsAPI.getCoins(arg0);
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return "SimpleCoinsAPI";
	}

	@Override
	public boolean has(String arg0, double arg1) {
		if(SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(arg0)) >= (int) arg1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		if(SimpleCoinsAPI.getCoins(arg0) >= (int) arg1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		if(SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(arg0)) >= (int) arg2){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		if(SimpleCoinsAPI.getCoins(arg0) >= (int) arg2){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(String arg0) {
		if(Bukkit.getPlayerExact(arg0).hasPlayedBefore()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		if(arg0.hasPlayedBefore()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		if(Bukkit.getPlayerExact(arg0).hasPlayedBefore()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		if(arg0.hasPlayedBefore()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		SimpleCoinsAPI.substractCoins(Bukkit.getPlayerExact(arg0), (int) arg1);
		return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		SimpleCoinsAPI.substractCoins(arg0, (int) arg1);
		return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		SimpleCoinsAPI.substractCoins(Bukkit.getPlayerExact(arg0), (int) arg2);
		return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		SimpleCoinsAPI.substractCoins(arg0, (int) arg2);
		return new EconomyResponse(arg2, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "It worked!");
	}
	
}
