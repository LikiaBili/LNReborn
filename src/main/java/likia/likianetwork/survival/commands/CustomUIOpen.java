package likia.likianetwork.survival.commands;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.main;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import likia.likianetwork.survival.api.usefuls;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomUIOpen implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        // if args have length 0 then cast eror message
        if(args.length == 0){
            sender.sendMessage("ERROR 0x000003");
        }else {
            //get what ui that player want to open
            if(args[0].toLowerCase(Locale.ROOT).equals("anvil")){
                Inventory ui = Bukkit.createInventory(null,6*9,"铁砧");
                player.openInventory(ui);
            }else if(args[0].toLowerCase(Locale.ROOT).equals("etable")){
                sender.sendMessage("ERROR 1x000001");
            }else if(args[0].equalsIgnoreCase("steel_furnace")){
                //preset
                Inventory craftingUI = usefuls.bgmenu("钢炉");
                craftingUI = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",craftingUI);
                ItemStack[] contents = craftingUI.getContents();

                //set ui
                contents[49] = usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭");
                contents[13] = null;
                NBTItem nbtitem = new NBTItem(usefuls.newitem(Material.ANVIL,ChatColor.YELLOW+"锻造"));
                nbtitem.setString("LikiaID","MENUITEM");
                contents[31] = nbtitem.getItem();

                //final
                craftingUI.setContents(contents);
                Inventory finalCraftingUI = craftingUI;
                player.openInventory(finalCraftingUI);

                //runable
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(player.getItemOnCursor() !=null) {
                            if (new NBTItem(player.getItemOnCursor()).getString("LikiaID").equalsIgnoreCase("MENUITEM")) {
                                player.setItemOnCursor(null);
                                NBTItem nbtitem = new NBTItem(usefuls.newitem(Material.ANVIL, ChatColor.YELLOW + "锻造"));
                                nbtitem.setString("LikiaID", "MENUITEM");
                                finalCraftingUI.setItem(31, nbtitem.getItem());
                                if (finalCraftingUI.getItem(13).isSimilar(usefuls.addrareity(usefuls.addprice(new ItemStack(Material.IRON_INGOT))))) {
                                    finalCraftingUI.setItem(13, RecipeItemList.newitemautolore(Material.IRON_INGOT,1,"STEEL_INGOT","钢锭",15,5.0,0,0,0,0,0,finalCraftingUI.getItem(13).getAmount(),false));
                                }
                            }
                        }
                        if(!player.getOpenInventory().getTitle().equals("钢炉")){
                            this.cancel();
                        }
                    }
                }.runTaskTimer(main.getPlug(),0,2);

            }else if(args[0].equalsIgnoreCase("smelter")){
                //pre
                Inventory ui = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",usefuls.bgmenu("熔化炉"));
                ItemStack black = usefuls.newitem(Material.BLACK_STAINED_GLASS_PANE," ");

                ui.setItem(13,black);
                ui.setItem(22,black);
                ui.setItem(31,black);
                ui.setItem(32,black);
                ui.setItem(14,black);
                ui.setItem(21,black);
                ui.setItem(33,null);
                ui.setItem(15,null);
                ui.setItem(20,null);
                ui.setItem(49,usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭"));

                player.openInventory(ui);
                //runable detector
                ItemStack coal = usefuls.addprice(new ItemStack(Material.COAL));
                ItemStack container = RecipeItemList.newitemautolore(Material.GLASS,1,"GLASS_CONTAINER","玻璃容器",18,0.0,0,0,0,0,0,1,false);
                ItemStack essenceCube = RecipeItemList.newitemautolore(Material.LIME_CONCRETE,1,"ESSENCE_CUBE","精华立方",17,0.0,0,0,0,0,0,1,false);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        System.out.println("run");
                        if(!player.getOpenInventory().getTitle().equals("熔化炉")){this.cancel();}
                        System.out.println(coal);
                        System.out.println(ui.getItem(20));
                        if(nulltostructurevoid(ui.getItem(20)).isSimilar(coal)){
                            System.out.println("coal accepcted");
                            if(new NBTItem(nulltostructurevoid(ui.getItem(33))).getString("LikiaID").equals("GLASS_CONTAINER")){
                                System.out.println("container accepcted");
                                if(nulltostructurevoid(ui.getItem(15)).isSimilar(essenceCube) && ui.getItem(33).getAmount() == 1){
                                    System.out.println("material accepcted");
                                    if(new NBTItem(nulltostructurevoid(ui.getItem(33))).getString("ContainFluid").equals("")) {
                                        System.out.println("fluid accepcted");
                                        ItemStack providedCoal = ui.getItem(20);
                                        providedCoal.setAmount(providedCoal.getAmount() - 1);
                                        ui.setItem(20, providedCoal);
                                        ItemStack providedEss = ui.getItem(15);
                                        providedEss.setAmount(providedEss.getAmount() - 1);
                                        ui.setItem(15, providedEss);
                                        NBTItem nbtContainer = new NBTItem(ui.getItem(33));
                                        nbtContainer.setString("ContainFluid", "MELTED_ESSENCE");
                                        ItemStack containerfilled = nbtContainer.getItem();
                                        containerfilled.setType(Material.GREEN_STAINED_GLASS);
                                        ItemMeta containermeta = containerfilled.getItemMeta();
                                        containermeta.setDisplayName("装满融化的实体精华的玻璃容器");
                                        containerfilled.setItemMeta(containermeta);
                                        ui.setItem(33, containerfilled);

                                    }
                                }
                            }
                        }
                    }
                }.runTaskTimer(main.getPlug(),0,2);
            } else if(args[0].equals("molding_table")){
                //preset
                Inventory craftingUI = usefuls.bgmenu("膜具雕刻台");
                craftingUI = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",craftingUI);
                ItemStack[] contents = craftingUI.getContents();

                //set ui
                contents[49] = usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭");
                contents[11] = null;
                contents[29] = null;
                contents[13] = newitem1(Material.IRON_SWORD,"剑","sword",13);
                contents[14] = newitem1(Material.IRON_PICKAXE,"镐","pickaxe",14);
                contents[15] = newitem1(Material.IRON_AXE,"斧","axe",15);
                contents[22] = newitem1(Material.IRON_INGOT,"锭","ingot",22);
                contents[23] = newitem1(Material.IRON_HELMET,"头盔","helmet",23);
                contents[24] = newitem1(Material.IRON_CHESTPLATE,"胸甲","chestplate",24);
                contents[31] = newitem1(Material.IRON_LEGGINGS,"护腿","legging",31);
                contents[32] = newitem1(Material.IRON_BOOTS,"靴子","boot",32);
                contents[33] = newitem1(Material.IRON_SHOVEL,"铲子","shovel",33);

                //finalize
                craftingUI.setContents(contents);
                Inventory finalCraftingUI = craftingUI;
                player.openInventory(finalCraftingUI);

                //runable
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(!player.getOpenInventory().getTitle().equals("膜具雕刻台")){this.cancel();}

                        try {
                            //get item on cursor
                            NBTItem cursorNbt = new NBTItem(nulltostructurevoid(player.getItemOnCursor()));

                            //get if item is for menu
                            if (new NBTItem(nulltostructurevoid(player.getItemOnCursor())).getString("LikiaID").equals("MENUITEM")) {
                                ItemStack cursorItem = player.getItemOnCursor();
                                finalCraftingUI.setItem(new NBTItem(cursorItem).getInteger("Slot"), cursorItem);
                                if (!new NBTItem(nulltostructurevoid(finalCraftingUI.getItem(29))).getString("LikiaID").equals("STEEL_MOLDER")) {
                                    player.setItemOnCursor(null);
                                    player.closeInventory();
                                    player.sendMessage("§c你没有刻刀！");
                                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.5f);
                                } else {
                                    if (!new NBTItem(nulltostructurevoid(finalCraftingUI.getItem(11))).getString("LikiaID").equals("GOLDEN_MOLD")) {
                                        player.setItemOnCursor(null);
                                        player.closeInventory();
                                        player.sendMessage("§c你没有膜具！");
                                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.5f);
                                    } else {
                                        if (!new NBTItem(nulltostructurevoid(finalCraftingUI.getItem(11))).getString("MoldType").equals("")) {
                                            player.setItemOnCursor(null);
                                            player.closeInventory();
                                            player.sendMessage("§c你没有你使用的膜具已经被雕刻过了！");
                                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.5f);
                                        } else {
                                            player.setItemOnCursor(null);
                                            ItemStack mold = finalCraftingUI.getItem(11);
                                            List<String> lore = new ArrayList<>();
                                            lore.add(" ");
                                            lore.add("§e雕刻膜具: §a" + cursorNbt.getString("edittypename"));
                                            lore.add(" ");
                                            lore.add("§f§l普通");
                                            ItemMeta moldmeta = mold.getItemMeta();
                                            moldmeta.setDisplayName("§r§f"+cursorNbt.getString("edittypename") + "金制膜具");
                                            moldmeta.setLore(lore);
                                            mold.setItemMeta(moldmeta);
                                            NBTItem moldnbt = new NBTItem(mold);
                                            moldnbt.setString("MoldType", cursorNbt.getString("edittype"));
                                            mold = moldnbt.getItem();
                                            finalCraftingUI.setItem(11, mold);
                                            finalCraftingUI.setItem(29, null);
                                        }
                                    }
                                }
                            }
                        }catch(Exception exception){}
                    }
                }.runTaskTimer(main.getPlug(),0,2);
            }else if(args[0].toLowerCase(Locale.ROOT).equals("mold_filler")){
                //preset
                Inventory craftingUI = usefuls.bgmenu("注入机");
                craftingUI = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",craftingUI);
                ItemStack[] contents = craftingUI.getContents();

                //ui
                contents[49] = usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭");
                contents[13] = null;
                contents[22] = usefuls.newitem(Material.BLACK_STAINED_GLASS_PANE," ");
                contents[31] = null;

                //final
                craftingUI.setContents(contents);
                Inventory finalcraftingUI = craftingUI;
                player.openInventory(finalcraftingUI);

                //detect item runable
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(!player.getOpenInventory().getTitle().equals("注入机")){this.cancel();}

                        NBTItem nbtContainer = new NBTItem(finalcraftingUI.getItem(13));
                        NBTItem nbtMold = new NBTItem(finalcraftingUI.getItem(31));
                        if(finalcraftingUI.getItem(31).getAmount() == 1 && !nbtMold.getBoolean("Filled")
                        && nbtContainer.getString("LikiaID").equals("GLASS_CONTAINER") && nbtMold.getString("LikiaID").equals("GOLDEN_MOLD")
                        && nbtContainer.getString("ContainFluid").equals("MELTED_ESSENCE") && nbtMold.getString("Contain").equals("")){
                            finalcraftingUI.setItem(13,RecipeItemList.newitemautolore(Material.GLASS,1,"GLASS_CONTAINER","玻璃容器",18,0.0,0,0,0,0,0,1,false));
                            nbtMold.setString("Contain","MELTED_ESSENCE");
                            nbtMold.setBoolean("Filled",true);
                            ItemStack newmold = nbtMold.getItem();
                            ItemMeta moldMeta = newmold.getItemMeta();
                            moldMeta.setDisplayName("§r§f装满实体精华的" +moldMeta.getDisplayName());
                            newmold.setItemMeta(moldMeta);
                            finalcraftingUI.setItem(31,newmold);
                        }
                    }
                }.runTaskTimer(main.getPlug(),0,10);
            }else if(args[0].toLowerCase(Locale.ROOT).equals("fridge")){
                //preset
                Inventory craftingUI = usefuls.bgmenu("冰箱");
                craftingUI = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",craftingUI);
                ItemStack[] contents = craftingUI.getContents();

                //ui
                contents[49] = usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭");
                contents[22] = null;

                //final
                craftingUI.setContents(contents);
                Inventory finalcraftingUI = craftingUI;
                player.openInventory(finalcraftingUI);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(!player.getOpenInventory().getTitle().equals("冰箱")){this.cancel();}

                        if(new NBTItem(nulltostructurevoid(finalcraftingUI.getItem(22))).getString("LikiaID").equals("GOLDEN_MOLD")
                        && new NBTItem(nulltostructurevoid(finalcraftingUI.getItem(22))).getBoolean("Filled")
                        && !new NBTItem(nulltostructurevoid(finalcraftingUI.getItem(22))).getBoolean("Cold")
                        && new NBTItem(nulltostructurevoid(finalcraftingUI.getItem(22))).getString("Contain").equals("")){
                            NBTItem nbtItem = new NBTItem(finalcraftingUI.getItem(22));
                            nbtItem.setBoolean("Cold",true);
                            ItemStack itemStack = nbtItem.getItem();
                            ItemMeta meta = itemStack.getItemMeta();
                            meta.setDisplayName("冷冻的"+meta.getDisplayName());
                            List<String> lore = new ArrayList<>();
                            lore.add(" ");
                            lore.add("§6技能: 脱膜 §e§l右键点击");
                            lore.add(" ");
                            lore.add("§f§l普通");
                            meta.setLore(lore);
                            itemStack.setItemMeta(meta);
                            finalcraftingUI.setItem(22,itemStack);
                        }
                    }
                }.runTaskTimer(main.getPlug(),0,10);
            } else if (args[0].toLowerCase(Locale.ROOT).equals("crafting")) {
                //preset
                Inventory craftingUI = usefuls.bgmenu("合成台");
                craftingUI = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",craftingUI);
                ItemStack[] contents = craftingUI.getContents();

                //set ui
                contents[49] = usefuls.newitem(Material.BARRIER,ChatColor.RED+"关闭");
                contents[24] = usefuls.newitem(Material.BARRIER,"无匹配配方！");
                contents[10] = new ItemStack(Material.AIR);
                contents[11] = new ItemStack(Material.AIR);
                contents[12] = new ItemStack(Material.AIR);
                contents[19] = new ItemStack(Material.AIR);
                contents[20] = new ItemStack(Material.AIR);
                contents[21] = new ItemStack(Material.AIR);
                contents[28] = new ItemStack(Material.AIR);
                contents[29] = new ItemStack(Material.AIR);
                contents[30] = new ItemStack(Material.AIR);

                //finalize
                craftingUI.setContents(contents);
                Inventory finalCraftingUI = craftingUI;
                player.openInventory(finalCraftingUI);
                //create (r)unable
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        //avoid bugs
                        Inventory craftingUI1 = finalCraftingUI;
                        craftingUI1 = removeitemscrafted(craftingUI1);
                        ItemStack[] contents2 = craftingUI1.getContents();
                        ItemStack[] craftingItemsList = {contents2[10], contents2[11], contents2[12], contents2[19], contents2[20], contents2[21], contents2[28], contents2[29], contents2[30]};

                        //avoid nulls
                        craftingItemsList[0] = nulltostructurevoid(craftingItemsList[0]);
                        craftingItemsList[1] = nulltostructurevoid(craftingItemsList[1]);
                        craftingItemsList[2] = nulltostructurevoid(craftingItemsList[2]);
                        craftingItemsList[3] = nulltostructurevoid(craftingItemsList[3]);
                        craftingItemsList[4] = nulltostructurevoid(craftingItemsList[4]);
                        craftingItemsList[5] = nulltostructurevoid(craftingItemsList[5]);
                        craftingItemsList[6] = nulltostructurevoid(craftingItemsList[6]);
                        craftingItemsList[7] = nulltostructurevoid(craftingItemsList[7]);
                        craftingItemsList[8] = nulltostructurevoid(craftingItemsList[8]);

                        Object[] listobjectitems = RecipeItemList.getmatchitem(craftingItemsList,player,"1");

                        //if player took item then remove the item use to craft
                        if(finalCraftingUI.getItem(24) == null){
                            Integer[][] needitems = (Integer[][]) RecipeItemList.getmatchitem(craftingItemsList,player,"2");
                            Integer range = (Integer) listobjectitems[1];

                            craftingItemsList[0].setAmount(craftingItemsList[0].getAmount() - needitems[range][0]);
                            contents[10] = voidtoair(craftingItemsList[0]);
                            craftingItemsList[1].setAmount(craftingItemsList[1].getAmount() - needitems[range][1]);
                            contents[11] = voidtoair(craftingItemsList[1]);
                            craftingItemsList[2].setAmount(craftingItemsList[2].getAmount() - needitems[range][2]);
                            contents[12] = voidtoair(craftingItemsList[2]);
                            craftingItemsList[3].setAmount(craftingItemsList[3].getAmount() - needitems[range][3]);
                            contents[19] = voidtoair(craftingItemsList[3]);
                            craftingItemsList[4].setAmount(craftingItemsList[4].getAmount() - needitems[range][4]);
                            contents[20] = voidtoair(craftingItemsList[4]);
                            craftingItemsList[5].setAmount(craftingItemsList[5].getAmount() - needitems[range][5]);
                            contents[21] = voidtoair(craftingItemsList[5]);
                            craftingItemsList[6].setAmount(craftingItemsList[6].getAmount() - needitems[range][6]);
                            contents[28] = voidtoair(craftingItemsList[6]);
                            craftingItemsList[7].setAmount(craftingItemsList[7].getAmount() - needitems[range][7]);
                            contents[29] = voidtoair(craftingItemsList[7]);
                            craftingItemsList[8].setAmount(craftingItemsList[8].getAmount() - needitems[range][8]);
                            contents[30] = voidtoair(craftingItemsList[8]);
                            finalCraftingUI.setContents(contents);
                            //add xp
                            if(main.getData("main","profession",player).equals("carptender")) {
                                usefuls.grantxpforplayer(player, (Integer) listobjectitems[2]);
                            }
                        }

                        //set results
                        craftingUI1.setItem(24, (ItemStack) listobjectitems[0]);

                        //endtask if player closed the inventory
                        if(((Player) sender).getOpenInventory().getTitle() != "合成台"){
                            this.cancel();
                        }
                    }
                }.runTaskTimer(main.getPlug(),0,2);
            }else {
                sender.sendMessage("ERROR 0x000001");
            }
        }
        return false;
    }
    private ItemStack newitem1(Material material,String type,String typeid,Integer slot){
        ItemStack item = new ItemStack(material);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§7雕刻为"+type+"样式");
        lore.add(" ");
        lore.add("§e点击雕刻！");
        item.setLore(lore);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a"+type);
        item.setItemMeta(meta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("edittype",typeid);
        nbtItem.setString("edittypename",type);
        nbtItem.setString("LikiaID","MENUITEM");
        nbtItem.setInteger("Slot",slot);
        item = nbtItem.getItem();
        return item;
    }

    private Inventory removeitemscrafted(Inventory ui){
        ItemStack[] contents = ui.getContents();
        //if result slot is empty, remove items crafting used to craft
        if(contents[24] == null){
            //get item counts and remove items
            return ui;
        }else {
            return ui;
        }
    }
    private ItemStack nulltostructurevoid(@Nullable ItemStack item){
        if(item == null || item.isSimilar(new ItemStack(Material.AIR))){
            return new ItemStack(Material.STRUCTURE_VOID);
        }else{
            return item;
        }
    }
    private ItemStack voidtoair(ItemStack item){
        if(item.isSimilar(new ItemStack(Material.STRUCTURE_VOID))){
            return null;
        }else {
            return item;
        }
    }
}
