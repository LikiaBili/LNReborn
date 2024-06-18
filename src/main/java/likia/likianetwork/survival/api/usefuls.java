package likia.likianetwork.survival.api;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.papi.PapiDisplay;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;
import likia.likianetwork.survival.main;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class usefuls {
    public static void grantxpforplayer(Player player,@Nullable Integer value) {
        if (value != null || value != 0) {
            main.setData("main","exp",player,(Integer) main.getData("main","exp",player) + value);
            if(value < 0) {
                player.sendActionBar(ChatColor.DARK_AQUA + "" + main.getData("main","exp",player) + "/" + getXpNeedForLevelUp((Integer) main.getData("main","level",player)) + " §c(" + value + ")");
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 100, 0.1f);
            }else {
                player.sendActionBar(ChatColor.DARK_AQUA + "" + main.getData("main","exp",player) + "/" + getXpNeedForLevelUp((Integer) main.getData("main","level",player)) + " §b(+" + value + ")");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0.5f);
            }
        }
    }
    public static boolean completequest(Integer currentquest,Integer nextquest,Double rewardcoins,Integer rewardexp,Integer grantstat,String grantstattype,Player player){
        String currentquestname = new PapiDisplay().onRequest(player,"quest");

        if(currentquest == main.getData("main","questid",player)){
            String add = "";
            if(rewardcoins != 0 || rewardexp != 0 || grantstat != 0){
                add = add+"\n\n§a§l奖励";
                if(rewardcoins != 0){
                    add = add+"\n    §8+ §6"+rewardcoins+" §7ᄕ";

                    DecimalFormat df = new DecimalFormat("#.00"); // format
                    main.setData("main","purse",player,Double.valueOf(df.format((Double)main.getData("main","purse",player) + rewardcoins)));
                }if(rewardexp != 0){
                    add = add+"\n    §8+ "+ChatColor.DARK_AQUA+rewardexp+" §7\u111c";

                    main.setData("main","exp",player,(Integer)main.getData("main","purse",player) + rewardexp);
                }if(grantstat != 0){
                    add = add+"\n    §8+ §f"+grantstat+" §7\u4004";
                }
            }
            player.sendMessage("§7====================\n§f已完成任务：§e"+currentquestname+"\n"+add+"\n§7====================");
            player.playSound(player.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,0.5f);
            main.setData("main","questid",player,nextquest);
            return true;
        }
        return false;
    }
    public static String gettype(String str){
        if (str.equals("战斗配方菜单")) {
            return "combat";
        } else if (str.equals("工具配方菜单")) {
            return "tool";
        } else if (str.equals("农业配方菜单")) {
            return "farming";
        } else if (str.equals("附魔配方菜单")) {
            return "enchant";
        } else if (str.equals("工业配方菜单")) {
            return "industry";
        } else if (str.equals("防具配方菜单")) {
            return "armor";
        } else if (str.equals("杂项材料配方菜单")) {
            return "util";
        } else if (str.equals("战利品配方菜单")) {
            return "slayer";
        }else{
            return "";
        }
    }

    public static void dialouge(Player player,Sound soundEffect,Float soundPitch,Sound endSound,Float endSoundPitch,String[] dialouges,String npcName,Integer waitMills){
        new BukkitRunnable() {
            @Override
            public void run() {
                Integer range = 0;
                while(true){
                    if (range >= dialouges.length - 1) {
                        player.sendMessage("§[NPC] §e" + main.getLang(player, npcName) + "§f: " + main.getLang(player, dialouges[range]));
                        player.playSound(player.getLocation(), endSound, 1000000, endSoundPitch);
                        break;
                    } else {
                        player.sendMessage("§[NPC] §e" + main.getLang(player, npcName) + "§f: " + main.getLang(player, dialouges[range]));
                        player.playSound(player.getLocation(), soundEffect, 1000000, soundPitch);
                    }

                    try {
                        Thread.sleep(waitMills);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.runTask(main.getProvidingPlugin(likia.likianetwork.survival.main.class));
    }

    public static void dialouge(Player player,Sound soundEffect,Float soundPitch,String[] dialouges,String npcName,Integer waitMills){
        new BukkitRunnable() {
            @Override
            public void run() {
                Integer range = 0;
                while(true){
                    player.sendMessage("§[NPC] §e" + main.getLang(player, npcName) + "§f: " + main.getLang(player, dialouges[range]));
                    player.playSound(player.getLocation(), soundEffect, 1000000, soundPitch);

                    if (range >= dialouges.length - 1) {
                        break;
                    }

                    try {
                        Thread.sleep(waitMills);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.runTask(main.getProvidingPlugin(likia.likianetwork.survival.main.class));
    }

    public static Inventory menuQuitButton(Inventory menu,Player player){
        menu.setItem(49,newitem(Material.BARRIER,main.getLang(player,"ui_menu_buttons_name_close")));
        return menu;
    }

    public static String intToRareityStr(Integer rareityid){
        String[] list = {"§f§l普通","§a§l不寻常","§1§l稀有","§5§l奇异","§6§l传奇","§c§l???"};
        return list[rareityid - 1];
    }
    public static Inventory bgmenu(String menuname){
        Inventory menu = Bukkit.createInventory(null,6*9,menuname);
        ItemStack[] contents = menu.getContents();

        ItemStack bg = newitem(Material.GRAY_STAINED_GLASS_PANE," ");
        Integer range = 0;
        while(true){
            contents[range] = bg;
            range = range + 1;
            if(range >= 54){break;}
        }

        menu.setContents(contents);
        return menu;
    }
    public static ItemStack addrareity(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setBoolean("Placeable",true);
        if(nbtItem.getInteger("Rareity") == 0) {
            nbtItem.setInteger("Rareity", 1);
            List<String> lore = item.getLore();
            if (lore == null) {
                lore = new ArrayList<>();
            }
            lore.add(" ");
            lore.add(intToRareityStr(1));
            item.setLore(lore);
        }
        item = nbtItem.getItem();
        return item;
    }
    public static void boardcastLangMessage(String langMessage){
        Bukkit.getServer().getOnlinePlayers().forEach(player ->{
            player.sendMessage(main.getLang(player,langMessage));
        });
    }
    public static ItemStack newitem(Material material,String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack newitem(Material material,String name,Integer count){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    public static ItemStack newitem(Material material, String name,Integer count, List<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    public static ItemStack newitem(Material material, String name,Integer count, List<String> lore,Integer rareityid,String LikiaID){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        lore.add(" ");
        lore.add(intToRareityStr(rareityid));
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(count);
        //savenbt
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("Rareity",rareityid);
        nbtItem.setString("LikiaID",LikiaID);
        item = nbtItem.getItem();
        return item;
    }

    public static Integer caculatedamge(Integer dmg,Integer def){
        int defc = def+100;
        double defpercent = def / defc;
        defpercent = 1-defpercent;
        int dmgfinal = dmg;
        dmgfinal = (int) (defpercent * dmgfinal);
        return dmgfinal;
    }

    public static ItemStack addprice(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);
        System.out.println("A \""+nbtItem.getString("LikiaID")+"\"");
        if(nbtItem.getString("LikiaID").equals("")){
            System.out.println("B");
            nbtItem.setString("LikiaID",item.getType().toString());
            item = nbtItem.getItem();
            nbtItem = new NBTItem(item);
        }
        if (nbtItem.getDouble("Price") == 0.0) {
            Object[][] list = {
                    {"OAK_LOG", 0.2},
                    {"DARK_OAK_LOG", 0.25},
                    {"BIRCH_LOG", 0.2},
                    {"SPRUCE_LOG", 0.25},
                    {"JUNGLE_LOG", 0.35},
                    {"ACACIA_LOG", 0.3},
                    {"CRIMSON_STEM", 0.3},
                    {"WARPED_STEM", 0.3},
                    {"COAL", 0.4},
                    {"IRON_ORE", 0.6},
                    {"GOLD_ORE", 1.2},
                    {"LAPIS_LAZULI", 1.0},
                    {"DIAMOND", 20.0}
            };
            Integer range2 = 0;
            while (true) {
                if(range2 == list.length){break;}
                //if item match, set price
                if (nbtItem.getString("LikiaID").equalsIgnoreCase( (String) list[range2][0])) {
                    nbtItem.setDouble("Price", (Double) list[range2][1]);
                    item = nbtItem.getItem();
                    break;
                }
                range2++;
            }
        }
        return addrareity(item);
    }
    public static String getlevelcolor(Integer level){
        String color = "§b";
        if(level >= 61){color = ChatColor.DARK_RED+"";}
        else if(level >= 60){color = "§c";}
        else if(level >= 50){color = "§e";}
        else if(level >= 40){color = "§a";}
        else if(level >= 20){color = ChatColor.DARK_AQUA+"";}
        return color;
    }
    public static Integer getXpNeedForLevelUp(Integer level){
        Integer[] needxps = {10,20,30,40,50,60,70,80,90,100,120,140,160,180,200,220,240,260,280,300,310,320,330,340,350,360,370,380,390,400,410,420,430,440,450,460,470,480,490,
                500,505,510,515,520,525,530,535,540,545,550,555,560,565,570,575,580,585,590,595,25000,2147483647};
        if(level == null){
            level = 0;
        }
        if(level >= needxps.length){
            return needxps[needxps.length - 1];
        }
        return needxps[level];
    }
    public static String professidtoname(String id){
        if(Objects.equals(id, "carptender")){
            return "§a木匠";
        } else if (Objects.equals(id, "swordsman")) {
            return "§a剑客";
        } else if (Objects.equals(id, "miner")) {
            return "§a矿工";
        } else if (Objects.equals(id, "builder")) {
            return "§a建筑师";
        } else if (Objects.equals(id, "farmer")) {
            return "§a农夫";
        }else {
            return "§c无！";
        }
    }
    public static Inventory invbar(Material material,String align,Inventory inventory){
        ItemStack item = new ItemStack(material);
        ItemStack[] contents = inventory.getContents();
        if(align.equals("up")){
            contents[0]=item;
            contents[1]=item;
            contents[2]=item;
            contents[3]=item;
            contents[4]=item;
            contents[5]=item;
            contents[6]=item;
            contents[7]=item;
            contents[8] = item;
            inventory.setContents(contents);
            return inventory;
        }else if(align.equals("down")){
            contents[45]=item;
            contents[46]=item;
            contents[47]=item;
            contents[48]=item;
            contents[49]=item;
            contents[50]=item;
            contents[51]=item;
            contents[52]=item;
            contents[53] = item;
            inventory.setContents(contents);
            return inventory;
        }else if(align.equals("left")){
            contents[0]=item;
            contents[9]=item;
            contents[18]=item;
            contents[27]=item;
            contents[36]=item;
            contents[45] = item;
            inventory.setContents(contents);
            return inventory;
        }else if(align.equals("right")){
            contents[8]=item;
            contents[17]=item;
            contents[26]=item;
            contents[35]=item;
            contents[44]=item;
            contents[53] = item;
            inventory.setContents(contents);
            return inventory;
        }else {
            return inventory;
        }
    }

    public static void savedata(YamlConfiguration data, File file){
        try {
            data.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void banplayer(Integer time, Player player,String reason) {
        //ban player
        if (time <= 0) {
            player.banPlayer("§c你已被服务器永久封禁！\n\n§e原因："+reason+"\n§e如你的账号被盗用。你可以联系邮箱：likianetwork@outlook.com");
        }else{
            Time time1 = new Time(time);
            player.banPlayer("§c你已被服务器封禁"+time1+"！\n\n§e原因："+reason+"\n§a如你的账号被盗用。你可以联系邮箱：§alikianetwork@outlook.com");
            File mainfile = new File("D:/LikiaStudiosData/LikiaNetwork/","maindata.yml");
            YamlConfiguration maindata = YamlConfiguration.loadConfiguration(mainfile);
        }
    }
}
