package me.vik1395.staffchatbungee.commands;

import me.vik1395.staffchatbungee.StaffChatBungeePlugin;
import me.vik1395.staffchatbungee.ChannelType;

/**
 * @author sarhatabaot
 */
public class ModChatCommand extends ACommand{
	public ModChatCommand(final StaffChatBungeePlugin plugin) {
		super("crewchat","scb.channel.mod", plugin);
	}

	@Override
	protected ChannelType getChannelType() {
		return ChannelType.CREW;
	}
}
