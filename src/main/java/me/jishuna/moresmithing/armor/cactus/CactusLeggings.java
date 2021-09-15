package me.jishuna.moresmithing.armor.cactus;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jishuna.commonlib.items.ItemBuilder;
import me.jishuna.commonlib.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;

public class CactusLeggings extends CactusArmor {

	public CactusLeggings(YamlConfiguration config) {
		super("cactus_leggings", config, new ItemBuilder(Material.LEATHER_LEGGINGS)
				.withName(ChatColor.translateAlternateColorCodes('&', config.getString("items.leggings.name", "Missing-Name")))
				.withDyeColor(StringUtils.getColor(config.getString("items.leggings.color"))), config.getInt("items.leggings.durability", 100));
	}
}
