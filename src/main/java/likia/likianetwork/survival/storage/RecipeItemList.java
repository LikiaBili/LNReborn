package likia.likianetwork.survival.storage;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.listerner.CombatSystem;
import likia.likianetwork.survival.main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;

import static likia.likianetwork.survival.api.usefuls.addprice;
import static likia.likianetwork.survival.api.usefuls.addrareity;

public class RecipeItemList{

    private static String itemidsToString(String[] list){
        return "["+list[0]+","+list[1]+","+list[2]+","+list[3]+","+list[4]+","+list[5]+","+list[6]+","+list[7]+","+list[8]+"]";
    }

    public static String[] listRecipes = {itemidsToString(new String[]{
        "","ROTTEN_FLESH","","ROTTEN_FLESH","","ROTTEN_FLESH","","ROTTEN_FLESH",""
}),itemidsToString(new String[]{
        "","CRUDE_FLESH_BLOCK","","","CRUDE_FLESH_BLOCK","","", "STICK",""
}),itemidsToString(new String[]{
        "CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK","","",""
}),itemidsToString(new String[]{
        "CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK"
}),itemidsToString(new String[]{
        "CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK"
}),itemidsToString(new String[]{
        "","","","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK"
}),itemidsToString(new String[]{
        "IRON_INGOT","IRON_INGOT","IRON_INGOT","IRON_INGOT", "FURNACE","IRON_INGOT","IRON_INGOT","IRON_INGOT","IRON_INGOT"
}),itemidsToString(new String[]{
        "","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK","STEEL_INGOT","CRUDE_FLESH_BLOCK","","CRUDE_FLESH_BLOCK",""
}),itemidsToString(new String[]{
        "ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT","","",""
}),itemidsToString(new String[]{
            "ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT"
}),itemidsToString(new String[]{
        "ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT"
}),itemidsToString(new String[]{
        "","","","ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT","ZOMBIE_STEEL_INGOT","","ZOMBIE_STEEL_INGOT"
}),itemidsToString(new String[]{
        "","ZOMBIE_STEEL_INGOT","","","ZOMBIE_STEEL_INGOT","","", "STICK",""
}),itemidsToString(new String[]{
        "WHEAT","WHEAT","WHEAT","WHEAT","WHEAT","WHEAT","WHEAT","WHEAT","WHEAT"
}),itemidsToString(new String[]{
        "","","","COMPRESSED_HAY_BALE","COMPRESSED_HAY_BALE","COMPRESSED_HAY_BALE","","",""
}),itemidsToString(new String[]{
        "CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","ZOMBIE_STEEL_INGOT","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK","CRUDE_FLESH_BLOCK"
}),itemidsToString(new String[]{
        "","ENTITY_ESSENCE","","ENTITY_ESSENCE","","ENTITY_ESSENCE","","ENTITY_ESSENCE",""
}),itemidsToString(new String[]{
        "IRON_BARS","GLASS_PANE","IRON_BARS","IRON_BARS","GLASS_PANE","IRON_BARS","IRON_BARS","IRON_BARS","IRON_BARS"
}),itemidsToString(new String[]{
        "IRON_BARS","IRON_INGOT","IRON_BARS","IRON_INGOT","FURNACE","IRON_INGOT","IRON_BARS","IRON_INGOT","IRON_BARS"
}),itemidsToString(new String[]{
        "","","","GOLD_INGOT","GOLD_INGOT","GOLD_INGOT","","",""
}),itemidsToString(new String[]{
        "STEEL_INGOT","STEEL_INGOT","STEEL_INGOT","","STEEL_INGOT","","","STEEL_INGOT",""
}),itemidsToString(new String[]{
        "STEEL_INGOT","STEEL_INGOT","STEEL_INGOT","STEEL_INGOT","CRAFTING_TABLE","STEEL_INGOT","STEEL_INGOT","STEEL_INGOT","STEEL_INGOT"
}),itemidsToString(new String[]{
        "PACKED_ICE","PACKED_ICE","PACKED_ICE","PACKED_ICE","CHEST","PACKED_ICE","DIAMOND","HAY_BALE","DIAMOND"
}),itemidsToString(new String[]{
        "IRON_INGOT","STEEL_INGOT","IRON_INGOT","STEEL_INGOT","GLASS_PANE","STEEL_INGOT","","STEEL_INGOT",""
})};
    public static final int[] listItems = {
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25
    };

