package me.jishuna.moresmithing.armor.cactus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;

import me.jishuna.commonlib.items.ItemBuilder;
import me.jishuna.commonlib.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;

public class CactusHelmet extends CactusArmor {

	public CactusHelmet(YamlConfiguration config) {
		super("cactus_helmet", config,
				new ItemBuilder(Material.LEATHER_HELMET)
						.withName(ChatColor.translateAlternateColorCodes('&',
								config.getString("items.helmet.name", "Missing-Name")))
						.withDyeColor(StringUtils.getColor(config.getString("items.helmet.color"))),
				config.getInt("items.helmet.durability", 100));

		setBaseItem(Material.matchMaterial(config.getString("items.helmet.base")));
		setSecondaryItem(Material.matchMaterial(config.getString("items.helmet.addition")));
	}

	@Override
	public void addRecipes() {
		if (getBaseItem() != null && getSecondaryItem() != null) {
			
			SmithingRecipe recipe = new SmithingRecipe(NamespacedKey.fromString("moresmithing:cactus_helmet"),
					getItem(), new RecipeChoice.MaterialChoice(getBaseItem()),
					new RecipeChoice.MaterialChoice(getSecondaryItem()));

			Bukkit.addRecipe(recipe);
		}
	}
}
