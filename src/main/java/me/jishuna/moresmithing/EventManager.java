package me.jishuna.moresmithing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import me.jishuna.commonlib.events.EventConsumer;

public class EventManager {
	private final MoreSmithing plugin;

	private Map<Class<? extends Event>, EventConsumer<? extends Event>> listenerMap = new HashMap<>();

	public EventManager(MoreSmithing plugin) {
		this.plugin = plugin;
		registerBaseEvents();
	}

	public <T extends Event> boolean registerListener(Class<T> eventClass, Consumer<T> handler) {
		if (isListenerRegistered(eventClass))
			return false;

		EventConsumer<? extends Event> consumer = new EventConsumer<>(eventClass, handler);
		consumer.register(this.plugin);

		this.listenerMap.put(eventClass, consumer);
		return true;
	}

	public boolean isListenerRegistered(Class<? extends Event> eventClass) {
		return this.listenerMap.containsKey(eventClass);
	}

	public <T extends PlayerEvent> void processPlayerEvent(T event, Class<T> eventClass) {
		processEvent(event.getPlayer(), event, eventClass);
	}

	public <T extends EntityEvent> void processEntityEvent(T event, Class<T> eventClass) {
		if (event.getEntity() instanceof LivingEntity livingEntity)
			processEvent(livingEntity, event, eventClass);
	}

	public <T extends Event> void processEvent(LivingEntity entity, T event, Class<T> eventClass) {
		for (ItemStack item : entity.getEquipment().getArmorContents()) {
			Optional<CustomItem> itemOptional = this.plugin.getCustomItemManager().getCustomItemOptional(item);

			if (itemOptional.isPresent()) {
				itemOptional.get().getEventHandlers(eventClass).forEach(consumer -> consumer.consume(event));
			}
		}
	}

	private void registerBaseEvents() {

		// Combat
		registerListener(EntityDamageEvent.class, event -> processEntityEvent(event, EntityDamageEvent.class));

		registerListener(EntityDamageByEntityEvent.class, event -> {
			processEntityEvent(event, EntityDamageByEntityEvent.class);
			if (event.getDamager() instanceof LivingEntity livingEntity) {
				processEvent(livingEntity, event, EntityDamageByEntityEvent.class);
			} else if (event.getDamager() instanceof Projectile
					&& ((Projectile) event.getDamager()).getShooter() instanceof Player player) {
				processEvent(player, event, EntityDamageByEntityEvent.class);
			}
		});
}

}
