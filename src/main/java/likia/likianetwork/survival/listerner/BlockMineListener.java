package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTBlock;
import likia.likianetwork.survival.api.usefuls;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.metadata.MetadataValue;

import java.io.File;
import java.util.Collection;
import java.util.Random;

import likia.likianetwork.survival.main;

public class BlockMineListener implements Listener {

    @EventHandler
    public void onBlockMine(BlockBreakEvent event){
        if(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("hub") && !event.getPlayer().isOp()){
            event.setCancelled(true);
            return;
        }
        try {
            new NBTBlock(event.getBlock()).getData().setString("Owner", "");
            if (new NBTBlock(event.getBlock()).getData().getString("Owner").equals(event.getPlayer().getName()) || new NBTBlock(event.getBlock()).getData().getString("Owner").equals("")) {
                Block BLOCK = event.getBlock();
                NBTBlock nbtBlock = new NBTBlock(event.getBlock());
                //if profission of player is miner || farmer & block is naturaly spawned:
                if (!nbtBlock.getData().getBoolean("NotNatural")) {
                    if (main.getData("main", "profession", event.getPlayer()).equals("miner")) {
                        if (BLOCK.getType().equals(Material.STONE)) {
                            //25% chance +1xp
                            Integer random = new Random().nextInt(4);
                            if (random == 1) {
                                usefuls.grantxpforplayer(event.getPlayer(), 1);
                            }
                        } else if (event.getBlock().getType().equals(Material.IRON_ORE)) {
                            usefuls.grantxpforplayer(event.getPlayer(), 5);
                        } else if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
                            usefuls.grantxpforplayer(event.getPlayer(), 20);
                        } else if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
                            usefuls.grantxpforplayer(event.getPlayer(), 50);
                        }
                    }
                } else if (((String) main.getData("main", "profession", event.getPlayer())).equals("farmer")) {
                    if (event.getPlayer().isOp() && main.isDebugOn) {
                        event.getPlayer().sendMessage("§aBlock Material: §e\"" + BLOCK.getType() + "\"");
                    }
                    if (event.getPlayer().isOp() && main.isDebugOn) {
                        event.getPlayer().sendMessage("§aCrop State: §e\"" + ((Crops) BLOCK.getState().getData()).getState() + "\"");
                    }

                    if (BLOCK.getType() == Material.WHEAT && ((Crops) BLOCK.getState().getData()).getState() == CropState.RIPE) {
                        usefuls.grantxpforplayer(event.getPlayer(), 2);
                        Integer random = new Random().nextInt(4);
                        if (random == 2) {
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.WHEAT));
                        }
                    } else if (BLOCK.getType() == Material.POTATO && ((Crops) BLOCK.getState().getData()).getState() == CropState.RIPE) {
                        usefuls.grantxpforplayer(event.getPlayer(), 3);

                        Integer random = new Random().nextInt(8);
                        if (random == 2 || random == 3) {
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.POTATO));
                        } else if (random == 7) {
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.POTATO));
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.POTATO));
                        }
                    } else if (BLOCK.getType() == Material.CARROT && ((Crops) BLOCK.getState().getData()).getState() == CropState.RIPE) {
                        usefuls.grantxpforplayer(event.getPlayer(), 3);

                        Integer random = new Random().nextInt(8);
                        if (random == 2 || random == 3) {
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.CARROT));
                        } else if (random == 7) {
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.CARROT));
                            BLOCK.getLocation().getWorld().dropItem(BLOCK.getLocation(), new ItemStack(Material.CARROT));
                        }
                    }
                }
            } else {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§c该方块已被锁定！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
