package me.jishuna.moresmithing.armor.cactus;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jishuna.commonlib.items.ItemBuilder;
import me.jishuna.commonlib.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;

public class CactusBoots extends CactusArmor {

	public CactusBoots(YamlConfiguration config) {
		super("cactus_boots", config, new ItemBuilder(Material.LEATHER_BOOTS)
				.withName(ChatColor.translateAlternateColorCodes('&', config.getString("items.boots.name", "Missing-Name")))
				.withDyeColor(StringUtils.getColor(config.getString("items.boots.color"))), config.getInt("items.boots.durability", 100));
	}
}
