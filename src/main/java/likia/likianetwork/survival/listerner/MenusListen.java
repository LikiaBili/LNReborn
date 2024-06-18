package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import likia.likianetwork.survival.api.usefuls;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenusListen implements Listener {

    static String[][] menunames = {
            {main.getLang("en_us","ui_menu_label_title_link"),main.getLang("zh_cn","ui_menu_label_title_link")}
    };

    int find(String name){
        int range = 0;
        while (true){
            if(range >= menunames.length){break;}

            if(new ArrayList<>(Arrays.asList(menunames[range])).contains(name)){
                return range;
            }

            range++;
        }
        return -1;
    }

    @EventHandler
    public void exitmenus(InventoryCloseEvent event){
        //get if the menu closed is listed in the menus of to run codes
        if(event.getPlayer().getOpenInventory().getTitle().equals("合成台")){
            //return player unclaimed items
            Inventory craftingUI = event.getInventory();
            Player player = (Player) event.getPlayer();
            //-get contents and return items
            ItemStack[] contents = craftingUI.getContents();

            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[10]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[11]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[12]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[19]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[20]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[21]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[28]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[29]);
            returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(player,contents[30]);
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("废品回收站")){
            //return item havent sell
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(13)
            );
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("钢炉")){
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(13)
            );
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("熔化炉")){
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(15)
            );
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(20)
            );
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(33)
            );
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("冰箱")){
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(22)
            );
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("膜具雕刻台")){
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(11)
            );
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(29)
            );
        }
        if(event.getPlayer().getOpenInventory().getTitle().equals("注入机")){
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(13)
            );
            event.getPlayer().getInventory().addItem(
                    event.getInventory().getItem(31)
            );
        }
    }

    @EventHandler
    public void ClickMainMenu(InventoryClickEvent event){
        //get configs
        if(main.getData("config","menuclickeffect",(Player) event.getWhoClicked()) == null){
            //send player hint
            event.getWhoClicked().sendMessage(ChatColor.GREEN+">>>"+ChatColor.YELLOW+"如果你觉得点击声很烦的话，你可以在设置里关闭点击声！");
            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,0.1f);
            main.setData("config","menuclickeffect",(Player) event.getWhoClicked(),true);
        }else if((Boolean) main.getData("config","menuclickeffect",(Player) event.getWhoClicked()) == true) {
            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_CHAIN_STEP, 1, 1);
        }

        if(event.getWhoClicked().getOpenInventory().getTitle().contains("废品回收站")){
            if(event.getRawSlot() != 38){
                event.setCancelled(true);
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("熔化炉")){
            if(event.getRawSlot() < 54){
                if(event.getRawSlot() != 15 || event.getRawSlot() != 20 || event.getRawSlot() != 33){
                    event.setCancelled(true);
                }else {
                    if(event.getRawSlot() == 49){
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().contains("设置")){
            //I~ Just wanna tell u how im fEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELING~~~~~~~~~
            //GOTTA MAKE U UPPERSTAND~
            event.setCancelled(true);
            if(event.getRawSlot() == 49){
                //close inv
                event.getWhoClicked().closeInventory();
            }
            if(event.getRawSlot() == 50){
                //return to main menu
                ((Player) event.getWhoClicked()).chat("/mm");
            }
            if(event.getRawSlot() == 19){
                //switch on/off status
                main.setData("config","menuclickeffect",(Player) event.getWhoClicked(),!(Boolean)main.getData("config","menuclickeffect",(Player) event.getWhoClicked()));
                ((Player) event.getWhoClicked()).chat("/menu settings");
            }
            if(event.getRawSlot() == 21){
                //switch on/off status
                main.setData("config","packon",(Player) event.getWhoClicked(),!(Boolean)main.getData("config","packon",(Player) event.getWhoClicked()));
                ((Player) event.getWhoClicked()).chat("/menu settings");
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("test")){
            //you know the rules, and so do i~
            event.setCancelled(true);
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("钢炉")){
            if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT){
                event.setCancelled(true);
            }
            if(event.getRawSlot() != 13 && event.getRawSlot() != 31){
                if(event.getRawSlot() < 54){
                    event.setCancelled(true);
                }
                if(event.getRawSlot() == 49){
                    event.getWhoClicked().closeInventory();
                }
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("快捷传送菜单")){
            event.setCancelled(true);
            if(event.getRawSlot() == 49){
                event.getWhoClicked().closeInventory();
            }
            if(event.getRawSlot() == 50){
                ((Player)event.getWhoClicked()).chat("/mm");
            }
            if(event.getRawSlot() == 10){
                ((Player)event.getWhoClicked()).chat("/survival");
            }
            if(event.getRawSlot() == 11){
                ((Player)event.getWhoClicked()).chat("/spawn");
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("LikiaNetwork生存")){
            event.setCancelled(true);
            if(event.getRawSlot() == 19){
                //make player open crafting table menu
                ((Player)event.getWhoClicked()).chat("/ui crafting");
            }
            if(event.getRawSlot() == 20){
                //open profession menu
                ((Player)event.getWhoClicked()).chat("/menu profes");
            }
            if(event.getRawSlot() == 21){
                //open profession menu
                ((Player)event.getWhoClicked()).chat("/menu warp");
            }
            if (event.getRawSlot() == 45) {
                //make player open settings
                ((Player)event.getWhoClicked()).chat("/menu settings");
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("职业和等级")){
            event.setCancelled(true);
            Integer slot = event.getRawSlot();
            if(slot == 49){
                //close inv
                event.getWhoClicked().closeInventory();
            }
            if(slot == 50){
                //return to main menu
                ((Player) event.getWhoClicked()).chat("/mm");
            }
            if(slot == 29 || slot == 30 || slot == 31 || slot == 32 || slot == 33){
                //set player pros
                if (slot == 29){
                    main.setData("main","profession",(Player) event.getWhoClicked(),"swordsman");
                }
                if (slot == 30){
                    main.setData("main","profession",(Player) event.getWhoClicked(),"miner");
                }
                if (slot == 31){
                    main.setData("main","profession",(Player) event.getWhoClicked(),"carptender");
                }
                if (slot == 32){
                    main.setData("main","profession",(Player) event.getWhoClicked(),"farmer");
                }
                if (slot == 33){
                    main.setData("main","profession",(Player) event.getWhoClicked(),"builder");
                }
                event.getWhoClicked().sendMessage("§a已切换！");
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("合成台")){
            //if clicked in whitelisted slots then set notcancelled
            Integer slot = event.getRawSlot();
            if(slot == -999 || slot >= 54
                    || slot == 10 || slot == 11 || slot == 12 || slot == 19 || slot == 20 || slot == 21 || slot == 28 || slot == 29 || slot == 30 ){//nothin
            }else if (event.getRawSlot() == 49) {
                event.getWhoClicked().closeInventory();
            }else if(slot == 24){
                if(event.getInventory().getItem(24).getType() == Material.BARRIER){
                    event.setCancelled(true);
                }
            }else{
                event.setCancelled(true);
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("铁砧")){
            event.setCancelled(true);
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("木工")){
            event.setCancelled(true);
            if(event.getRawSlot() == 49){
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                player.playSound(player.getLocation(),Sound.BLOCK_CHAIN_BREAK,1,1);
            }else {

                if (event.getRawSlot() == 20) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender combat 1");
                }
                if (event.getRawSlot() == 21) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender tool 1");
                }
                if (event.getRawSlot() == 22) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender farming 1");
                }
                if (event.getRawSlot() == 23) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender enchant 1");
                }
                if (event.getRawSlot() == 24) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender industry 1");
                }
                if (event.getRawSlot() == 30) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender armor 1");
                }
                if (event.getRawSlot() == 31) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender util 1");
                }
                if (event.getRawSlot() == 32) {
                    Player player = (Player) event.getWhoClicked();
                    player.chat("/npc carptender slayer 1");
                }
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("流浪商人")){
            //create list
            event.setCancelled(true);
            List<String> lore1 = new ArrayList<>();
            lore1.add(" "); lore1.add("§6技能：锁定方块 §e§l右键点击");
            lore1.add("§7锁定你看向的方块，");lore1.add("§7方块锁定后只有你能和该方块互动，");lore1.add("§7消耗§6§l0.5G§r§7。");
            lore1.add(" "); lore1.add("§6技能：解锁方块 §e§l左键点击");
            lore1.add("§7解锁你看向的方块，");
            lore1.add("§7返还§6§l0.4G§r§7。");
            Object[][] list = {{usefuls.newitem(Material.NAME_TAG,"§a方块锁定器",1,lore1,1,"BLOCK_LOCKER"),10.0}};
            //load file and try to buy item
            Integer slot = event.getRawSlot();
            slot = slot - 10;

            if(list[slot][0] != null) {
                if ((Double) main.getData("main","purse",(Player) event.getWhoClicked()) >= (Double) list[slot][1]) {
                    //grant item and revoke purse
                    Player player = (Player) event.getWhoClicked();
                    DecimalFormat df = new DecimalFormat("#.00"); // format
                    main.setData("main","purse",player,Double.valueOf(df.format((Double) main.getData("main","purse",player) - (Double) list[slot][1])));
                    player.getInventory().addItem((ItemStack) list[slot][0]);
                    player.sendMessage("§a购买成功！");
                }else {
                    event.getWhoClicked().sendMessage("§c硬币不足！:(");
                }
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("冰箱")){
            if(event.getRawSlot() < 6*9 && event.getRawSlot() != 22 && event.getRawSlot() != -999){
                event.setCancelled(true);
            }
            if(event.getRawSlot() == 49){
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                player.playSound(player.getLocation(),Sound.BLOCK_CHAIN_BREAK,1,1);
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("注入机")){
            if(event.getRawSlot() < 6*9 && event.getRawSlot() != 13 && event.getRawSlot() != -999
                    && event.getRawSlot() != 31){
                event.setCancelled(true);
            }
            if(event.getRawSlot() == 49){
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                player.playSound(player.getLocation(),Sound.BLOCK_CHAIN_BREAK,1,1);
            }
        }else if(event.getWhoClicked().getOpenInventory().getTitle().equals("膜具雕刻台")){
            if(event.getRawSlot() < 6*9 && event.getRawSlot() != 29 && event.getRawSlot() != 11 && event.getRawSlot() != -999
            && event.getRawSlot() != 13 && event.getRawSlot() != 14 && event.getRawSlot() != 15 && event.getRawSlot() != 22
            && event.getRawSlot() != 22 && event.getRawSlot() != 23 && event.getRawSlot() != 24 && event.getRawSlot() != 31
            && event.getRawSlot() != 32 && event.getRawSlot() != 33){ //function by likia.likianetwork.survival.storage.CustomUIOpen.java
                event.setCancelled(true);
            }
            if(event.getClick() == ClickType.SHIFT_LEFT ||event.getClick() == ClickType.SHIFT_RIGHT){
                event.setCancelled(true);
            }
            if(event.getRawSlot() == 49){
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                player.playSound(player.getLocation(),Sound.BLOCK_CHAIN_BREAK,1,1);
            }
        } else if(event.getWhoClicked().getOpenInventory().getTitle().equals("战斗配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("test配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("工具配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("农业配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("附魔配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("工业配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("防具配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("杂项材料配方菜单") || event.getWhoClicked().getOpenInventory().getTitle().equals("战利品配方菜单")) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getRawSlot() >= 0 && event.getRawSlot() <= 44){
                //使用NBTAPI获取NBT并查看价格是否等于0
                // (如无价格则该商品不存在)
                NBTItem nbtItem = new NBTItem(event.getClickedInventory().getItem(event.getRawSlot()));

                if(nbtItem.getBoolean("isBuyable") != false){
                    //执行购买指令并查看该玩家当前能否解锁该物品
                    //get player currency
                    Double purse = Double.valueOf(String.valueOf(main.getData("main","purse",player)));
                    Integer id = event.getRawSlot() * new NBTItem(event.getInventory().getItem(49)).getInteger("page");

                    if(((List<String>) main.getData("recipe","recipe"+gettype(event.getWhoClicked().getOpenInventory().getTitle()),player)).get(id).equals("0")) {
                        if (purse >= nbtItem.getInteger("price")) {
                            //get requirements
                            if (nbtItem.getString("reqType") != "") {
                                //get if player have the meeting requirement
                                if (nbtItem.getString("reqType").equals("level")) {
                                    if (Integer.valueOf(String.valueOf( main.getData("main","level",player))) >= nbtItem.getInteger("req")) {
                                        unlock(player, id, gettype(event.getWhoClicked().getOpenInventory().getTitle()), purse, event,new NBTItem(event.getInventory().getItem(49)).getInteger("page"));
                                    } else {
                                        event.getWhoClicked().closeInventory();
                                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 0.1f);
                                        event.getWhoClicked().sendMessage(ChatColor.RED + "你的等级不够购买该配方！");
                                    }
                                } else if (nbtItem.getString("reqType").equals("rank")) {

                                } else {
                                    player.sendMessage("ERROR 2x000001");
                                }
                            } else {
                                //give player recipe
                                unlock(player, id, gettype(event.getWhoClicked().getOpenInventory().getTitle()), purse, event,new NBTItem(event.getInventory().getItem(49)).getInteger("page"));
                            }
                        } else {
                            event.getWhoClicked().closeInventory();
                            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 0.1f);
                            event.getWhoClicked().sendMessage(ChatColor.RED + "你的硬币不够购买该配方！");
                        }
                    }else{
                        event.getWhoClicked().closeInventory();
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 0.1f);
                        event.getWhoClicked().sendMessage(ChatColor.RED + "你已解锁该配方！");
                    }
                }
            }

            if (event.getRawSlot() == 49) {
                player.closeInventory();
            }

            if (event.getRawSlot() == 50) {
                player.chat("/npc carptender");
            }

            if (event.getRawSlot() == 53 && event.getInventory().getItem(53).getType().equals(Material.ARROW)) {
                NBTItem data = new NBTItem(event.getInventory().getItem(49));
                Integer nextpage = data.getInteger("Page") + 1;
                player.chat("/npc carptender "+gettype(event.getWhoClicked().getOpenInventory().getTitle())+" " + nextpage);
            }

            if (event.getRawSlot() == 45 && event.getInventory().getItem(45).getType().equals(Material.ARROW)) {
                NBTItem data = new NBTItem(event.getInventory().getItem(49));
                Integer prevpage = data.getInteger("page");
                prevpage = prevpage - 1;
                player.chat("/npc carptender "+gettype(event.getWhoClicked().getOpenInventory().getTitle())+" " + prevpage);
            }
        }else {
            int id = find(((InventoryView)event.getInventory()).getTitle());

            if(id != -1){

                if(id == 0){

                }

            }
        }
    }

    private void unlock(Player player, Integer id, String type, Double playerpurse, InventoryClickEvent event, Integer page){
        List<String> list = (List<String>) main.getData("recipe","recipe"+type,player);

        list.set(id,"1");

        playerpurse = playerpurse - new NBTItem(event.getClickedInventory().getItem(event.getRawSlot())).getInteger("price");

        main.setData("recipe","recipe" + type,player,list);
        main.setData("main","purse",player,playerpurse);
        player.sendMessage(ChatColor.GREEN+"解锁成功！");
        player.playSound(player.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
        player.chat("/npc carptender "+type+" "+page);
    }

    private String gettype(String str){
        if (str.equals("战斗配方菜单")) {
            return "combat";
        } else if (str.equals("工具配方菜单")) {
            return "tool";
        } else if (str.equals("农业配方菜单")) {
            return "farming";
        } else if (str.equals("附魔配方菜单")) {
            return "enchant";
        } else if (str.equals("工业配方菜单")) {
            return "industry";
        } else if (str.equals("防具配方菜单")) {
            return "armor";
        } else if (str.equals("杂项材料配方菜单")) {
            return "util";
        } else if (str.equals("战利品配方菜单")) {
            return "slayer";
        }else{
            return "";
        }
    }

    private void returnButDoNotReturnAirAndAirIsNullSoReturingAirWillCauseErrorThatIsNullPointerExpectionFunction(Player player,ItemStack item){
        if(item != null){
            //get inv and give player item
            PlayerInventory inv = player.getInventory();
            inv.addItem(item);
        }
    }
}
