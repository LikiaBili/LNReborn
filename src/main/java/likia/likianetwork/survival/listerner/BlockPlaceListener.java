package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.util.Locale;
import java.util.Random;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void event(BlockPlaceEvent event){
        String likiaID = new NBTItem(event.getItemInHand()).getString("LikiaID");
        if(!new NBTItem(event.getItemInHand()).getBoolean("Placeable")){
            if(likiaID.equals("STEEL_FURNACE") || likiaID.equals("MOLDING_TABLE") || likiaID.equals("SMELTER") || likiaID.equals("FRIDGE")){
                new NBTBlock(event.getBlock()).getData().setString("LikiaID",likiaID.toLowerCase(Locale.ROOT));
            }else if(
                    new NBTItem(nulltostructurevoid(event.getItemInHand())).getBoolean("Filled") &&
                            new NBTItem(nulltostructurevoid(event.getItemInHand())).getBoolean("Cold") &&
                            new NBTItem(nulltostructurevoid(event.getItemInHand())).getBoolean("LikiaID").equals("GOLDEN_MOLD")
            ){
                event.setCancelled(true);
                String moldtype = new NBTItem(event.getItemInHand()).getString("MoldType");
                String filltype = new NBTItem(event.getItemInHand()).getString("ContainFluid");
                if(filltype.equalsIgnoreCase("MELTED_ESSENCE")) {
                    if (moldtype.equalsIgnoreCase("sword")) {
                        //26
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_SWORD,1,"ESSENCE_SWORD","精华之剑",26,0.0,0,0,0,0,0,1,false));
                    }else if (moldtype.equalsIgnoreCase("ingot")) {
                        //27
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_INGOT,1,"ESSENCE_INGOT","精华之锭",26,0.0,0,0,0,0,0,1,false));
                    }else if (moldtype.equalsIgnoreCase("helmet")) {
                        //28
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_HELMET,1,"ESSENCE_HELMET","精华头盔",26,0.0,0,0,0,0,0,1,false));
                    }else if (moldtype.equalsIgnoreCase("chestplate")) {
                        //29
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_CHESTPLATE,1,"ESSENCE_CHESTPLATE","精华胸甲",26,0.0,0,0,0,0,0,1,false));
                    }else if (moldtype.equalsIgnoreCase("leggings")) {
                        //30
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_LEGGINGS,1,"ESSENCE_LEGGINGS","精华护腿",26,0.0,0,0,0,0,0,1,false));
                    }else if (moldtype.equalsIgnoreCase("boots")) {
                        //31
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(),
                                RecipeItemList.newitemautolore(Material.IRON_BOOTS,1,"ESSENCE_BOOTS","精华靴子",26,0.0,0,0,0,0,0,1,false));
                    } else {
                        event.getPlayer().sendMessage("§cERROR404: 1x000002");
                    }
                }else {
                    event.getPlayer().sendMessage("§cERROR404: 1x000002");
                }
            }else if(likiaID.equals("BOSS_SPAWNER")) {
                event.setCancelled(true);
                if (event.getBlock().getLocation().getWorld() != Bukkit.getServer().getWorld("hub")) {
                    if (new NBTItem(event.getItemInHand()).getString("BossID").equals("ZOMBIE_KING")) {
                        Entity entity = event.getBlock().getLocation().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.ZOMBIE);
                        //modify entity
                        Location location = entity.getLocation();
                        entity.setCustomNameVisible(true);
                        //random HP
                        Integer hp = 1000;
                        Integer level = (Integer) 50;
                        entity.setCustomName("§7[§8LV" + level + "§7] §c僵尸之王 §a" + hp + "§7/§a" + hp + "§c❤");
                        //save data
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.setMaxHealth(hp);
                        livingEntity.setHealth(hp);
                        //get uuid and set data
                        String uuid = entity.getUniqueId().toString();
                        main.setDataTemp(new String[]{uuid,"Level"},level);
                        main.setDataTemp(new String[]{uuid,"Defence"},100);
                        main.setDataTemp(new String[]{uuid,"Name"},"僵尸之王");
                        main.setDataTemp(new String[]{uuid,"Attack"},250);
                        main.setDataTemp(new String[]{uuid,"Strength"},0);
                        main.setDataTemp(new String[]{uuid,"LikiaID"},"ZOMBIE_KING");
                        main.setDataTemp(new String[]{uuid,"Health"},hp);
                        main.setDataTemp(new String[]{uuid,"MaxHealth"},hp);

                        event.getPlayer().playSound(entity.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 100, 1);
                        ItemStack item = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());
                        item.setAmount(item.getAmount() - 1);
                        event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(), item);
                    }
                } else {
                    event.getPlayer().sendMessage("§c你不能在主城生成Boss!");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.1f);
                }
            }else if(event.getBlock().getType() == Material.PISTON || event.getBlock().getType() == Material.STICKY_PISTON){
                event.setCancelled(true);
                event.getPlayer().sendMessage("§c因为活塞可以用来刷新方块nbt来刷取经验，\n因此活塞已被暂时禁用！（不知道什么时候能修好）");
                event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.ENTITY_VILLAGER_NO,100,0.1f);
            }else if(event.getPlayer().getLocation().getWorld() == Bukkit.getServer().getWorld("world_the_end")){
                if(event.getItemInHand().getType() == Material.RED_MUSHROOM || event.getItemInHand().getType() == Material.BROWN_MUSHROOM) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§chttps://www.bilibili.com/video/BV1854y1H7Hd 指出:\n在末地,蘑菇可被用来崩服,因此蘑菇已被禁用！");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.1f);
                }
            }else {
                if(likiaID.equals("")){
                    if(main.getData("main","profession",event.getPlayer()).equals("builder")){
                        Object[][] blockslist = {
                                {Material.STONE,4},{Material.STONE_BRICKS,4},{Material.COBBLESTONE,4},{Material.ACACIA_PLANKS,4},
                                {Material.BIRCH_PLANKS,4},{Material.CRIMSON_PLANKS,4},{Material.JUNGLE_PLANKS,4},{Material.OAK_PLANKS,4},
                                {Material.DARK_OAK_PLANKS,4},{Material.SPRUCE_PLANKS,4},{Material.WARPED_PLANKS,4}
                        };
                        Integer range = 0;
                        while (true){
                            if(range >= blockslist.length){break;}

                            if(event.getItemInHand().isSimilar(usefuls.addprice(new ItemStack((Material) blockslist[range][0])))){
                                //random
                                Integer random = new Random().nextInt((Integer) blockslist[range][1]);
                                if(random == 0){
                                    event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,1.75f);
                                    Block block = event.getBlock();
                                    event.setCancelled(true);
                                    new NBTBlock(block).getData().setBoolean("NotNatural",true); //for if it did not trigger this event
                                    block.setBlockData(Bukkit.createBlockData((Material) blockslist[range][0]));
                                }
                                break;
                            }

                            range++;
                        }
                    }
                }else {
                    event.setCancelled(true);
                }
            }
        }else {
            if(event.getPlayer() != null) {
                new NBTBlock(event.getBlock()).getData().setBoolean("NotNatural", true);
            }
        }

        //set block nbt
        new NBTBlock(event.getBlockPlaced()).getData().setString("LikiaID",likiaID);
    }

    private ItemStack nulltostructurevoid(ItemStack item){
        if(item == null){return new ItemStack(Material.STRUCTURE_VOID);}else{return item;}
    }
}
