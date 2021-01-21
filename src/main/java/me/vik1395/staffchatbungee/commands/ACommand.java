package me.vik1395.staffchatbungee.commands;


import me.vik1395.staffchatbungee.StaffChatBungeePlugin;
import me.vik1395.staffchatbungee.ChannelType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public abstract class ACommand extends Command {
	protected StaffChatBungeePlugin plugin;
	protected ProxiedPlayer player;

	public ACommand(final String name, final StaffChatBungeePlugin plugin) {
		super(name);
		this.plugin = plugin;
	}


	public ACommand(final String name, final String permission, final StaffChatBungeePlugin plugin, final String... aliases) {
		super(name, permission, aliases);
		this.plugin = plugin;
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
		if(!(sender instanceof ProxiedPlayer)){
			sender.sendMessage(TextComponent.fromLegacyText("You must be a player to use this command."));
			return;
		}

		this.player = (ProxiedPlayer) sender;

		if(args.length == 0) {
			onToggleCommand();
			return;
		}

		onCommand(args);
	}

	protected void onCommand(final String[] args) {

		final StringBuilder sb = new StringBuilder();
		for(String str: args){
			sb.append(str).append(" ");
		}
		plugin.sendMessage(player,sb.toString(), getChannelType());
	}

	protected void onToggleCommand() {
		if(plugin.getPlayerCache().get(player.getUniqueId()) == getChannelType()) {
			plugin.getPlayerCache().remove(player.getUniqueId());
			player.sendMessage(new TextComponent(String.format("You have left the %s channel",getChannelType().name())));
		} else {
			plugin.getPlayerCache().put(player.getUniqueId(),getChannelType());
			player.sendMessage(new TextComponent(String.format("You have joined the %s channel",getChannelType().name())));
		}
	}

	protected abstract ChannelType getChannelType();
}
