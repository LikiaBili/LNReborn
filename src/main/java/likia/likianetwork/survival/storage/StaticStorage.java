package likia.likianetwork.survival.storage;

import likia.likianetwork.survival.main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static likia.likianetwork.survival.storage.RecipeItemList.newitemautolore;
import static likia.likianetwork.survival.storage.RecipeItemList.newitemautolore2;

public class StaticStorage {
    public static Object[][][] avalableDailyQuests = {{
        //easy
            {"gain5xp","gold",2.5d} //{questname,rewardtype,rewardamount}
    },{
        //med
            {"kill10zombie","xp",5}
    },{
        //hard
            {"craft10items","item","RED_CORE"}
    }};
    public static String[] dailyQuests = {"A","B","C"};

    public static ItemStack[][] items = {
            {
                    //en_us
                    newitemautolore(Material.ROTTEN_FLESH,1,"CRUDE_FLESH_BLOCK","en_us",0,1.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.WOODEN_SWORD,1,"CRUDE_FLESH_SWORD","en_us",1,2.5,30,0,0,0,0,1,false),
                    newitemautolore(Material.LEATHER_HELMET,1,"CRUDE_FLESH_HELMET","en_us",2,7.0,0,0,10,5,10,1,false),
                    newitemautolore(Material.LEATHER_CHESTPLATE,1,"CRUDE_FLESH_CHESTPLATE","en_us",3,10.0,0,0,16,8,3,1,false),
                    newitemautolore(Material.LEATHER_LEGGINGS,1,"CRUDE_FLESH_LEGGINGS","en_us",4,9.0,0,0,14,7,3,1,false),
                    newitemautolore(Material.LEATHER_BOOTS,1,"CRUDE_FLESH_BOOTS","en_us",5,6.0,0,0,8,5,2,1,false),
                    newitemautolore(Material.BLAST_FURNACE,1,"STEEL_FURNACE","en_us",6,10.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.GOLD_INGOT,1,"ZOMBIE_STEEL_INGOT","en_us",7,30.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.GOLDEN_HELMET,1,"ZOMBIE_STEEL_HELMET","en_us",8,150.0,0,0,20,10,10,1,false),
                    newitemautolore(Material.GOLDEN_CHESTPLATE,1,"ZOMBIE_STEEL_CHESTPLATE","en_us",9,240.0,0,1,32,10,16,1,false),
                    newitemautolore(Material.GOLDEN_LEGGINGS,1,"ZOMBIE_STEEL_LEGGINGS","en_us",10,210.0,0,28,0,10,14,1,false),
                    newitemautolore(Material.GOLDEN_BOOTS,1,"ZOMBIE_STEEL_BOOTS","en_us",11,120.0,0,0,18,10,8,1,false),
                    newitemautolore(Material.GOLDEN_SWORD,1,"ZOMBIE_SWORD","en_us",12,60.0,14,0,0,0,0,1,false),
                    newitemautolore(Material.HAY_BLOCK,1,"COMPRESSED_HAY_BALE","en_us",13,10.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.BREAD,1,"COMPRESSED_BREAD","en_us",14,10.0,0,0,0,0,0,3,false),
                    newitemautolore2(Material.BEACON,1,"en_us","ZOMBIE_KING"),
                    newitemautolore(Material.LIME_CONCRETE,1,"ESSENCE_CUBE","en_us",17,0.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.GLASS,1,"GLASS_CONTAINER","en_us",18,0.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.FURNACE,1,"SMELTER","en_us",19,25.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.YELLOW_STAINED_GLASS_PANE,1,"GOLDEN_MOLD","en_us",21,0.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.IRON_SWORD,1,"STEEL_MOLDER","en_us",22,0.0,0,0,0,0,0,1,false),
                    newitemautolore(Material.CRAFTING_TABLE,1,"MOLDING_TABLE","en_us",23,0.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.PACKED_ICE,1,"FRIDGE","en_us",24,0.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.ANVIL,1,"FILLER","en_us",25,0.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.RED_DYE,1,"RED_CORE","en_us",26,0.0,0,0,0,0,0,1,false)
            },
            {
                //zh_cn
            newitemautolore(Material.ROTTEN_FLESH,1,"CRUDE_FLESH_BLOCK","zh_cn",0,1.0,0,0,0,0,0,1,false),
            newitemautolore(Material.WOODEN_SWORD,1,"CRUDE_FLESH_SWORD","zh_cn",1,2.5,30,0,0,0,0,1,false),
            newitemautolore(Material.LEATHER_HELMET,1,"CRUDE_FLESH_HELMET","zh_cn",2,7.0,0,0,10,5,10,1,false),
            newitemautolore(Material.LEATHER_CHESTPLATE,1,"CRUDE_FLESH_CHESTPLATE","zh_cn",3,10.0,0,0,16,8,3,1,false),
            newitemautolore(Material.LEATHER_LEGGINGS,1,"CRUDE_FLESH_LEGGINGS","zh_cn",4,9.0,0,0,14,7,3,1,false),
            newitemautolore(Material.LEATHER_BOOTS,1,"CRUDE_FLESH_BOOTS","zh_cn",5,6.0,0,0,8,5,2,1,false),
            newitemautolore(Material.BLAST_FURNACE,1,"STEEL_FURNACE","zh_cn",6,10.0,0,0,0,0,0,1,true),
            newitemautolore(Material.GOLD_INGOT,1,"ZOMBIE_STEEL_INGOT","zh_cn",7,30.0,0,0,0,0,0,1,false),
            newitemautolore(Material.GOLDEN_HELMET,1,"ZOMBIE_STEEL_HELMET","zh_cn",8,150.0,0,0,20,10,10,1,false),
            newitemautolore(Material.GOLDEN_CHESTPLATE,1,"ZOMBIE_STEEL_CHESTPLATE","zh_cn",9,240.0,0,1,32,10,16,1,false),
            newitemautolore(Material.GOLDEN_LEGGINGS,1,"ZOMBIE_STEEL_LEGGINGS","zh_cn",10,210.0,0,28,0,10,14,1,false),
            newitemautolore(Material.GOLDEN_BOOTS,1,"ZOMBIE_STEEL_BOOTS","zh_cn",11,120.0,0,0,18,10,8,1,false),
            newitemautolore(Material.GOLDEN_SWORD,1,"ZOMBIE_SWORD","zh_cn",12,60.0,14,0,0,0,0,1,false),
            newitemautolore(Material.HAY_BLOCK,1,"COMPRESSED_HAY_BALE","zh_cn",13,10.0,0,0,0,0,0,1,false),
            newitemautolore(Material.BREAD,1,"COMPRESSED_BREAD","zh_cn",14,10.0,0,0,0,0,0,3,false),
            newitemautolore2(Material.BEACON,1,"zh_cn","ZOMBIE_KING"),
            newitemautolore(Material.LIME_CONCRETE,1,"ESSENCE_CUBE","zh_cn",17,0.0,0,0,0,0,0,1,false),
            newitemautolore(Material.GLASS,1,"GLASS_CONTAINER","zh_cn",18,0.0,0,0,0,0,0,1,false),
            newitemautolore(Material.FURNACE,1,"SMELTER","zh_cn",19,25.0,0,0,0,0,0,1,true),
            newitemautolore(Material.YELLOW_STAINED_GLASS_PANE,1,"GOLDEN_MOLD","zh_cn",21,0.0,0,0,0,0,0,1,false),
            newitemautolore(Material.IRON_SWORD,1,"STEEL_MOLDER","zh_cn",22,0.0,0,0,0,0,0,1,false),
            newitemautolore(Material.CRAFTING_TABLE,1,"MOLDING_TABLE","zh_cn",23,0.0,0,0,0,0,0,1,true),
            newitemautolore(Material.PACKED_ICE,1,"FRIDGE","zh_cn",24,0.0,0,0,0,0,0,1,true),
            newitemautolore(Material.ANVIL,1,"FILLER","zh_cn",25,0.0,0,0,0,0,0,1,true),
                    newitemautolore(Material.RED_DYE,1,"RED_CORE","zh_cn",26,0.0,0,0,0,0,0,1,false)
            }};
}