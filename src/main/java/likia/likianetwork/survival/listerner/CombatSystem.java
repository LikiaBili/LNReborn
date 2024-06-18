package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtinjector.NBTInjector;
import likia.likianetwork.survival.api.fileoperation;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import likia.likianetwork.survival.storage.RecipeItemList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

public class CombatSystem implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event){
        //get entity stuff
        Entity entity = event.getEntity();
        //health stuff
        final Object[][] entitydata = {
                {EntityType.ZOMBIE, 80, 10, "僵尸", 1,0,4,0,"ZOMBIE",false}, //{EntityType,Health,HealthFlow,entityname,level,def,atk,str,id}
                {EntityType.SKELETON, 90, 20, "骷髅", 2,0,4,0,"SKELETON",false},
                {EntityType.CREEPER, 100, 10, "苦力怕", 3,0,0,0,"CREEPER",false},
                {EntityType.ENDERMAN, 300, 50, "末影人", 5,0,6,1,"ENDERMAN",false},
                {EntityType.SPIDER, 50, 10, "蜘蛛", 2,20,0,0,"SPIDER",false},
                {EntityType.BLAZE, 500, 100, "烈焰人", 35,0,150,1,"BLAZE",false},
                {EntityType.ZOMBIFIED_PIGLIN, 300, 50, "僵尸猪灵", 25,0,100,0,"ZOMBIE_PIGLIN",false},
                {EntityType.PIGLIN, 600, 100, "猪灵", 35,0,150,0,"PIGLIN",false},
                {EntityType.PIGLIN_BRUTE, 1000, 250, "猪灵蛮兵", 50,0,300,0,"PIGLIN_BRUTE",false},
                {EntityType.WITHER_SKELETON, 2000, 500, "凋零骷髅", 75,0,500,0,"WITHER_SKELETON",false}
        };
        Integer range = 0;
        boolean modifyed = false;
        while (entitydata.length < range) {
            if (entitydata[range][0] == entity.getType()) {
                //modify entity
                Location location = entity.getLocation();
                entity.setCustomNameVisible(true);
                //random HP
                Integer hp = new Random().nextInt((Integer) entitydata[range][2]) + (Integer) entitydata[range][1];
                Integer level = (Integer) entitydata[range][4];
                entity.setCustomName("§7[§8LV" + level + "§7] §c" + entitydata[range][3] + " §a" + hp + "§7/§a" + hp + "§c❤");
                //save data
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.setMaxHealth(hp);
                livingEntity.setHealth(hp);
                String uuid = entity.getUniqueId().toString();
                main.setDataTemp(new String[]{uuid, "Level"}, entitydata[range][4]);
                main.setDataTemp(new String[]{uuid, "Defence"}, entitydata[range][5]);
                main.setDataTemp(new String[]{uuid, "Name"}, entitydata[range][3]);
                main.setDataTemp(new String[]{uuid, "Attack"}, entitydata[range][6]);
                main.setDataTemp(new String[]{uuid, "Strength"}, entitydata[range][7]);
                main.setDataTemp(new String[]{uuid, "LikiaID"}, entitydata[range][8]);
                main.setDataTemp(new String[]{uuid, "Limits", "1Dmg"}, entitydata[range][9]);
                main.setDataTemp(new String[]{uuid, "Health"}, hp);
                main.setDataTemp(new String[]{uuid, "MaxHealth"}, hp);
                modifyed = true;
                break;
            }
            range++;
        }
        if(!modifyed){
            String uuid = entity.getUniqueId().toString();
            main.setDataTemp(new String[]{uuid, "Level"}, 1);
            main.setDataTemp(new String[]{uuid, "Defence"}, 0);
            main.setDataTemp(new String[]{uuid, "Name"}, entity.getType().toString());
            main.setDataTemp(new String[]{uuid, "Attack"}, 20);
            main.setDataTemp(new String[]{uuid, "Strength"}, 0);
            main.setDataTemp(new String[]{uuid, "LikiaID"}, entity.getType().toString());
            main.setDataTemp(new String[]{uuid, "Limits", "1Dmg"}, false);
            main.setDataTemp(new String[]{uuid, "Health"}, 100);
            main.setDataTemp(new String[]{uuid, "MaxHealth"}, 100);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        event.setDamage(0.01);
        if(event.getDamager().getType().equals(EntityType.PLAYER) && event.getEntity().getLocation().getWorld().getName().equalsIgnoreCase("hub")){
            event.setCancelled(true);
            return;
        }


        Player player = null;
        if(main.getDataTemp(new String[]{event.getEntity().getUniqueId().toString(), "LikiaID"},"Null").equals("ZOMBIE_KING")){
            //skill: spawn lightning
            event.getDamager().getLocation().getWorld().spawnEntity(event.getDamager().getLocation(),EntityType.LIGHTNING);
        }
        String uuid = "Null";
        String uuidDamager = "Null";
        if(!event.getEntity().getType().equals(EntityType.PLAYER)){
            uuid = event.getEntity().getUniqueId().toString();
        }else {
            player = (Player) event.getEntity();
            uuid = player.getName();
        }
        if(!event.getDamager().getType().equals(EntityType.PLAYER)){
            uuidDamager = event.getEntity().getUniqueId().toString();
        }else {
            player = (Player) event.getDamager();
            uuidDamager = player.getName();
        }
        //get data
        Integer dmg = (Integer) main.getDataTemp(new String[]{uuidDamager,"Attack"},10);
        Integer def = (Integer) main.getDataTemp(new String[]{uuid,"Defence"} ,0);

        //print debug info
        if(main.isDebugOn){
            System.out.println("def:"+def+",dmg:"+dmg+",calcdmg:"+usefuls.caculatedamge(dmg,def));
        }

        dmg = usefuls.caculatedamge(dmg,def);

        Integer hp = (Integer) main.getDataTemp(new String[]{uuid,"Health"},100);

        if(main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},null) != null) {
            if(main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},false).equals(true)) {
                dmg = 1;
            }
        }

        String hpcolor = "§a";
        if(hp - dmg < ((Integer) main.getDataTemp(new String[]{uuid,"MaxHealth"},100)) / 4){
            hpcolor = "§c";
        }else if(hp - dmg < ((Integer) main.getDataTemp(new String[]{uuid,"MaxHealth"},100)) / 2){
            hpcolor = "§e";
        }

        //not player then set custom name
        Integer remainhp = hp - dmg;
        if(!event.getEntity().getType().equals(EntityType.PLAYER)) {
            if((Boolean) main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},false)) {
                String hitslang = main.getLang(player,"ui_combat_namestats_hits").replaceAll("@hp@", String.valueOf(hp - dmg));
                event.getEntity().setCustomName("§7[LV" + main.getDataTemp(new String[]{uuid, "Level"},1) + "] §a" + main.getDataTemp(new String[]{uuid, "Name"},"Error:NameNull") + " " + hpcolor +hitslang);
            }else{
                event.getEntity().setCustomName("§7[LV" + main.getDataTemp(new String[]{uuid, "Level"},1) + "] §a" + main.getDataTemp(new String[]{uuid, "Name"},"Error:NameNull") + " " + hpcolor + remainhp + "§a/" + main.getDataTemp(new String[]{uuid, "MaxHealth"},100) + " §c❤ (-"+dmg+")");
            }
            event.getEntity().setCustomNameVisible(true);
        }

        if(remainhp <= 0){
            event.setDamage(2048.0d);
            remainhp = 0;
        }

        main.setDataTemp(new String[]{uuid,"Health"},remainhp);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)){
            String uuid = "";
            if(event.getEntity().getType() != EntityType.PLAYER){
                //is not player

                //get uuid
                uuid = event.getEntity().getUniqueId().toString();
            }else {
                //is player

                //get name as uuid
                Player player = (Player) event.getEntity();
                uuid = (player).getName();
            }

            int dmg = (int) (event.getDamage()*5.0d);
            event.setDamage(0.01);
            if(dmg < 1){
                System.out.println("EventDmg:"+event.getDamage());
            }
            Integer def = (Integer) main.getDataTemp(new String[]{uuid,"Defence"} ,0);

            if(main.isDebugOn){
                System.out.println("def:"+def+",dmg:"+dmg+",calcdmg:"+usefuls.caculatedamge(dmg,def));
            }
            dmg = usefuls.caculatedamge(dmg,def);

            Integer hp = (Integer) main.getDataTemp(new String[]{uuid,"Health"},100);

            if(main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},null) != null) {
                if(main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},false).equals(true)) {
                    dmg = 1;
                }
            }

            String hpcolor = "§a";
            if(hp - dmg < ((Integer) main.getDataTemp(new String[]{uuid,"MaxHealth"},100)) / 4){
                hpcolor = "§c";
            }else if(hp - dmg < ((Integer) main.getDataTemp(new String[]{uuid,"MaxHealth"},100)) / 2){
                hpcolor = "§e";
            }

            //not player then set custom name
            Integer remainhp = hp - dmg;
            if(!event.getEntity().getType().equals(EntityType.PLAYER)) {
                if((Boolean) main.getDataTemp(new String[]{uuid,"Limits","1Dmg"},false)) {
                    String hitslang = main.getLang("EN_US","ui_combat_namestats_hits").replaceAll("@hp@", String.valueOf(hp - dmg));
                    event.getEntity().setCustomName("§7[LV" + main.getDataTemp(new String[]{uuid, "Level"},1) + "] §a" + main.getDataTemp(new String[]{uuid, "Name"},"Error:Null") + " " + hpcolor +hitslang);
                }else{
                    event.getEntity().setCustomName("§7[LV" + main.getDataTemp(new String[]{uuid, "Level"},1) + "] §a" + main.getDataTemp(new String[]{uuid, "Name"},"Error:Null") + " " + hpcolor + remainhp + "§a/" + main.getDataTemp(new String[]{uuid, "MaxHealth"},100) + " §c❤ (-"+dmg+")");
                }
                event.getEntity().setCustomNameVisible(true);
            }
            if(remainhp <= 0){
                event.setDamage(9999);
                remainhp = 0;
            }

            main.setDataTemp(new String[]{uuid,"Health"},remainhp);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        //get entity name
        String likiaid = (String) main.getDataTemp(new String[]{event.getEntity().getUniqueId().toString(),"LikiaID"},"Null");
        if(likiaid == null){
            likiaid = "NULL";
        }
        Location location = event.getEntity().getLocation();
        if(likiaid.equalsIgnoreCase( "ZOMBIE_KING")){
            //drop items
            Integer essencecount = new Random().nextInt(1)+2;
            ItemStack entityEssence = RecipeItemList.newitemautolore(Material.LIME_DYE,1,"ENTITY_ESSENCE","实体精华",16,100.0,0,0,0,0,0,essencecount,false);
            location.getWorld().dropItem(location,entityEssence);
        }
    }

    public static Object deNull(Object object,Object defaultValue){
        if(object == null) {
            return defaultValue;
        }else {
            return object;
        }
    }
}
