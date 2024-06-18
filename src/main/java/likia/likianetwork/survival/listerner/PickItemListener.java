package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PickItemListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        NBTBlock nbtBlock = new NBTBlock(event.getBlock());
        if(nbtBlock.getData().getString("LikiaID").equals("")){
            Object[] droplist = event.getBlock().getDrops().toArray();
            Integer range = 0;
            while (true) {
                if(range == droplist.length){break;}
                droplist[range] = usefuls.addprice((ItemStack) droplist[range]);
                //debug WIP gone to sleep

                //price 0 to list
                //item = nbtItem.getItem();
                //item = usefuls.addprice(item);
                //likiaid test
                //if(!new NBTBlock(event.getBlock()).getData().getString("LikiaID").equals("")){
                    //if(new NBTBlock(event.getBlock()).getData().getString("LikiaID").equals("STEEL_FURNACE")){
                        //item = RecipeItemList.newitemautolore(Material.BLAST_FURNACE,1,"STEEL_FURNACE","钢炉",0,10.0,0,0,0,0,0,1,true);
                    //}
                    //event.getBlock().setBlockData(Bukkit.createBlockData(Material.AIR));
                    //event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), item);
                    //break;
                }
                event.getBlock().setBlockData(Bukkit.createBlockData(Material.AIR));
                //event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), item);
                range++;
            }
            event.getBlock().setBlockData(Bukkit.createBlockData(Material.AIR));
            event.setCancelled(true);
        //}else{
            //String likiaID = nbtBlock.getData().getString("LikiaID");
            //event.setCancelled(true);
            //event.getBlock().setBlockData(Bukkit.createBlockData(Material.AIR));
        //}
    }

    @EventHandler
    public void onPick(PlayerPickupItemEvent event){
        //get item and add rareity
        event.getItem().setItemStack(usefuls.addprice(event.getItem().getItemStack()));
    }
}
