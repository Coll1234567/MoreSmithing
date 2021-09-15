package me.jishuna.moresmithing.armor.cactus;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.persistence.PersistentDataType;

import me.jishuna.commonlib.items.ItemBuilder;
import me.jishuna.moresmithing.CustomArmor;
import me.jishuna.moresmithing.PluginKeys;

public class CactusArmor extends CustomArmor {
	private final double chance;
	private double multiplier;

	public CactusArmor(String key, YamlConfiguration config, ItemBuilder builder, int durability) {
		super(key, "cactus", config, builder.withPersistantData(PluginKeys.DURABILITY.getKey(), PersistentDataType.INTEGER, durability), durability);

		addEventHandler(EntityDamageEvent.class, this::onDamage);
		addEventHandler(EntityDamageByEntityEvent.class, this::onDamageByEntity);
		this.chance = config.getInt("thorns-chance", 25) / 100d;
		this.multiplier = config.getInt("damage-percentage", 25) / 100d;
	}

	private void onDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.CONTACT) {
			event.setCancelled(true);
		}
	}

	private void onDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof LivingEntity livingEntity
				&& ThreadLocalRandom.current().nextFloat() < this.chance) {
			livingEntity.damage(event.getDamage() * this.multiplier, event.getEntity());
		}
	}

}
