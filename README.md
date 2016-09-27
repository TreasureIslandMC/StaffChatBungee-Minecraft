StaffChat creates two additional chat streams to the basic public chat, One for Administrators, and another for Moderators. These streams can be viewed and used by only those who have the permissions. The main advantage of this plugin is that the chat stream can be viewed in **

ALL servers** connected through BungeeCord, i.e. If a moderator says hello in the Lobby server, people on (for example) the SMP server who have the permission can read it and reply.

Toggle Feature: A toggle feature has been added to this plugin where, when a player toggles one of the chat streams, all their future messages go directly to that chat stream instead of public chat. This continues until the player toggles it again.

Please report any issues with this plugin [HERE](https://github.com/vik1395/StaffChatBungee/issues)

If you like my work, please consider donating, I would greatly appreciate it. [![Image](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=vik1395lp%40gmail%2ecom&lc=US&item_name=Spigot%20Plugins&item_number=LegitPlay%2enet%20Plugin%20Dev&no_note=0&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHostedGuest)

**Permissions - (Should be added to BungeeCord's config.yml)**
-------------

    staffchat.admin - Allows you to view and use the admin chat stream.
    
    staffchat.mod - Allows you to view and use the moderator chat stream.

**Commands**
-------------
    
    /ac [message] - For Admin chat stream
    or
    [Custom Command Alias] [message]
    
    /actoggle - Toggle Admin chat stream
    
    /mc [message] - For Moderator chat stream
    or
    [Custom Command Alias] [message]
    
    /mctoggle - Toggle Moderator chat stream

**Config**
-------------
Here, you can edit the Format of the Chat Tags, Admin & Mod chat aliases, and whether you want to allow chat colors in both streams.

When editing the format, make sure you dont remove "{TYPE}", "{NAME}" or "{MESSAGE}". They are a vital part of the config.

    Format: '&7[&6{TYPE} Chat&7] &c{NAME} &6: &r&e{MESSAGE}'
    
    # This is the format of the chat message that will come up in Staff chat. '&*' represent color codes.
    
    Chat Color: Allow
    
    # Type "Allow" to allow players to use chat colors. Else type "Deny".
    
    Admin Chat Message Alias: '@'
    
    # An optional alias for /ac. Please Do not leave this empty.
    
    Mod Chat Message Alias: '#'
    
    # An optional alias for /mc. Please Do not leave this empty.

Here is an example of how the plugin works/is used:
![enter image description here](http://i.imgur.com/0KrDRiC.jpg?1)

To type in Moderator Chat: ![Image](http://i.imgur.com/TSiRQ0Z.jpg?1)

This plugin is licensed under [CC Attribution-ShareAlike 4.0 International](http://creativecommons.org/licenses/by-sa/4.0/deed.en_US)

In very basic terms, Do whatever you want with the code of this plugin, as long as you give credits to the author and/or the plugin itself.

The **NON-Bungee** version of this plugin can be found **[HERE](http://www.spigotmc.org/resources/staffchat.363/)**
