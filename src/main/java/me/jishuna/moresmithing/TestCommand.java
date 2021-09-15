package me.jishuna.moresmithing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	private final MoreSmithing plugin;

	public TestCommand(MoreSmithing plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length < 1)
			return false;

		if (sender instanceof Player player) {

			String key = args[0];

			plugin.getCustomItemManager().getCustomItemOptional(key)
					.ifPresent(item -> player.getInventory().addItem(item.getItem()));
			return true;
		}
		return false;
	}

}
