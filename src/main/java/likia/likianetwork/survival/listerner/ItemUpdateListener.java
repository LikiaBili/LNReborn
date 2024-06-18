package likia.likianetwork.survival.listerner;

import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemUpdateListener implements Listener {
    @EventHandler
    public void onUpdate(InventoryMoveItemEvent event){
        ItemStack item = event.getItem();
        ItemStack newitem = usefuls.addprice(item);
        if(newitem.isSimilar(item)){
            //try to update item
            Object[][] list = {
                    {
                        usefuls.addprice(new ItemStack(Material.WOODEN_SWORD)), RecipeItemList.newitemautolore(Material.WOODEN_SWORD,1,"","木剑",-2,0.0,4,0,0,0,0,1,false)
                    },{
                    usefuls.addprice(new ItemStack(Material.STONE_SWORD)), RecipeItemList.newitemautolore(Material.STONE_SWORD,1,"","石剑",-2,0.0,6,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.IRON_SWORD)), RecipeItemList.newitemautolore(Material.IRON_SWORD,1,"","铁剑",-2,0.0,8,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.DIAMOND_SWORD)), RecipeItemList.newitemautolore(Material.DIAMOND_SWORD,1,"","钻石剑",-2,0.0,12,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.NETHERITE_SWORD)), RecipeItemList.newitemautolore(Material.NETHERITE_SWORD,1,"","下界合金剑",-2,0.0,16,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.STONE_AXE)), RecipeItemList.newitemautolore(Material.STONE_AXE,1,"","石斧",-2,0.0,8,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.IRON_AXE)), RecipeItemList.newitemautolore(Material.IRON_AXE,1,"","铁斧",-2,0.0,14,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.DIAMOND_AXE)), RecipeItemList.newitemautolore(Material.DIAMOND_AXE,1,"","钻石斧",-2,0.0,20,0,0,0,0,1,false)
            },{
                    usefuls.addprice(new ItemStack(Material.NETHERITE_AXE)), RecipeItemList.newitemautolore(Material.NETHERITE_AXE,1,"","下界合金斧",-2,0.0,28,0,0,0,0,1,false)
            }};
            Integer range = 0;
            while (true){
                if(list.length >= range){break;}

                if(newitem.isSimilar((ItemStack) list[range][0])){
                    newitem = (ItemStack) list[range][1];
                    break;
                }

                range++;
            }
        }
        event.setItem(newitem);
    }
}
