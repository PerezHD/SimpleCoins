package de.germanelectronix.simplecoins.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.germanelectronix.simplecoins.SimpleCoins;
import de.germanelectronix.simplecoins.SimpleCoinsAPI;

public class SetCoins implements CommandExecutor {

	public SetCoins(SimpleCoins simpleCoins) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args[0].matches("[0-9]+")){
					if(p.hasPermission("simplecoins.setcoins")){
						SimpleCoinsAPI.setCoins(p, Integer.parseInt(args[0])); //Set players coins
						p.sendMessage("§2§lYour coins were set to §7" + args[0]);
					}
				} else {
					p.sendMessage("§c" + args[0] + " §cis not a valid number!");
				}
			}
		} else if(args.length == 2){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args[1].matches("[0-9]+")){
					if(p.hasPermission("simplecoins.setcoins.others")){
						if(Bukkit.getPlayerExact(args[0]) != null){
							SimpleCoinsAPI.setCoins(p, Integer.parseInt(args[1])); //Set players coins
							p.sendMessage("§2§lThe coins of §2" + args[0] + " §2§lwere set to §7" + args[1]);
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
				p.sendMessage("§6§lUsage: §6/setcoins [value] §1OR §6/setcoins [player] [value]");
			}
		}
		
		return true;
	}

}
