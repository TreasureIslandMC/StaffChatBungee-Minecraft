package me.vik1395.staffchatbungee.commands;

import me.vik1395.staffchatbungee.ChannelType;
import me.vik1395.staffchatbungee.StaffChatBungeePlugin;

/**
 * @author sarhatabaot
 */
public class HelperChatCommand extends ACommand{
	public HelperChatCommand(final StaffChatBungeePlugin plugin) {
		super("shipchat", "scb.channel.helper", plugin);
	}

	@Override
	protected ChannelType getChannelType() {
		return ChannelType.SHIP;
	}
}
