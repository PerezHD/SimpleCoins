package de.germanelectronix.simplecoins.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.germanelectronix.simplecoins.SimpleCoins;
import de.germanelectronix.simplecoins.SimpleCoinsAPI;

public class GetCoins implements CommandExecutor {

	public GetCoins(SimpleCoins simpleCoins) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("simplecoins.getcoins")){
					p.sendMessage("§2§lYou have: §7" + SimpleCoinsAPI.getCoins(p).toString() + " §2§lCoins.");
				}
			} else {
				System.out.println("You have to be a player to run this command!");
			}
		} else if(args.length == 1){
			if(sender instanceof Player){ //Sender is Player
				Player p = (Player) sender;
				if(p.hasPermission("simplecoins.getcoins.others")){
					if(Bukkit.getPlayerExact(args[0]) != null){
						p.sendMessage("§2§lThe Player §2" + args[0] + "§2§l has §7" + SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(args[0])).toString() + " §2§lCoins.");
					} else {
						p.sendMessage("§c§lThe Player §7" + args[0] + " §c§lis currently not online.");
					}
				}
			} else { //Sender is Console
				if(Bukkit.getPlayerExact(args[0]) != null){
					System.out.println("The Player " + args[0] + " has " + SimpleCoinsAPI.getCoins(Bukkit.getPlayerExact(args[0])).toString() + " Coins.");
				} else {
					System.out.println("The Player " + args[0] + " is currently not online.");
				}
			}
		} else {
			if(sender instanceof Player){
				Player p = (Player) sender;
				p.sendMessage("§6§lUsage: §6/coins [player]");
			}
		}
		
		return true;
	}

}
