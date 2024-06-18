package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Locale;

public class BlockClickDetector implements Listener {
    @EventHandler
    public void ClickBlock(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()) != null){
                if(new NBTItem(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot())).getString("LikiaID").equals("BLOCK_LOCKER")){
                    //if owner is null then own block
                    NBTBlock block = new NBTBlock(event.getClickedBlock());
                    if(block.getData().getString("Owner") == ""){
                        if((Double) main.getData("main", "purse", event.getPlayer()) >= 0.5){
                            DecimalFormat df = new DecimalFormat("#.00"); // format
                            main.setData("main","purse",event.getPlayer(),Double.valueOf(df.format((Double) main.getData("main","purse",event.getPlayer())-0.5)));
                            block.getData().setString("Owner",event.getPlayer().getName());
                            event.getPlayer().sendMessage("§a已锁定！");
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,0.5f);
                        }
                    }else {
                        event.getPlayer().sendMessage("§c该方块已被锁定！");
                        Location newlocation = event.getClickedBlock().getLocation();
                        newlocation.setY(event.getClickedBlock().getLocation().getY() + 1);
                        event.getClickedBlock().getLocation().getWorld().spawnParticle(Particle.REDSTONE,newlocation,100);
                    }
                }else{
                    openUI(event);
                }
            }else {
                openUI(event);
            }
        } else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot()) != null) {
                if (new NBTItem(event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot())).getString("LikiaID").equals("BLOCK_LOCKER")) {
                    //if block owner isnt null
                    NBTBlock block = new NBTBlock(event.getClickedBlock());
                    if(block.getData().getString("Owner") != ""){
                        if(block.getData().getString("Owner") == event.getPlayer().getName()){
                            //give +0.4G and remove ownership
                            block.getData().setString("Owner","");
                            DecimalFormat df = new DecimalFormat("#.00"); // format
                            main.setData("main","purse",event.getPlayer(),Double.valueOf(df.format((Double) main.getData("main","purse",event.getPlayer())+0.4)));
                            event.getPlayer().sendMessage("§a已解锁！");
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,0.5f);
                        }else {
                            event.getPlayer().sendMessage("§c这不是你的方块！");
                        }
                    }else {
                        event.getPlayer().sendMessage("§c锁定方块请使用右键！");
                    }
                }
            }
        }
    }

    private void openUI(PlayerInteractEvent event){
        if(new NBTBlock(event.getClickedBlock()).getData().getString("Owner").equals(event.getPlayer().getName()) || new NBTBlock(event.getClickedBlock()).getData().getString("Owner").equals("")) {
            String likiaID = new NBTBlock(event.getClickedBlock()).getData().getString("LikiaID");
            if(likiaID.equals("STEEL_FURNACE") || likiaID.equals("FRIDGE") || likiaID.equals("MOLDING_TABLE") || likiaID.equals("FILLER")
                    || likiaID.equals("SMELTER")){
                event.setCancelled(true);
                event.getPlayer().chat("/ui "+likiaID.toLowerCase(Locale.ROOT));
            }else if (event.getClickedBlock().getType().equals(Material.ANVIL) || event.getClickedBlock().getType().equals(Material.CHIPPED_ANVIL) || event.getClickedBlock().getType().equals(Material.DAMAGED_ANVIL) || event.getClickedBlock().getType().equals(Material.LEGACY_ANVIL)) {
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
            event.getPlayer().sendMessage("§c该方块已被锁定！");
        }
    }
}