    //1.recipelist
    //2.recipeid (-1 for no requirement,-2 for removed)
    public static final Object[][] listRequirements = {
            {"util",0},{"combat",0},{"armor",0},{"armor",1},{"armor",2},{"armor",3},{"industry",0},{"util",1},{"armor",4},{"armor",5},
            {"armor",6},{"armor",7},{"combat",1},{"farming",0},{"farming",1},{"combat",2},{"util",-2},{"util",-2},{"industry",-2},{"util",-2},
            {"tool",-2},{"industry",-2},{"industry",-2},{"industry",-2}
    };
    public static final Integer[][] listAmount = {
            {1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},
            {1,1,1,1,9,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},{3,3,3,3,3,3,3,3,3},{1,1,1,1,1,1,1,1,1},{1,1,1,1,5,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},{1,1,1,1,3,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1}
    };
    public static final Integer[] xpamount = {
            3,3,5,5,5,5,4,7,8,10,10,8,5,3,2,5,10,5,5,5,5,5,5,5
    };
    public static Object[] getmatchitem(ItemStack[] listProvidedItems,Player player,String method){

        if(method.equals("1")) {

            String[] providedItemsLikiaIDList = {
                    //convent item to LikiaID
                    new NBTItem(listProvidedItems[0]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[1]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[2]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[3]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[4]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[5]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[6]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[7]).getString("LikiaID"),
                    new NBTItem(listProvidedItems[8]).getString("LikiaID")
            };

            Long time = 0L;
            if(main.isDebugOn){
                time = System.currentTimeMillis();
            }
            int position = -1;
            int range = 0;
            String providedItems = itemidsToString(providedItemsLikiaIDList);
            while(true) {
                if(range >= listRecipes.length) {
                    break;
                }

                if(providedItems.equals(listRecipes[range])){
                    position = range;
                    break;
                }

                range++;
            }
            if(main.isDebugOn){
                Long elapsed = System.currentTimeMillis() - time;
                player.sendActionBar("§eDebug§a elapsedRunTime:"+elapsed);
            }


            Object[] list;

            //no found
            if(position < 0){
                if(main.isDebugOn) {
                    System.out.println("err recipenotfound");
                    System.out.println(itemidsToString(providedItemsLikiaIDList)+"\n"+listRecipes[0]+"\n"+position);
                }
                list = new Object[]{newitem(Material.BARRIER, main.getLang(player,"ui_interface_crafting_error_itemnotfound")), -2};
                return list;
            }else {
                //Errors:
                //-1: item not unlocked
                //-2: item not found
                //-3: not enough items
                //-4: item removed
                if (position == -3 && false) {
                    list = new Object[]{newitem(Material.BARRIER, "ERROR 404"), -4};
                    return list;
                }
                System.out.println("position:" + position);

                if ((Integer) CombatSystem.deNull(listRequirements[position][1], -3) == -1
                        || ((List<String>) main.getData("recipe", "recipe"+listRequirements[position][0], player)).get((Integer) listRequirements[position][1]).equals("1")) {
                    list = new Object[]{StaticStorage.items[(Integer) main.getData("main","language",player)][position], position, xpamount[position]};

                    if ((Integer) listRequirements[position][1] == -2) {
                        List<String> lore = new ArrayList<>();
                        lore.add("§fQ&A:");
                        lore.add(main.getLang(player, "ui_interface_crafting_error_itemremoved_Q_1"));
                        lore.add(" ");
                        lore.add(main.getLang(player, "ui_interface_crafting_error_itemremoved_A_1"));
                        lore.add(main.getLang(player, "ui_interface_crafting_error_itemremoved_A_2"));
                        list = new Object[]{newitem(Material.BARRIER, main.getLang(player, "ui_interface_crafting_error_itemremoved")), -4, 0, lore};
                        if(main.isDebugOn) {
                            System.out.println("err itemremoved");
                        }
                        return list;
                    }

                    if (listProvidedItems[0].getAmount() >= listAmount[position][0] &&
                            listProvidedItems[1].getAmount() >= listAmount[position][1] &&
                            listProvidedItems[2].getAmount() >= listAmount[position][2] &&
                            listProvidedItems[3].getAmount() >= listAmount[position][3] &&
                            listProvidedItems[4].getAmount() >= listAmount[position][4] &&
                            listProvidedItems[5].getAmount() >= listAmount[position][5] &&
                            listProvidedItems[6].getAmount() >= listAmount[position][6] &&
                            listProvidedItems[7].getAmount() >= listAmount[position][7] &&
                            listProvidedItems[8].getAmount() >= listAmount[position][8]) {
                        return list;
                    } else {
                        list = new Object[]{newitem(Material.BARRIER, main.getLang(player, "ui_interface_crafting_error_itemnotfound")), -3, 0};
                        if(main.isDebugOn) {
                            System.out.println("err amount");
                        }
                        return list;
                    }
                } else {
                    list = new Object[]{newitem(Material.BARRIER, main.getLang(player, "ui_interface_crafting_error_itemnotfound")), -1, 0};
                    if(main.isDebugOn) {
                        System.out.println("err requirementnotmet");
                    }
                    return list;
                }
            }



        }else if(method.equals("getItems")){
            return listRecipes;
        }else {
            return listAmount;
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
    public static ItemStack newitemautolore2(Material material,Integer rareity,String namelang,String bossid) {
        ItemStack item = new ItemStack(material);
        List<String> lore = new ArrayList<>();
        String bossname = main.getLang(namelang,"entity_mob_boss_@"+bossid+"@");
        lore.add(" ");
        lore.add("§6技能：生成"+bossname+" §e§l右键点击");
        lore.add("§7生成"+bossname+"。");
        lore.add(" ");
        lore.add(usefuls.intToRareityStr(rareity));
        item.setLore(lore);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a"+bossname+"生成器");
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("LikiaID", "BOSS_SPAWNER");
        nbtItem.setInteger("Rareity",rareity);
        nbtItem.setInteger("LikiaIDInteger",15);
        nbtItem.setString("BossID",bossid);
        item = nbtItem.getItem();
        return item;
    }
    public static ItemStack newitemautolore(Material material, Integer rareity, String itemid,String langauge,Integer itemnumid,Double price,Integer damage,Integer str,Integer def,Integer intellgence,Integer HP,Integer count,boolean placeable){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET+main.getLang(langauge,"items_item_name_@"+itemid+"@"));
        List<String> lore = new ArrayList<>();
        if(damage != 0 || str != 0 || intellgence != 0 || def != 0 || HP != 0){
            lore.add(" ");
            if(damage != 0){
                lore.add("§7伤害ᄓ: §c+"+damage);
            }
            if(str != 0){
                lore.add("§7力量ᄖ: §c+"+str);
            }
            if(def != 0){
                lore.add("§7防御ᄙ: §c+"+def);
            }
            if(intellgence != 0){
                lore.add("§7智力ᄗ: §c+"+intellgence);
            }
            if(HP != 0){
                lore.add("§7生命值ᄚ: §c+"+HP);
            }
        }

        lore.add(" ");
        lore.add(usefuls.intToRareityStr(rareity));
        meta.setLore(lore);
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("Placeable",placeable);
        nbtItem.setString("LikiaID",itemid);
        nbtItem.setInteger("LikiaIDInteger",itemnumid);
        nbtItem.setInteger("Rareity",rareity);
        nbtItem.setDouble("Price",price);
        nbtItem.setInteger("Intellgence",intellgence);
        nbtItem.setInteger("Attack",damage);
        nbtItem.setInteger("Strength",str);
        nbtItem.setInteger("Defence",def);
        nbtItem.setInteger("Health",HP);
        nbtItem.setBoolean("Unbreakable",true);
        item = nbtItem.getItem();
        item.setAmount(count);
        return item;
    }
    public static ItemStack newtool(Material material,Integer rareity,Integer amount,Double price,Integer numberlikiaID,String likiaID,String name,Integer woodspeed,Integer damage,Integer minespeed,Integer digspeed){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET+name);
        List<String> lore = new ArrayList<>();
        if(damage != 0){
            lore.add(" ");
            lore.add("§7伤害ᄓ: §c"+damage+"+");
        }
        if(woodspeed != 0 || minespeed != 0 || digspeed != 0){
            lore.add(" ");
            if(minespeed != 0){
                lore.add("§6采矿速度ᄔ§f: "+minespeed);
            }
            if(woodspeed != 0){
                lore.add("§6伐木速度ᄒ§f: "+woodspeed);
            }
            if(digspeed != 0){
                lore.add("§6挖掘速度ᄑ§f: "+digspeed);
            }
        }

        lore.add(" ");
        lore.add(usefuls.intToRareityStr(rareity));
        meta.setLore(lore);
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("LikiaID",likiaID);
        nbtItem.setInteger("LikiaIDInteger",numberlikiaID);
        nbtItem.setInteger("Rareity",rareity);
        nbtItem.setDouble("Price",price);
        nbtItem.setInteger("WoodSpeed",woodspeed);
        nbtItem.setInteger("Attack",damage);
        nbtItem.setInteger("MineSpeed",minespeed);
        nbtItem.setBoolean("Unbreakable",true);
        item = nbtItem.getItem();
        item.setAmount(amount);
        return item;
    }
}
