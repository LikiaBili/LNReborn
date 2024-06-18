package likia.likianetwork.survival.commands;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import likia.likianetwork.survival.storage.CraftingItemList;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NpcInterface implements CommandExecutor {

    private String getLang(CommandSender sender,String lang){
        return main.getLang((Player) sender,lang);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            sender.sendMessage("ERROR 0x000003");
        }else {
            if (args[0].equals("carptender")) {
                if (args.length == 1) {
                    if (main.getData("npc","hint.Carptender",(Player) sender) == null) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW + "木工" + ChatColor.WHITE + ": 你好！我是当地的木匠，我可以教你一些有用的配方");
                                Player player = (Player) sender;
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW + "木工" + ChatColor.WHITE + ": 当然那肯定是要" + ChatColor.YELLOW + "马内" + ChatColor.WHITE + "的");
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW + "木工" + ChatColor.WHITE + ": 再次点击我可以打开菜单！");
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                                main.setData("npc","hint.Carptender",(Player) sender,true);

                                //complete quest
                                usefuls.completequest(1,0,0.25,0,0,"",player);
                            }
                        }.run();

                    } else if (main.getData("npc","hint.Carptender",(Player) sender).equals(true)) {
                        Inventory menu = bgmenu("木工");
                        Player player = (Player) sender;

                        player.playSound(player.getLocation(),Sound.BLOCK_CHAIN_STEP,1,1);

                        ItemStack[] contents = menu.getContents();

                        contents[20] = newitemautolore1(Material.NETHERITE_SWORD, "战斗",sender);
                        contents[21] = newitemautolore1(Material.DIAMOND_PICKAXE, "工具",sender);
                        contents[22] = newitemautolore1(Material.WHEAT, "农业",sender);
                        contents[23] = newitemautolore1(Material.ENCHANTING_TABLE, "附魔",sender);
                        contents[24] = newitemautolore1(Material.BLAST_FURNACE, "工业",sender);
                        contents[30] = newitemautolore1(Material.IRON_CHESTPLATE, "防具",sender);
                        contents[31] = newitemautolore1(Material.FLINT, "杂项材料",sender);
                        contents[32] = newitemautolore1(Material.ROTTEN_FLESH, "战利品",sender);

                        contents[49] = newitem(Material.BARRIER, ChatColor.RED + "关闭");

                        menu.setContents(contents);
                        player.openInventory(menu);
                    }
                } else {
                    ((Player)sender).playSound(((Player) sender).getLocation(),Sound.BLOCK_CHAIN_STEP,1,1);
                    if (args[1].equals("combat")) {
                        Inventory menu = bgmenu("战斗配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("test")) {
                        Inventory menu = bgmenu("test配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("tool")) {
                        Inventory menu = bgmenu("工具配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("farming")) {
                        Inventory menu = bgmenu("农业配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("enchant")) {
                        Inventory menu = bgmenu("附魔配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("industry")) {
                        Inventory menu = bgmenu("工业配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("armor")) {
                        Inventory menu = bgmenu("防具配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("util")) {
                        Inventory menu = bgmenu("杂项材料配方菜单");
                        runmenu(menu, args, sender);
                    } else if (args[1].equals("slayer")) {
                        Inventory menu = bgmenu("战利品配方菜单");
                        runmenu(menu, args, sender);
                    } else {
                        sender.sendMessage("ERROR 0x000001");
                    }
                }
            }else if(args[0].equals("jamie")){
                //read file
                if(main.getData("npc","firstJamie",(Player) sender) == null){
                    Player player = (Player) sender;
                    player.sendMessage("§7[NPC] §e"+getLang(player,"npc_names_jamie")+"§f: "+getLang(player,"message_npc_jamie_1"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,100,1);
                    player.getInventory().addItem(RecipeItemList.newitemautolore(Material.NETHERITE_SWORD,1,"SWORD_OF_JAMIE","杰米之剑",20,0.0,15,0,0,0,0,1,false));

                    main.setData("npc","firstJamie",player,true);
                }else {
                    Player player = (Player) sender;
                    player.sendMessage("§7[NPC] §e"+getLang(player,"npc_names_jamie")+"§f: "+getLang(player,"message_npc_jamie_2"));
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,100,1);
                }
            }else if(args[0].equals("wandering")){
                //startup
                Inventory menu = Bukkit.createInventory(null,6*9,"流浪商人");
                Player player = (Player) sender;
                ItemStack[] contents = menu.getContents();

                //deco
                ItemStack border = newitem(Material.GRAY_STAINED_GLASS_PANE," ");
                contents[0] = border;
                contents[1] = border;
                contents[2] = border;
                contents[3] = border;
                contents[4] = border;
                contents[5] = border;
                contents[6] = border;
                contents[7] = border;
                contents[8] = border;
                contents[9] = border;
                contents[17] = border;
                contents[18] = border;
                contents[26] = border;
                contents[27] = border;
                contents[35] = border;
                contents[36] = border;
                contents[44] = border;
                contents[45] = border;
                contents[46] = border;
                contents[47] = border;
                contents[48] = border;
                contents[49] = border;
                contents[50] = border;
                contents[51] = border;
                contents[52] = border;
                contents[53] = border;

                //create items
                contents[10] = newitem(Material.NAME_TAG,"§a"+getLang(sender,"items_item_name_@BLOCK_LOCKER@"),1,autolore1(getLang(sender,"items_item_shop_description_@BLOCK_LOCKER@"),player,10.0));

                //finalize
                menu.setContents(contents);
                player.openInventory(menu);
            }else if(args[0].equals("linsee")){
                Player player = (Player) sender;
                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW + getLang(player,"npc_names_hayashishio") + ChatColor.WHITE + ": "+getLang(player,"message_npc_hayashishio_1"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW + getLang(player,"npc_names_hayashishio")+ ChatColor.WHITE + ": "+getLang(player,"message_npc_hayashishio_2"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.YELLOW +getLang(player,"npc_names_hayashishio")+ ChatColor.WHITE + ": "+ChatColor.AQUA+"/mm"+ChatColor.WHITE+"！");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
            }else if(args[0].equals("likia")){
                Player player = (Player) sender;
                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.RED + "LikiaBili" + ChatColor.WHITE + ": "+getLang(player,"message_npc_likia_1"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1);
                sleep(1000);
                sender.sendMessage(ChatColor.GRAY + "[NPC] " + ChatColor.RED + "LikiaBili" + ChatColor.WHITE + ": "+getLang(player,"message_npc_likia_2"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 0.9F);
                sleep(250);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.2F);
                sleep(250);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.2F);
                sleep(250);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.4F);
                sleep(250);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 0.8F);
                sleep(250);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.3F);
                sleep(200);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 0.8F);
                sleep(200);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.3F);
                sleep(200);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 0.8F);
                sleep(200);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.3F);
            }else if(args[0].equals("link")){

                if((Boolean) main.getData("npc","isFirstTalkToLink",(Player) sender)){
                    Player player = (Player) sender;
                    //talk to player
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,100,1);
                    player.sendMessage("§7[NPC] §e"+main.getLang(player,"npc_names_link")+"§f: "+main.getLang(player,"message_npc_link_1"));
                    sleep(1000);
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,100,1);
                    player.sendMessage("§7[NPC] §e"+main.getLang(player,"npc_names_link")+"§f: "+main.getLang(player,"message_npc_link_2"));
                    sleep(1000);
                    player.playSound(player.getLocation(),Sound.ENTITY_VILLAGER_TRADE,100,1);
                    player.sendMessage("§7[NPC] §e"+main.getLang(player,"npc_names_link")+"§f: "+main.getLang(player,"message_npc_link_3"));
                    main.setData("npc","isFirstTalkToLink",player,false);
                }else {
                    //make menu and stuf
                    Inventory menu = usefuls.bgmenu(main.getLang(sender.getName(),"ui_menu_label_title_link"));
                    menu = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",menu);
                    ItemStack[] contents = menu.getContents();

                    contents[49] = newitem(Material.BARRIER,main.getLang(sender.getName(),"ui_menu_buttons_name_close"));

                    menu.setContents(contents);
                    ((Player)sender).openInventory(menu);
                }

            }else if(args[0].equals("witch")) {
                Player player = (Player) sender;
                Integer talkState = (Integer) main.getData("npc","firstMeetWitch",player);
                if(talkState == 0) {
                    //talkstate = 0
                    //0 is for just first meet
                    usefuls.dialouge(player,Sound.ENTITY_VILLAGER_AMBIENT,1.0f,Sound.ENTITY_VILLAGER_CELEBRATE,1.1f,
                            new String[]{"message_npc_witch_1","message_npc_witch_2"},"npc_names_witch",1000);
                    main.setData("npc", "firstMeetWitch", sender.getName(), 1);
                }else if(talkState == 1){
                    //1 is not first met but met again
                    if((Integer) main.getData("main","prestige",player) >= 1 || (Integer) main.getData("main","level",player) >= 60){
                        //you met the requirements
                        usefuls.dialouge(player,Sound.ENTITY_VILLAGER_CELEBRATE,1.1f,
                                new String[]{"message_npc_witch_3"},"npc_names_witch",0);
                        main.setData("npc", "firstMeetWitch", sender.getName(), 2);
                    }else {
                        //you talk to him again but you did not met the requirements
                        usefuls.dialouge(player,Sound.ENTITY_VILLAGER_AMBIENT,1.0f,new String[]{"message_npc_witch_2"},"npc_names_witch",0);
                    }
                }else if(talkState == 2){
                    //open menu

                    //create menu and deco and preset
                    Inventory menu = usefuls.bgmenu(main.getLang(player,"npc_names_witch"));
                    menu = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",menu);
                    ItemStack[] contents = menu.getContents();

                }else {
                    //maybe expections
                    usefuls.dialouge(player,Sound.ENTITY_VILLAGER_AMBIENT,1.0f,Sound.ENTITY_VILLAGER_CELEBRATE,1.1f,
                            new String[]{"message_npc_witch_1","message_npc_witch_2"},"npc_names_witch",1000);
                    main.setData("npc", "firstMeetWitch", sender.getName(), 1);
                }
            }else if(args[0].equals("sell")){
                //get item in hand
                Player player = (Player)sender;
                ItemStack sellitem = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
                if(sellitem == null){
                    sender.sendMessage("§7[NPC] §e"+getLang(player,"npc_names_recycler")+"§f:"+getLang(player,"message_npc_recycler_a1")+"\n§7[NPC] §e"+getLang(player,"npc_names_recycler")+"§f:"+getLang(player,"message_npc_recycler_a2"));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 100, 1);
                }else {
                    //get item price
                    Float price = new NBTItem(sellitem).getFloat("Price") * sellitem.getAmount();
                    //say we dont take trash when you give him price 0 item
                    if (price == 0.0) {
                        sender.sendMessage("§7[NPC]§e"+getLang(player,"npc_names_recycler")+"§f:"+getLang(player,"message_npc_recycler_b"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 0.3f);
                    } else {
                        //open menu for selling
                        Inventory sellmenu = usefuls.bgmenu("废品回收站");
                        ItemStack solditem = player.getItemInHand();
                        sellmenu.setItem(13, solditem);
                        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), null);

                        List<String> lore = new ArrayList<>();
                        lore.add("§7将该物品以§6§l" + price + "ᄕ§r§7价格出售。");
                        lore.add(" ");
                        lore.add("§e点击出售！");
                        ItemStack selluiitem = usefuls.newitem(Material.GREEN_STAINED_GLASS_PANE, "§a出售", 1, lore);
                        sellmenu.setItem(38, selluiitem);
                        sellmenu.setItem(42, usefuls.newitem(Material.RED_STAINED_GLASS_PANE, "§c取消"));
                        player.openInventory(sellmenu);
                        //create runable to detect for player is selling
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                List<String> lore = new ArrayList<>();
                                lore.add("§7将该物品以§6§l" + price + "ᄕ§r§7价格出售。");
                                lore.add(" ");
                                lore.add("§e点击出售！");

                                if (player.getItemOnCursor().isSimilar(selluiitem)) {
                                    player.setItemOnCursor(null);

                                    Double purse = (Double) main.getData("main","purse",player);
                                    DecimalFormat df = new DecimalFormat("#.00"); // format
                                    main.setData("main","purse",player, Double.valueOf(df.format(purse + price)));
                                    sellmenu.setItem(13, null);
                                    player.closeInventory();

                                    player.playSound(player.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,100,1.5f);
                                    player.sendMessage("§a已成功卖出！");
                                    System.out.println("Player:\""+player.getName()+"\" sold item:\""+solditem.getI18NDisplayName()+"\" price:"+price);

                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(main.getPlug(), 0, 2);
                    }
                }
            }else if(args[0].equals("buy")){
                sender.sendMessage("§7[NPC] "+getLang(sender,"npc_names_comingsoon")+" §e商人§f: "+getLang(sender,"message_npc_comingsoon_1"));
                ((Player) sender).playSound(((Player) sender).getLocation(),Sound.ENTITY_VILLAGER_AMBIENT,100,1);
            }else {
                sender.sendMessage("ERROR 0x000001");
            }
        }
        return false;
    }

    private void runmenu(Inventory menu,String[] args,CommandSender sender){
        ItemStack[] contents = menu.getContents();

        contents[46] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        contents[47] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        contents[48] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        contents[51] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        contents[52] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        contents[53] = newitem(Material.ARROW,ChatColor.GREEN+"下一页");
        contents[45] = newitem(Material.ARROW,ChatColor.GREEN+"上一页");
        contents[50] = newitem(Material.ARROW,ChatColor.GREEN+"返回");

        //使用NBT API创建带nbt的物品并将页码存入
        NBTItem datastore = new NBTItem(newitem(Material.BARRIER,ChatColor.RED+"关闭"));
        datastore.setInteger("page",Integer.valueOf(args[2]));
        contents[49] = datastore.getItem();

        if(args[2].equals("1")){
            contents[45] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        }

        //使用内置存储API获取物品列表
        List<ItemStack> items = CraftingItemList.getItems(args[1],(Player) sender);
        try {
            Integer page = Integer.valueOf(args[2]);

            Integer range = 0;
            while (true){
                Integer tmp = page*54;
                tmp = tmp - 54;
                if(items.get(range+tmp) == null || range >= 45){break;}
                contents[range] = items.get(range+tmp);
                range++;
            }
        }catch (Exception nFe){
            if(sender.isOp() == false) {
                sender.sendMessage("ERROR 0x000004");
            }
        }
        if(contents[44].getType() == Material.GRAY_STAINED_GLASS_PANE){
            contents[53] = newitem(Material.BLACK_STAINED_GLASS_PANE," ");
        }
        menu.setContents(contents);
        Player player = (Player) sender;
        player.openInventory(menu);
    }

    private List<String> autolore1(String des,Player player,Double price){
        Double purse = (Double) main.getData("main","purse",player);
        List<String> lore = new ArrayList<>();
        lore.add("§7"+des);
        lore.add(" ");
        lore.add("§f价格:§6§l"+price+"ᄕ");
        lore.add(" ");
        if(purse >= price){
            lore.add("§e点击购买！");
        }else {
            lore.add("§c金币不足！");
        }
        return lore;
    }
    private Inventory bgmenu(String menuname){
        Inventory menu = Bukkit.createInventory(null,6*9,menuname);
        ItemStack[] contents = menu.getContents();

        ItemStack bg = newitem(Material.GRAY_STAINED_GLASS_PANE," ");
        Integer range = 0;
        while(true){
            contents[range] = bg;
            range = range + 1;
            if(range >= 54){break;}
        }

        menu.setContents(contents);
        return menu;
    }

    private ItemStack newitem(Material material,String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    private ItemStack newitem(Material material,String name,Integer count){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    private ItemStack newitem(Material material, String name,Integer count, List<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }
    private ItemStack newitemautolore1(Material material, String name,CommandSender sender){
        Player player = (Player)sender;
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+name+"配方");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"打开"+name+"合成配方菜单");
        lore.add(" ");
        lore.add(ChatColor.YELLOW+"点击打开！");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    private void sleep(Integer mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
