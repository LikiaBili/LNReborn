package likia.likianetwork.survival.storage;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CraftingItemList {
    public static List<ItemStack> getItems(String type, Player player){
        if(type.equals("combat")) {
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.WOODEN_SWORD, "粗制腐肉剑", 10.0, 1, "level",player,0,type));
            items.add(newitemautolore(Material.GOLDEN_SWORD, "僵尸剑", 25.0, 3, "level",player,1,type));
            return items;
        }else if(type.equals("test")){
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.DIAMOND, "测试用物品1", 2147483647.0, null, null,player,0,type));
            items.add(newitemautolore(Material.NETHERITE_HOE, "测试用物品2", 0.0, 99999, "level",player,1,type));
            items.add(newitemautolore(Material.DIAMOND_SHOVEL, "测试用物品3", 0.0, 7, "rank",player,2,type));
            return items;
        }else if(type.equals("tool")){
            List<ItemStack> items = new ArrayList<>();
            return items;
        }else if(type.equals("farming")){
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.HAY_BLOCK, "压缩干草块",0.0,2,"level",player,0,type));
            items.add(newitemautolore(Material.BREAD, "压缩面包",5.0,2,"level",player,1,type));
            return items;
        }else if(type.equals("enchant")){
            List<ItemStack> items = new ArrayList<>();
            return items;
        }else if(type.equals("industry")){
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.BLAST_FURNACE,"钢炉",10.0,3,"level",player,0,type));
            return items;
        }else if(type.equals("armor")){
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.LEATHER_HELMET, "粗制腐肉头盔",5.0,2,"level",player,0,type));
            items.add(newitemautolore(Material.LEATHER_CHESTPLATE, "粗制腐肉胸甲",5.0,2,"level",player,1,type));
            items.add(newitemautolore(Material.LEATHER_LEGGINGS, "粗制腐肉护腿",5.0,2,"level",player,2,type));
            items.add(newitemautolore(Material.LEATHER_BOOTS, "僵尸钢靴子",5.0,3,"level",player,3,type));
            items.add(newitemautolore(Material.GOLDEN_HELMET, "僵尸钢头盔",10.0,3,"level",player,4,type));
            items.add(newitemautolore(Material.GOLDEN_CHESTPLATE, "僵尸钢胸甲",15.0,3,"level",player,5,type));
            items.add(newitemautolore(Material.GOLDEN_LEGGINGS, "僵尸钢护腿",15.0,3,"level",player,6,type));
            items.add(newitemautolore(Material.GOLDEN_BOOTS, "僵尸钢靴子",10.0,3,"level",player,7,type));
            return items;
        }else if(type.equals("util")){
            List<ItemStack> items = new ArrayList<>();
            items.add(newitemautolore(Material.ROTTEN_FLESH, "粗制腐肉块", 0.0, null, null,player,0,type));
            items.add(newitemautolore(Material.GOLD_INGOT, "僵尸钢锭", 5.0, 3, "level",player,1,type));
            return items;
        }else if(type.equals("slayer")){
            List<ItemStack> items = new ArrayList<>();
            return items;
        }else {
            List<ItemStack> items = new ArrayList<>();
            items.add(newitem(Material.BEDROCK, "ERROR"));
            return items;
        }
    }

    private static ItemStack newitem(Material material,String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+name);
        item.setItemMeta(meta);
        return item;
    }
    private static ItemStack newitem(Material material,String name,Integer count){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    private static ItemStack newitem(Material material, String name,Integer count, List<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    private static ItemStack newitemautolore(Material material, String name,Double price,@Nullable Integer requirement,@Nullable String requirestat,Player player,Integer id,String type){
        List<String> list = (List<String>) main.getData("recipe","recipe" + type,player);
        if(list == null){
            list = new ArrayList<>();
            Integer length = 0;
            while(true){
                if(length.equals(300)){break;}
                list.add("0");
                length++;
            }
        }
        main.setData("recipe","recipe" + type,player,list);
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET+name);
        List<String> lore = new ArrayList<>();
        if(price.equals(0.0)) {
            lore.add(ChatColor.GRAY + "解锁价格：" + ChatColor.GREEN + "免费！");
        }else{
            lore.add(ChatColor.GRAY + "解锁价格：" + ChatColor.GOLD + price + "G");
        }
        if(requirement != null){
            if(requirestat.equals("level")) {
                lore.add(ChatColor.GRAY + "需求：" + ChatColor.DARK_AQUA + "LV" + requirement);
            } else if (requirestat.equals("rank")) {
                if(requirement.equals(0)){
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.WHITE + "[N/A]");
                } else if (requirement.equals(1)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.GREEN + "[VIP]");
                } else if (requirement.equals(2)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.GREEN + "[VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"]");
                } else if (requirement.equals(3)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.AQUA + "[MVP]");
                } else if (requirement.equals(4)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.AQUA + "[MVP"+ChatColor.WHITE+"+"+ChatColor.AQUA+"]");
                } else if (requirement.equals(5)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.GOLD + "[MVP"+ChatColor.WHITE+"++"+ChatColor.GOLD+"]");
                } else if (requirement.equals(6)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.DARK_AQUA + "["+ChatColor.AQUA+"UP"+ChatColor.DARK_AQUA+"]");
                } else if (requirement.equals(7)) {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.RED + "[Admin]");
                } else {
                    lore.add(ChatColor.GRAY + "需求等阶：" + ChatColor.DARK_RED + "[OWNER]");
                }
            }
        }
        lore.add(" ");
        if(list.get(id).equals("1")) {
            lore.add(ChatColor.RED+"你已经解锁了这个配方！");
        }else {
            if (Double.valueOf(String.valueOf(main.getData("main","purse",player))) < price) {
                lore.add(ChatColor.RED + "你没有足够的硬币！:(");
            } else if (requirement != null) {
                if(requirestat.equals("level")) {
                    if (Double.valueOf(String.valueOf(main.getData("main","purse",player))) >= requirement) {
                        lore.add(ChatColor.YELLOW + "点击解锁！");
                    } else {
                        lore.add(ChatColor.RED + "你的等级不够！");
                    }
                }else {
                    lore.add(ChatColor.RED+"ERROR！");
                }
            } else {
                lore.add(ChatColor.YELLOW + "点击解锁！");
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        //get nbt item and store status using nbt in the item
        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setDouble("price",price);
        nbtItem.setInteger("req",requirement);
        nbtItem.setString("reqType",requirestat);

        nbtItem.setBoolean("isBuyable",true);

        item = nbtItem.getItem();
        return item;
    }
}
