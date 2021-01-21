package me.vik1395.staffchatbungee.commands;

import me.vik1395.staffchatbungee.StaffChatBungeePlugin;
import me.vik1395.staffchatbungee.ChannelType;

/**
 * @author sarhatabaot
 */
public class AdminChatCommand extends ACommand {
	public AdminChatCommand(final StaffChatBungeePlugin plugin) {
		super("capchat", "scb.channel.admin", plugin);
	}

	@Override
	protected ChannelType getChannelType() {
		return ChannelType.CAPTAIN;
	}
}
