package me.vik1395.staffchatbungee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.vik1395.staffchatbungee.commands.AdminChatCommand;
import me.vik1395.staffchatbungee.commands.HelperChatCommand;
import me.vik1395.staffchatbungee.commands.ModChatCommand;
import me.vik1395.staffchatbungee.commands.ReloadCommand;
import me.vik1395.staffchatbungee.listeners.PlayerChatListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class StaffChatBungeePlugin extends Plugin implements Listener {
	private Configuration config;

	private final Map<UUID, ChannelType> playerCache = new HashMap<>();


	private static String BASE_PERMISSION = "scb.channel.";

	public Map<UUID, ChannelType> getPlayerCache() {
		return playerCache;
	}

	public String getFormat(ChannelType channelType) {
		return config.getString("chat.format." + channelType.name());
	}

	@Override
	public void onEnable() {
		loadAndCreateConfig();
		try {
			this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		final PluginManager pluginManager = getProxy().getPluginManager();
		pluginManager.registerListener(this, new PlayerChatListener(this));
		pluginManager.registerCommand(this, new AdminChatCommand(this));
		pluginManager.registerCommand(this, new ModChatCommand(this));
		pluginManager.registerCommand(this, new HelperChatCommand(this));
		pluginManager.registerCommand(this,new ReloadCommand(this));
		getLogger().info("StaffChat has successfully started! Created by Vik1395, maintained by sarhatabaot");
	}

	private void loadAndCreateConfig() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();

		File file = new File(getDataFolder(), "config.yml");


		if (!file.exists()) {
			try (InputStream in = getResourceAsStream("config.yml")) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void reload(){
		try {
			this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(final ProxiedPlayer proxiedPlayer, final String eventMessage, final ChannelType channelType) {
		final String message = ChatColor.translateAlternateColorCodes('&', getFormat(channelType).replace("{TYPE}", channelType.name())
				.replace("{NAME}", proxiedPlayer.getName())
				.replace("{MESSAGE}", eventMessage));

		for (ProxiedPlayer receiver : getProxy().getPlayers()) {
			if (receiver.hasPermission("scb.channel." + channelType.name())) {
				receiver.sendMessage(new TextComponent(message));
			}
		}


	}
}