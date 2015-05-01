package me.vik1395.StaffChatBungee;

import java.util.Iterator;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatListener implements Listener 
{
	private Bungee plugin;
    public PlayerChatListener(Bungee instance)
    {
        this.plugin = instance;
    }

    @EventHandler
    public void onPlayerChat(ChatEvent e) 
    {
    	ProxiedPlayer m = (ProxiedPlayer) e.getSender();
        String msg = e.getMessage();
        
        if(msg.startsWith(Bungee.aAlias))
		{
        	
            if(m.hasPermission("staffchat.admin"))
            {
            	if(msg.length()<2)
            	{
            		
            	}
            	else
            	{
	            	msg = msg.substring(1, msg.length());
	            	for(Iterator<ProxiedPlayer> iterator1 = plugin.getProxy().getPlayers().iterator(); iterator1.hasNext();)
	                {
	                    ProxiedPlayer ap = (ProxiedPlayer)iterator1.next();
	                    if(ap.hasPermission("staffchat.admin"))
	                    {
	                    	String apath = ""+Bungee.path;
	                    	apath = apath.replace("{TYPE}", "Admin");
	    					apath = apath.replace("{NAME}", m.getName());
	    					apath = ChatColor.translateAlternateColorCodes('&', apath);
	    					apath = apath.replace("{MESSAGE}", format(msg));
	    					ap.sendMessage(new TextComponent(apath));
	                    }
	                }
            	}
            	e.setCancelled(true);
            }
		}
        
        else if(msg.startsWith(Bungee.mAlias))
		{
            if(m.hasPermission("staffchat.mod"))
            {
            	if(msg.length()<2)
            	{
            		
            	}
            	else
            	{
	            	msg = msg.substring(1, msg.length());
	            	for(Iterator<ProxiedPlayer> iterator1 = plugin.getProxy().getPlayers().iterator(); iterator1.hasNext();)
	                {
	                    ProxiedPlayer mp = (ProxiedPlayer)iterator1.next();
	                    if(mp.hasPermission("staffchat.mod"))
	                    {
	                    	String mpath = ""+Bungee.path;
	                    	mpath = mpath.replace("{TYPE}", "Mod");
	    					mpath = mpath.replace("{NAME}", m.getName());
	    					mpath = ChatColor.translateAlternateColorCodes('&', mpath);
	    					mpath = mpath.replace("{MESSAGE}", format(msg));
	    					mp.sendMessage(new TextComponent(mpath));
	                    }
	                }
            	}
            	e.setCancelled(true);
            }
		}
        
        else if(!e.getMessage().startsWith("/") && e.getSender() instanceof ProxiedPlayer) 
        {
            if(m.hasPermission("staffchat.admin"))
    		{
    			if(Bungee.actoggle.containsKey(m.getName()))
    			{
    				for(Iterator<ProxiedPlayer> iterator1 = plugin.getProxy().getPlayers().iterator(); iterator1.hasNext();)
                    {
                        ProxiedPlayer ap = (ProxiedPlayer)iterator1.next();
                        if(ap.hasPermission("staffchat.admin"))
                        {
                        	String apath = ""+Bungee.path;
                        	apath = apath.replace("{TYPE}", "Admin");
        					apath = apath.replace("{NAME}", m.getName());
        					apath = ChatColor.translateAlternateColorCodes('&', apath);
        					apath = apath.replace("{MESSAGE}", format(msg));
        					ap.sendMessage(new TextComponent(apath));
                        }
                    }
                	e.setCancelled(true);
    			}
    		}
            
            if(m.hasPermission("staffchat.mod"))
    		{
    			if(Bungee.mctoggle.containsKey(m.getName()))
    			{
    				for(Iterator<ProxiedPlayer> iterator1 = plugin.getProxy().getPlayers().iterator(); iterator1.hasNext();)
                    {
                        ProxiedPlayer mp = (ProxiedPlayer)iterator1.next();
                        if(mp.hasPermission("staffchat.mod"))
                        {
                        	String mpath = ""+Bungee.path;
                        	mpath = mpath.replace("{TYPE}", "Mod");
        					mpath = mpath.replace("{NAME}", m.getName());
        					mpath = ChatColor.translateAlternateColorCodes('&', mpath);
        					mpath = mpath.replace("{MESSAGE}", format(msg));
        					mp.sendMessage(new TextComponent(mpath));
                        }
                    }
                	e.setCancelled(true);
    			}
    		}
    		
        }
    }
    
    private String format(String input)
	{
		String Color = Bungee.config.getString("Chat Color");
		
		if(Color.equalsIgnoreCase("Allow"))
		{
			return ChatColor.translateAlternateColorCodes('&', input);
		}
		else
		{
			return(ChatColor.stripColor(input));
		}
	}
}
