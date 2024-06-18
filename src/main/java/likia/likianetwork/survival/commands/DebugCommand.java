package likia.likianetwork.survival.commands;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.fileoperation;
import likia.likianetwork.survival.api.papi.PapiDisplay;
import likia.likianetwork.survival.main;
import likia.likianetwork.survival.storage.CraftingItemList;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DebugCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @Nullable String[] args) {
        if(args[0].equals("getyaw")){
            Player player = (Player) sender;
            sender.sendMessage(ChatColor.GREEN+"你目前的Yaw:"+ChatColor.YELLOW+player.getLocation().getYaw());
        }
        if(args[0].equals("getpitch")){
            Player player = (Player) sender;
            sender.sendMessage(ChatColor.GREEN+"你目前的Pitch:"+ChatColor.YELLOW+player.getLocation().getPitch());
        }
        if(args[0].equals("getcommandname")){
            sender.sendMessage(ChatColor.GREEN+"你目前的指令:"+ChatColor.YELLOW+"\""+command.getName()+"\"");
        }
        if(args[0].equals("getname")){
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN+"你目前的名字:"+ChatColor.YELLOW+player.getName());
        }
        if(args[0].equals("getitems")){
            List<ItemStack> items = CraftingItemList.getItems(args[1],(Player) sender);
            sender.sendMessage(ChatColor.GREEN+"你请求的物品列表:"+ChatColor.YELLOW+items.toString());
        }
        if(args[0].equals("trylist")){
            String[] list = {"a","b","c"};
            sender.sendMessage(ChatColor.GREEN+"尝试创建的列表："+ChatColor.YELLOW+list);
            if(args[1] != null) {
                sender.sendMessage(ChatColor.GREEN + "逐步发送的列表：" + ChatColor.YELLOW + list[0]);
                sender.sendMessage(ChatColor.GREEN + "逐步发送的列表：" + ChatColor.YELLOW + list[1]);
                sender.sendMessage(ChatColor.GREEN + "逐步发送的列表：" + ChatColor.YELLOW + list[2]);
            }
        }
        if(args[0].equalsIgnoreCase("reloadlang") && sender.isOp()){
            fileoperation.langfiles = new File[]{
                    new File(fileoperation.studiosDataDisk + "/LikiaStudiosData/LikiaNetwork/Lang/Survival", "en_us.yml"),
                    new File(fileoperation.studiosDataDisk + "/LikiaStudiosData/LikiaNetwork/Lang/Survival", "zh_cn.yml")};
            fileoperation.langdatas = new FileConfiguration[]
                    {YamlConfiguration.loadConfiguration(fileoperation.langfiles[0]), YamlConfiguration.loadConfiguration(fileoperation.langfiles[1])};
            sender.sendMessage(main.getLang(sender.getName(),"message_done"));
        }
        if(args[0].equalsIgnoreCase("whiletest") && sender.isOp()){
            long starttime = System.currentTimeMillis();
            int range = 0;
            int abc = 0;
            String test = "[asdiflhdkrshukghfsgkjuhsugykhsfkjyedashfyi,gduighduigydifdhhsdjl]";
            while(true){
                if(range >= 100000000){
                    long elapsed = System.currentTimeMillis()-starttime;
                    sender.sendMessage("elapsed:"+elapsed+"range:"+range);
                    abc = 1;
                    break;
                }

                if(test.equals("sgjkhsdflcyujlvhsljvhjsfkvgjklgjsfljgsjklghsfkuvhsdkjhgfjksf")){
                    break;
                }

                range++;
            }
        }
        if(args[0].equals("numbersmenu")){
            Inventory menu = Bukkit.createInventory(null,6*9,"test");
            ItemStack[] contents = menu.getContents();

            ItemStack bg;
            contents[0] = new ItemStack(Material.AIR);
            Integer range = 1;
            while(true){
                bg = newitem(Material.GRAY_STAINED_GLASS_PANE," ");
                bg.setAmount(range);
                contents[range] = bg;
                range = range + 1;
                if(range >= 54){break;}
            }

            menu.setContents(contents);
            ((Player) sender).openInventory(menu);
        }
        if(args[0].equalsIgnoreCase("damage") && sender.isOp() && args.length == 3){

        }
        if(args[0].equalsIgnoreCase("prestiges")){
            sender.sendMessage(new PapiDisplay().prestigeColor.length+"");
        }
        if(args[0].equalsIgnoreCase("stopserver") && args.length == 2 && sender.isOp()){
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage("REBOOTING IN 10");
                sleep(1000);
                player.sendMessage("REBOOTING IN 9");
                sleep(1000);
                player.sendMessage("REBOOTING IN 8");
                sleep(1000);
                player.sendMessage("REBOOTING IN 7");
                sleep(1000);
                player.sendMessage("REBOOTING IN 6");
                sleep(1000);
                player.sendMessage("REBOOTING IN 5");
                sleep(1000);
                player.sendMessage("REBOOTING IN 4");
                sleep(1000);
                player.sendMessage("REBOOTING IN 3");
                sleep(1000);
                player.sendMessage("REBOOTING IN 2");
                sleep(1000);
                player.sendMessage("REBOOTING IN 1");
                sleep(1000);
            });
            ((Player)sender).chat("/stop");
        }
        if(args[0].equals("admingear") && sender.getName().equals("LikiaBili")){
            Player player = (Player) sender;
            player.getInventory().addItem(RecipeItemList.newitemautolore(Material.DIAMOND_HELMET,6,"ADMIN_HELMET","管理员头盔",-1,0.0,0,200000000,200000000,200000000,2000000000,1,false));
            player.getInventory().addItem(RecipeItemList.newitemautolore(Material.DIAMOND_SWORD,6,"ADMIN_SWORD","管理员剑",-1,0.0,200000000,0,0,0,0,1,false));
        }
        if (args[0].equals("clutch") && sender.isOp()){
            Player player = (Player) sender;
            ItemStack clutch = new ItemStack(Material.BEDROCK);
            NBTItem nbtItem = new NBTItem(clutch);
            nbtItem.setString("LikiaID","CLUTCH_BLOCK");
            clutch = nbtItem.getItem();
            player.getInventory().addItem(clutch);
        }
        if(args[0].equals("rottenfleshtest")){
            if(new ItemStack(Material.ROTTEN_FLESH).isSimilar(((Player) sender).getInventory().getItem(((Player)sender).getInventory().getHeldItemSlot()))) {
                sender.sendMessage("TRUE");
            }else{
                sender.sendMessage(new ItemStack(Material.ROTTEN_FLESH)+"");
                sender.sendMessage(((Player) sender).getInventory().getItem(((Player)sender).getInventory().getHeldItemSlot())+"");
            }
        }
        if(args[0].equalsIgnoreCase("idtostrtest")){
            sender.sendMessage(ChatColor.GREEN+"ID:"+ChatColor.YELLOW+Material.ACACIA_DOOR.toString());
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args){
        if(args.length == 1){
            //create list
            List<String> list = new ArrayList<>();
            list.add("numbersmenu");
            if(args[0] == "fuckyoulikia"){
                list.add("YOUMORON");
            }
            return list;
        }else {
            return null;
        }
    }

    private void sleep(Integer mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private Inventory bgmenu(){
        Inventory menu = Bukkit.createInventory(null,6*9,"menu");
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

    private ItemStack newitem(Material material,String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    private ItemStack newitem(Material material,String name,Integer count){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    private ItemStack newitem(Material material, String name,Integer count, List<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
}
