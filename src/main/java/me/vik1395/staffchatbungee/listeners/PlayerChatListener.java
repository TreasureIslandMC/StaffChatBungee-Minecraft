package me.vik1395.staffchatbungee.listeners;

import me.vik1395.staffchatbungee.StaffChatBungeePlugin;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatListener implements Listener {
	private final StaffChatBungeePlugin plugin;

	public PlayerChatListener(StaffChatBungeePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(ChatEvent event) {
		if (!(event.getSender() instanceof ProxiedPlayer)) {
			return;
		}
		if (event.getMessage().startsWith("/"))
			return;

		final ProxiedPlayer proxiedPlayer = (ProxiedPlayer) event.getSender();
		if (plugin.getPlayerCache().containsKey(proxiedPlayer.getUniqueId())) {
			plugin.sendMessage(proxiedPlayer, event.getMessage(), plugin.getPlayerCache()
					.get(proxiedPlayer.getUniqueId()));
			event.setCancelled(true);
		}
	}

}
