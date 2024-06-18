package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.papi.PapiDisplay;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlayerJoin implements Listener {

    public static String[] useablemotds1 = {"§6Network service powered","§e§lFinally in alpha test!",ChatColor.DARK_RED+"Can't connect to server!","§cSUS AMONGUS LOL!","§bBiliBili!","§eAyo my little friend!",
    "§eWe make impossible §lPossible§r§e."};
    public static String[] useablemotds2 = {"§6by Starry Frp and CloudFlare!","","","","","",""};

    @EventHandler
    public void onLoad(ServerLoadEvent event){
    }

    //motd and stuff
    @EventHandler
    public void onListPing(ServerListPingEvent event){
        try {
            Integer random = new Random().nextInt(useablemotds1.length);
            event.setMotd("§aLikia §c[1.16.5] " +useablemotds1[random]+"\n§r§aNetwork "+useablemotds2[random]);
        }catch (Exception e){
            event.setMotd("§aLikia §c[1.16.5] §lWhat Happened?\n§r§aBugwork §c§lThis is a ERROR!");
        }

        if(Bukkit.getServer().getOnlinePlayers().toArray().length >= Bukkit.getServer().getMaxPlayers()){
            event.setMaxPlayers(Bukkit.getMaxPlayers());
            event.setMotd("§aLikia §c[1.16.5] §bWhat Happened?\n§r§aNetwork §b§lWHOA! We are currently FULL!");
        }else if(Bukkit.getServer().getMaxPlayers() < Bukkit.getServer().getMaxPlayers() / 4 * 3) {
            event.setMaxPlayers(114514);
        }
        try {
            event.setServerIcon(Bukkit.loadServerIcon(new File(main.getPlug().getDataFolder(),"icon.png")));
        } catch (Exception e) {
            event.setMotd("§aLikia §c[1.16.5] §lWhat Happened?\n§r§aBugwork §c§lThis is a ERROR!");
            throw new RuntimeException(e);
        }
    }

    static Object[][] defaultslist = {{"main","purse",0},{"main","level",0},{"main","exp",0},{"main","questid",1},{"main","language",1},{"main","baseDef",0},{"main","baseStr",0},
            {"main","baseHP",0},{"main","baseDmg",0},{"main","baseInt",0},{"main","whitelisted",false},{"config","packon",false},{"main","worldLocation.Saved",false},{"main","prestige",0},
            {"main","death.Saved",false},{"main","isStudioMember",false},{"npc","isFirstTalkToLink",true},{"main","renown",0},{"npc","meetWitch",0}};


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().setHealthScale(20);

        //if player join first time / player do not have the data stored then set the data to default data value


        Integer defaultrange = 0;
        while (true){
            if(defaultrange >= defaultslist.length){break;}

            if(main.getData((String) defaultslist[defaultrange][0],(String) defaultslist[defaultrange][1],event.getPlayer()) == null){
                main.setData((String) defaultslist[defaultrange][0],(String) defaultslist[defaultrange][1],event.getPlayer(),defaultslist[defaultrange][2]);
            }

            defaultrange++;
        }

        //Likia Whitelist and Join
        Date  AvalableJoinTime = new Date(2022,8,16,12,0,0);
        if(!event.getPlayer().isOp() || new Date(System.currentTimeMillis()).after( AvalableJoinTime)) {
            if (!(Boolean) main.getData("main", "whitelisted", event.getPlayer())) {
                event.getPlayer().kickPlayer("§c你还没有§l白名单！\n\n§r§cYou are not§l whitelisted! ");
                return;
            }else if(new Date(System.currentTimeMillis()).before( AvalableJoinTime) && false){
                SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
                event.getPlayer().kickPlayer("§c服务器还有§e"+dateFormat.format(new Date(AvalableJoinTime.getTime() - System.currentTimeMillis()))+"§c发布§aAlpha§c版本！\n\nTheres only §e"+dateFormat.format(new Date(AvalableJoinTime.getTime() - System.currentTimeMillis()))+" §cleft until§a Alpha §cversion release!! ");
                return;
            }
        }

        Player player = event.getPlayer();
        player.teleport(new Location(Bukkit.getWorld("hub"), 89.5,63,261.5,90,0));

        if(player.getName().equals("LikiaBili")){
            event.setJoinMessage(ChatColor.GRAY+"["+ChatColor.GREEN+"+"+ChatColor.GRAY+"] "+ChatColor.DARK_RED+"[OWNER] LikiaBili "+ChatColor.WHITE+"进入了服务器！");
            event.getPlayer().setDisplayName(ChatColor.DARK_RED+"[OWNER] LikiaBili");
            main.setDataTemp(new String[]{player.getName(),"PlayerListColor"},ChatColor.DARK_RED+"");
            main.setDataTemp(new String[]{player.getName(),"PlayerRankPrefix"},ChatColor.DARK_RED+"[OWNER]");
        }else if (event.getPlayer().isOp()) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.RED + "[ADMIN] " + ChatColor.WHITE + event.getPlayer().getName() + " 进入了服务器！");
            event.getPlayer().setDisplayName(ChatColor.RED + "[ADMIN] " + event.getPlayer().getName());
            main.setDataTemp(new String[]{player.getName(), "PlayerListColor"}, ChatColor.RED + "");
            main.setDataTemp(new String[]{player.getName(),"PlayerRankPrefix"},ChatColor.RED+"[ADMIN]");
        }else if((Boolean) main.getData("main","isStudioMember",player)){
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.BLUE+ "[STUDIO" +
                    "] " + ChatColor.WHITE + event.getPlayer().getName() + " 进入了服务器！");
            event.getPlayer().setDisplayName(ChatColor.RED + "[ADMIN] " + event.getPlayer().getName());
            main.setDataTemp(new String[]{player.getName(), "PlayerListColor"}, ChatColor.BLUE + "");
            main.setDataTemp(new String[]{player.getName(),"PlayerRankPrefix"},ChatColor.BLUE+"[STUDIO]");
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getPlayer().getName());
            event.getPlayer().setDisplayName(ChatColor.WHITE + event.getPlayer().getName());
        }
        //send official wiki
        player.sendMessage(main.getLang(player,"hint_wikilink")+"\n\n"+main.getLang(player,"hint_datahint")+"\n\n§a/lang chinese §e可以切换语言至中文！\n§a/lang english §ecan switch your langauge to english!");
        //send resource pack
        player.setResourcePack("");

        //leveling runable
        String playername = event.getPlayer().getName();
        new BukkitRunnable(){
            @Override
            public void run() {

                Player playerupd = Bukkit.getServer().getPlayer(playername);
                if(playerupd == null){
                    //if cant get player from player name meant this player have left the game so the runable will end
                    this.cancel();
                }else {
                    //get xp&level
                    Integer xp = (Integer) main.getData("main","exp",player);
                    Integer level = (Integer) main.getData("main","level",player);

                    if(xp >= usefuls.getXpNeedForLevelUp(level)){
                        main.setData("main","exp",player,(Integer) main.getData("level","exp",player) - usefuls.getXpNeedForLevelUp(level));
                        main.setData("main","level",player,level + 1);

                        //congrads
                        Integer levelnow = level + 1;
                        player.sendTitle("§e§l升级！","§7["+usefuls.getlevelcolor(level)+level+"§7] §f-> §7[§b"+usefuls.getlevelcolor(levelnow)+levelnow+"§7]",10,40,10);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,0.3f);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,0.6f);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,1);
                    }
                }
            }
        }.runTaskTimer(main.getPlug(),0,10);

    }

    @EventHandler
    public void PlayerLeft(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§a+§7]"+main.getDataTemp(new String[]{player.getName(),"PlayerRankPrefix"},"")+player.getName());

        if(player.getLocation().getWorld().getName().equalsIgnoreCase("world") || player.getLocation().getWorld().getName().equalsIgnoreCase("world_the_end") || player.getLocation().getWorld().getName().equalsIgnoreCase("world_nether")){
            Location playerLocation = player.getLocation();

            main.setData("main","worldLocation.World",player,playerLocation.getWorld().getName());
            main.setData("main","worldLocation.X",player,playerLocation.getX());
            main.setData("main","worldLocation.Y",player,playerLocation.getY());
            main.setData("main","worldLocation.Z",player,playerLocation.getZ());
            main.setData("main","worldLocation.Yaw",player,playerLocation.getYaw());
            main.setData("main","worldLocation.Pitch",player,playerLocation.getPitch());
            main.setData("main","worldLocation.Saved",player,true);
        }
    }
    @EventHandler
    public void statsrunable(PlayerJoinEvent event){
        //get player likiaid and stuff
        Player player = event.getPlayer();
        String playername = player.getName();
        Integer playerLID = main.playerID(player);
        //status runable
        main.setDataTemp(new String[]{playername,"Health"},100);
        main.setDataTemp(new String[]{playername,"Mana"},100);
        new BukkitRunnable(){
            @Override
            public void run() {
                Player playerupd = Bukkit.getServer().getPlayer(playername);
                if (playerupd == null) {
                    //if cant get player from player name meant this player have left the game so the runable will end
                    this.cancel();
                }
                //set custom name on scoreboard
                playerupd.setPlayerListName(new PapiDisplay().onRequest((OfflinePlayer) playerupd,"levelprefix") +" "+main.getDataTemp(new String[]{playername,"PlayerListColor"},"§f")+playername);
                playerupd.setDisplayName(main.getDataTemp(new String[]{playername,"PlayerRankPrefix"},"")+" "+new PapiDisplay().onRequest((OfflinePlayer) playerupd,"levelprefix")+main.getDataTemp(new String[]{playername,"PlayerListColor"},"")+" "+playername);

                //calc display hp
                int displayHp = (int) (playerupd.getHealth() - 20);
                displayHp = displayHp/100;
                displayHp = displayHp+20;
                playerupd.setHealthScale(displayHp);

                NBTItem held = new NBTItem(nulltovoid(player.getInventory().getItem(player.getInventory().getHeldItemSlot())));
                NBTItem helmet = new NBTItem(nulltovoid(player.getInventory().getHelmet()));
                NBTItem leggings = new NBTItem(nulltovoid(player.getInventory().getLeggings()));
                NBTItem chestplate = new NBTItem(nulltovoid(player.getInventory().getChestplate()));
                NBTItem boots = new NBTItem(nulltovoid(player.getInventory().getBoots()));

                //=====Defence=====
                Integer def = boots.getInteger("Defence") + leggings.getInteger("Defence") + chestplate.getInteger("Defence") + helmet.getInteger("Defence");
                def += (Integer) main.getData("main","baseDef",player); // get base def
                main.setDataTemp(new String[]{playername,"Defence"},def);

                //=====Strength=====
                Integer str = boots.getInteger("Strength") + leggings.getInteger("Strength") + leggings.getInteger("Strength") + held.getInteger("Strength") + helmet.getInteger("Strength");
                str += (Integer) main.getData("main","baseStr",player); // get base str
                main.setDataTemp(new String[]{playername,"Strength"},str);

                //=====Attack Damage=====
                // LOL yeah this formula is weird or i can just rename the str to damage multyplyer LOL
                Integer dmgc = held.getInteger("Attack") * str;
                dmgc = dmgc;
                Integer dmg = dmgc + held.getInteger("Attack");
                dmg = dmg+5; //hand bonus
                dmg += (Integer) main.getData("main","baseDmg",player); // get base dmg
                main.setDataTemp(new String[]{playername,"Attack"},dmg);

                //=====Health=====
                Integer maxHealth = 100 + boots.getInteger("Health") + leggings.getInteger("Health") + chestplate.getInteger("Health") + helmet.getInteger("Health");
                maxHealth += (Integer) main.getData("main","baseHP",player); // get base health
                Integer hp = 100;
                if(hp < maxHealth) {
                    hp += 1;
                }
                if (hp > maxHealth) {
                    hp = maxHealth;
                }
                main.setDataTemp(new String[]{"Health"},hp);
                main.setDataTemp(new String[]{"MaxHealth"},maxHealth);
                if(maxHealth < 2048) {
                    player.setMaxHealth(maxHealth);
                }else {
                    player.setMaxHealth(2048);
                }
                if(hp < 2048) {
                    player.setHealth(hp);
                }else {
                    player.setHealth(2048);
                }

                //=====intellence=====
                Integer intellgence = held.getInteger("Intellgence") + helmet.getInteger("Intellgence") + leggings.getInteger("Intellgence") + chestplate.getInteger("Intellgence") + boots.getInteger("Intellgence");
                intellgence += (Integer) main.getData("main","baseInt",player); // get base intellgence

                Integer maxmana = 100 + intellgence;
                Integer mana = (Integer) main.getDataTemp(new String[]{playername,"Mana"},100);
                if(mana < maxmana) {
                    int calcmana = intellgence - mana + 100;
                    calcmana /= 20;
                    mana +=  calcmana;
                }
                if (mana > maxmana) {
                    mana = maxmana;
                }main.setDataTemp(new String[]{playername,"Mana"},mana);


                //send player stats
                player.sendActionBar(PlaceholderAPI.setPlaceholders(player,"§c"+hp+"/"+maxHealth+"%lnssymbol_heart%     §a"+def+"%lnssymbol_defence%"+main.getLang(player,"stats_defence")+"     §b"+mana+"/"+maxmana+"%lnssymbol_pencil%"+main.getLang(player,"stats_mana")));
            }
        }.runTaskTimer(main.getPlug(),20,20);
    }
    private ItemStack nulltovoid(ItemStack item){
        if(item == null || item.isSimilar(new ItemStack(Material.AIR))){
            return new ItemStack(Material.STRUCTURE_VOID);
        }else {
            return item;
        }
    }
}
