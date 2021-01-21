package me.vik1395.staffchatbungee.commands;

import me.vik1395.staffchatbungee.StaffChatBungeePlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author sarhatabaot
 */
public class ReloadCommand extends Command {
	private final StaffChatBungeePlugin plugin;

	public ReloadCommand(final StaffChatBungeePlugin plugin) {
		super("staffchatbungeereload", "scb.reload", "scbreload");
		this.plugin = plugin;
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
		sender.sendMessage(TextComponent.fromLegacyText("Reloaded config."));
		plugin.reload();
	}
}
