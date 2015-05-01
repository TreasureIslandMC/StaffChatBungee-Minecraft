package me.vik1395.StaffChatBungee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class Bungee extends Plugin implements Listener
{
	public static Configuration config;
    public static ConfigurationProvider cProvider;
    public static File cFile;
	public static HashMap<String, Boolean> mctoggle = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> actoggle = new HashMap<String, Boolean>();
	public static String path;
	public static String aAlias;
	public static String mAlias;
	
	public void onEnable()
    {
		File cFolder = new File(this.getDataFolder(),"");
		
		if (!cFolder.exists()) 
		{
	        cFolder.mkdir();
		}
		
		cFile = new File(this.getDataFolder() + "/config.yml");
		
		if (!cFile.exists()) 
		{
	        try 
	        {
	        	String file = "Format: \'&7[&6{TYPE} Chat&7] &c{NAME} &6: &r&e{MESSAGE}\'\n"
	        			+ "# This is the format of the chat message that will come up in Moderator chat. \'&*\' represent color codes.\n"
	        			+ "Chat Color: Allow\n"
	        			+ "# Type \"Allow\" to allow players to use chat colors. Else type \"Deny\".\n"
	        			+ "Admin Chat Message Alias: \'@\'\n"
	        			+ "# An optional alias for /ac. Please Do not leave this empty.\n"
	        			+ "Mod Chat Message Alias: \'#\'\n"
	        			+ "# An optional alias for /mc. Please Do not leave this empty.";
	        	
	            FileWriter fw = new FileWriter(cFile);
				BufferedWriter out = new BufferedWriter(fw);
	            out.write(file);
	            out.close();
	            fw.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
		}
		
		cProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
	    try 
	    {
	        config = cProvider.load(cFile);
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    path = config.getString("Format");
	    mAlias = config.getString("Mod Chat Message Alias");
	    aAlias = config.getString("Admin Chat Message Alias");
	    
	    
		getLogger().info("StaffChat has successfully started!");
		getLogger().info("Created by Vik1395");
        getProxy().getPluginManager().registerListener(this, new PlayerChatListener(this));
        
        getProxy().getPluginManager().registerCommand(this, new Command("ac") 
        {
			@Override
			public void execute(CommandSender sender, String args[])
		    {
					if(sender instanceof ProxiedPlayer)
					{
						ProxiedPlayer p = (ProxiedPlayer)sender;
						if(p.hasPermission("staffchat.admin"))
				        {
							if(args.length<1)
			            	{
			            		
			            	}
			            	else
			            	{
								String msg = "";
								for(int i = 0; i < args.length; i++)
								{
									msg = msg + args[i] + ' ';
								}
					            
					            for(Iterator<ProxiedPlayer> iterator1 = getProxy().getPlayers().iterator(); iterator1.hasNext();)
					            {
					                ProxiedPlayer ap = (ProxiedPlayer)iterator1.next();
					                if(ap.hasPermission("staffchat.admin"))
					                {
					                	String apath = ""+path;
					                	apath = apath.replace("{TYPE}", "Admin");
										apath = apath.replace("{NAME}", p.getName());
										apath = ChatColor.translateAlternateColorCodes('&', apath);
										apath = apath.replace("{MESSAGE}", format(msg));
										ap.sendMessage(new TextComponent(apath));
					                }
					            }
			            	}
				        }
						else
						{
							p.sendMessage(new TextComponent("Sorry, you do not have permission to use the StaffChat plugin."));
						}
					}
		    }
        });
        
        getProxy().getPluginManager().registerCommand(this, new Command("actoggle") 
        {
        	@Override
			public void execute(CommandSender sender, String args[])
		    {
        		if(sender instanceof ProxiedPlayer)
    			{
    				ProxiedPlayer a = (ProxiedPlayer)sender;
    				if(a.hasPermission("staffchat.admin"))
    				{
    					if(actoggle.containsKey(a.getName()))
    					{
    						actoggle.remove(a.getName());
    						a.sendMessage(new TextComponent(ChatColor.GOLD + "Your normal messages will now go to public chat"));
    					} 
    					else if(mctoggle.containsKey(a.getName()))
    					{
    						a.sendMessage(new TextComponent(ChatColor.DARK_RED + "You cannot toggle Admin and Mod Chat streams on at the same " + ChatColor.DARK_RED + "time. Please toggle Admin Chat off."));
    					}
    					else 
    					{
    						actoggle.put(a.getName(), false);
    						a.sendMessage(new TextComponent(ChatColor.GOLD + "You have switched on Admin Chat. All your messages will now " + ChatColor.GOLD + "go to the Admin Chat Stream."));
    					}
    				}
    			}
		    }
        });
        
        getProxy().getPluginManager().registerCommand(this, new Command("mc") 
        {
			@Override
			public void execute(CommandSender sender, String args[])
		    {
					if(sender instanceof ProxiedPlayer)
					{
						ProxiedPlayer p = (ProxiedPlayer)sender;   
			            if(p.hasPermission("staffchat.mod"))
				        {
			            	if(args.length<1)
			            	{
			            		
			            	}
			            	else
			            	{
			            		String msg = "";
								for(int i = 0; i < args.length; i++)
								{
									msg = msg + args[i] + ' ';
								}
					            
					            for(Iterator<ProxiedPlayer> iterator1 = getProxy().getPlayers().iterator(); iterator1.hasNext();)
					            {
					                ProxiedPlayer mp = (ProxiedPlayer)iterator1.next();
					                if(mp.hasPermission("staffchat.mod"))
					                {
					                	String mpath = ""+path;
					                	mpath = mpath.replace("{TYPE}", "Mod");
										mpath = mpath.replace("{NAME}", p.getName());
										mpath = ChatColor.translateAlternateColorCodes('&', mpath);
										mpath = mpath.replace("{MESSAGE}", format(msg));
										mp.sendMessage(new TextComponent(mpath));
					                }
					            }
			            	}
							
				        }
						else
						{
							p.sendMessage(new TextComponent(ChatColor.RED + "Sorry, you do not have permission to use the StaffChat plugin."));
						}
					}
		    }
        });
        
        getProxy().getPluginManager().registerCommand(this, new Command("mctoggle") 
        {
        	@Override
			public void execute(CommandSender sender, String args[])
		    {
        		if(sender instanceof ProxiedPlayer)
    			{
    				ProxiedPlayer a = (ProxiedPlayer)sender;
    				if(a.hasPermission("staffchat.mod"))
    				{
    					if(mctoggle.containsKey(a.getName()))
    					{
    						mctoggle.remove(a.getName());
    						a.sendMessage(new TextComponent(ChatColor.GOLD + "Your normal messages will now go to public chat"));
    					} 
    					else if(actoggle.containsKey(a.getName()))
    					{
    						a.sendMessage(new TextComponent(ChatColor.DARK_RED + "You cannot toggle Admin and Mod Chat streams on at the same " + ChatColor.DARK_RED + "time. Please toggle Mod Chat off."));
    					}
    					else 
    					{
    						mctoggle.put(a.getName(), false);
    						a.sendMessage(new TextComponent(ChatColor.GOLD + "You have switched on Mod Chat. All your messages will now go " + ChatColor.GOLD + "to the Mod Chat Stream."));
    					}
    				}
    			}
		    }
        });
        
    }
	
	private String format(String input)
	{
		String Color = config.getString("Chat Color");
		
		if(Color.equalsIgnoreCase("Allow"))
		{
			return ChatColor.translateAlternateColorCodes('§', input);
		}
		else
		{
			return(ChatColor.stripColor(input));
		}
	}
}