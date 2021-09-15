package me.jishuna.moresmithing.armor.cactus;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jishuna.commonlib.items.ItemBuilder;
import me.jishuna.commonlib.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;

public class CactusChestplate extends CactusArmor {

	public CactusChestplate(YamlConfiguration config) {
		super("cactus_chestplate", config, new ItemBuilder(Material.LEATHER_CHESTPLATE)
				.withName(ChatColor.translateAlternateColorCodes('&', config.getString("items.chestplate.name", "Missing-Name")))
				.withDyeColor(StringUtils.getColor(config.getString("items.chestplate.color"))), config.getInt("items.chestplate.durability", 100));
	}
}
