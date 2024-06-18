package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import likia.likianetwork.survival.api.usefuls;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuOpen implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //get player
        Player player = (Player) sender;
        if(command.getName().contains("menu")){
            if(args.length != 0) {
                if (args[0].contains("settings")) {
                    //create menu
                    Inventory menu = usefuls.bgmenu("设置");
                    //make decoration
                    menu = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE, "down", menu);
                    ItemStack[] contents = menu.getContents();
                    //create UIs

                    contents[49] = usefuls.newitem(Material.BARRIER, ChatColor.RED + "关闭");
                    contents[50] = usefuls.newitem(Material.ARROW, ChatColor.YELLOW + "返回");
                    contents[10] = newitemautolore2(Material.CHAIN, "点击菜单音效", sender.getName(), "icon", "menuclickeffect","是否开启在彩蛋内点击的音效");
                    contents[19] = newitemautolore2(Material.CHAIN, "点击菜单音效", sender.getName(), "button", "menuclickeffect","");
                    contents[12] = newitemautolore2(Material.MAP, "是否打开资源包", sender.getName(), "icon", "packon","是否已安装官方资源包");
                    contents[21] = newitemautolore2(Material.CHAIN, "是否打开资源包", sender.getName(), "button", "packon","");

                    //finalize
                    menu.setContents(contents);
                    player.openInventory(menu);
                } else if (args[0].contains("profes")) {
                    //create menu
                    Inventory menu = usefuls.bgmenu("职业和等级");
                    //make decoration
                    menu = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE, "down", menu);
                    ItemStack[] contents = menu.getContents();
                    //create UIs

                    contents[49] = usefuls.newitem(Material.BARRIER, ChatColor.RED + "关闭");
                    contents[50] = usefuls.newitem(Material.ARROW, ChatColor.YELLOW + "返回");
                    List<String> xpiconlore = new ArrayList<>();
                    Integer level = (Integer) main.getData("main","level",player);
                    assert level != null;
                    xpiconlore.add("§e" + usefuls.professidtoname((String) main.getData("main","profession",player)));
                    contents[13] = usefuls.newitem(Material.EXPERIENCE_BOTTLE, "§a当前职业：", 1, xpiconlore);
                    List<String> swrodsmanlore = new ArrayList<>();
                    swrodsmanlore.add("§7只能通过战斗获取经验值。");
                    swrodsmanlore.add(" ");
                    swrodsmanlore.add("§7冒险家经常选择的职业，也可以配合刷怪塔使用，");
                    swrodsmanlore.add("§7攻击伤害会随等级生长而增加。");
                    swrodsmanlore.add(" ");
                    swrodsmanlore.add("§e点击切换！");
                    List<String> minerlore = new ArrayList<>();
                    minerlore.add("§7只能通过挖掘方块获取经验值。");
                    minerlore.add(" ");
                    minerlore.add("§e点击切换！");
                    List<String> carptenderlore = new ArrayList<>();
                    carptenderlore.add("§7只能通过合成物品获取经验值。");
                    carptenderlore.add(" ");
                    carptenderlore.add("§7木匠合成物品会有些许几率提升物品稀有度。");
                    carptenderlore.add(" ");
                    carptenderlore.add("§e点击切换！");
                    List<String> farmerlore = new ArrayList<>();
                    farmerlore.add("§7只能通过收获农作物获取经验值。");
                    farmerlore.add(" ");
                    farmerlore.add("§7有概率在收获农作物时获取两倍作物。");
                    farmerlore.add(" ");
                    farmerlore.add("§e点击切换！");
                    List<String> builderlore = new ArrayList<>();
                    builderlore.add("§7不会增加经验值。");
                    builderlore.add(" ");
                    builderlore.add("§7在放置普通方块时有一定概率不消耗方块。");
                    builderlore.add(" ");
                    builderlore.add("§e点击切换！");

                    contents[29] = usefuls.newitem(Material.NETHERITE_SWORD, "§a剑士", 1, swrodsmanlore);
                    contents[30] = usefuls.newitem(Material.DIAMOND_PICKAXE, "§a矿工", 1, minerlore);
                    contents[31] = usefuls.newitem(Material.CRAFTING_TABLE, "§a木匠", 1, carptenderlore);
                    contents[32] = usefuls.newitem(Material.GOLDEN_HOE, "§a农夫", 1, farmerlore);
                    contents[33] = usefuls.newitem(Material.SCAFFOLDING, "§a建筑师", 1, builderlore);

                    menu.setContents(contents);
                    player.openInventory(menu);
                } else if(args[0].equals("warp")){
                    Inventory menu = usefuls.bgmenu("快捷传送菜单");
                    menu = usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"down",menu);
                    ItemStack[] contents = menu.getContents();

                    //ui

                    contents[49] = usefuls.newitem(Material.BARRIER,"§c关闭");
                    contents[50] = usefuls.newitem(Material.ARROW,"§e返回");

                    List<String> lore1 = new ArrayList<>();
                    lore1.add(" ");
                    lore1.add("§7传送到生存世界。");
                    lore1.add(" ");
                    lore1.add("§e快捷指令: §c/survival");
                    lore1.add("§e点击传送！");
                    contents[10] = usefuls.newitem(Material.GRASS_BLOCK,"§a生存世界",1,lore1);

                    List<String> lore2 = new ArrayList<>();
                    lore2.add(" ");
                    lore2.add("§7传送到主城。");
                    lore2.add(" ");
                    lore2.add("§e快捷指令: §c/spawn");
                    lore2.add("§e点击传送！");
                    contents[11] = usefuls.newitem(Material.GRASS_BLOCK,"§a主城",1,lore2);


                    //open
                    menu.setContents(contents);
                    player.openInventory(menu);

                } else {
                    sender.sendMessage(ChatColor.RED + "这个菜单不存在！你在想Peach!");
                }
            }

        }else {
            //create menu
            Inventory menu = usefuls.bgmenu("LikiaNetwork生存");
            //make decoration
            ItemStack dec = usefuls.newitem(Material.BLACK_STAINED_GLASS_PANE, " ");
            usefuls.invbar(Material.BLACK_STAINED_GLASS_PANE,"up",menu);

            ItemStack[] contents = menu.getContents();
            //create player skull

            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.GREEN + sender.getName() + "玩家信息");
            skullMeta.setOwner(sender.getName());
            skull.setItemMeta(skullMeta);
            //lore
            List<String> skulllore = new ArrayList<>();
            PersistentDataContainer container = player.getPersistentDataContainer();
            skulllore.add(PlaceholderAPI.setPlaceholders(player,"§c%lnssymbol_heart%生命: "+player.getMaxHealth()));
            skulllore.add(PlaceholderAPI.setPlaceholders(player,"§a%lnssymbol_defence%防御: "+main.getDataTemp(new String[]{player.getName(),"Defence"},0)));
            skulllore.add(PlaceholderAPI.setPlaceholders(player,"§b%lnssymbol_pencil%智力: "+main.getDataTemp(new String[]{player.getName(),"Intellgence"},0)));
            skulllore.add(PlaceholderAPI.setPlaceholders(player,"§c%lnssymbol_strength%力量: "+main.getDataTemp(new String[]{player.getName(),"Strength"},0)));
            skull.setLore(skulllore);
            contents[4] = skull;

            //create UIs

            contents[45] = newitemautolore1(Material.BLAZE_POWDER, "设置", "设置菜单");
            contents[19] = newitemautolore1(Material.CRAFTING_TABLE, "高级工作台", "高级工作台合成界面");
            contents[20] = newitemautolore1(Material.SHIELD,"职业&等级","职业选择与等级信息界面");
            contents[21] = newitemautolore1(Material.ENDER_PEARL,"世界传送","传送");

            //finalize
            menu.setContents(contents);
            player.openInventory(menu);
        }
        return false;
    }

    private ItemStack newitemautolore1(Material material, String name,String name2){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"打开"+name2);
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

    private ItemStack newitemautolore2(Material material,String name,String playername,String type,String settingname,String lorestr){
        if(type == "icon") {
            //create icon 1
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + name);
            List<String> lore = new ArrayList<>();
            lore.add(lorestr);

            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        } else if (type.contains("button")) {
            //check if player have setting on
            String active = "";
            if((Boolean) main.getData("config",settingname,Bukkit.getPlayer(playername)) == true){
                active = "关闭";
            }else {
                active = "开启";
            }
            //create icon 2
            ItemStack item;
            if(active.contains("开启")){
                item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            }else {
                item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "开关");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + active + name);
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "点击"+active+"！");

            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }else{
            return null;
        }
    }
}
