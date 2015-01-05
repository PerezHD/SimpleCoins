package de.germanelectronix.simplecoins.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.germanelectronix.simplecoins.SimpleCoins;
import de.germanelectronix.simplecoins.SimpleCoinsAPI;

public class TakeCoins implements CommandExecutor {

	public TakeCoins(SimpleCoins simpleCoins) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args[0].matches("[0-9]+")){
					if(p.hasPermission("simplecoins.takecoins")){
						SimpleCoinsAPI.substractCoins(p, Integer.parseInt(args[0])); //From coins of a players wallet
						p.sendMessage("§2§lYou took §7" + args[0] + " §2§lcoins from your wallet.");
					}
				} else {
					p.sendMessage("§c" + args[0] + " §cis not a valid number!");
				}
			}
		} else if(args.length == 2){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args[1].matches("[0-9]+")){
					if(p.hasPermission("simplecoins.takecoins.others")){
						if(Bukkit.getPlayerExact(args[0]) != null){
							SimpleCoinsAPI.substractCoins(p, Integer.parseInt(args[1])); //Take coins from a players wallet
							p.sendMessage("§2§lYou took §7" + args[1] + " §2§lcoins from the wallet of §7" + args[0]);
						} else {
							p.sendMessage("§c§lThe Player §7" + args[0] + " §c§lis currently not online.");
						}
					}
				} else {
					p.sendMessage("§c" + args[1] + " §cis not a valid number!");
				}
			}
		} else {
			if(sender instanceof Player){
				Player p = (Player) sender;
				p.sendMessage("§6§lUsage: §6/takecoins [amount] §1OR §6/takecoins [player] [amount]");
			}
		}
		
		return true;
	}
	
}
