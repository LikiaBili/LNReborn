package likia.likianetwork.survival;

import likia.likianetwork.survival.api.papi.PapiDisplay;
import likia.likianetwork.survival.api.papi.PapiLang;
import likia.likianetwork.survival.api.papi.PapiMain;
import likia.likianetwork.survival.api.papi.PapiSymbol;
import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.commands.*;
import likia.likianetwork.survival.listerner.*;
import likia.likianetwork.survival.api.fileoperation;
import likia.likianetwork.survival.storage.StaticStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class main extends JavaPlugin {

    public static boolean isDebugOn = true;

    public static void setData(String filenameKey, String dataKey, Player player, Object value){
        if(fileoperation.getFileID(filenameKey) != -1) {
            fileoperation.datas[new fileoperation().getFileID(filenameKey)].set(player.getName() + "." + dataKey, value);
        }
    }

    public static void setData(String filenameKey, String dataKey, String player, Object value){
        if(fileoperation.getFileID(filenameKey) != -1) {
            fileoperation.datas[new fileoperation().getFileID(filenameKey)].set(player+ "." + dataKey, value);
        }
    }

    public static void setDataTemp(String[] datakeys,Object value){
        String key = datakeys[0];
        int range = 1;
        while (range < datakeys.length) {

            key = key + "." + datakeys[range];

            range++;
        }
        fileoperation.tempfile.set(key,value);
    }

    public static Object getDataTemp(String[] datakeys,Object defaultValue){
        String key = datakeys[0];
        int range = 1;
        while (range < datakeys.length) {

            key = key + "." + datakeys[range];

            range++;
        }
        //denull
        Object returnObject = CombatSystem.deNull(fileoperation.tempfile.get(key),defaultValue);
        if(returnObject == defaultValue){
            setDataTemp(datakeys,defaultValue);
        }
        return returnObject;
    }

    public static Object getData(String filenameKey, String dataKey, Player player){
        if(fileoperation.getFileID(filenameKey) != -1) {
            return fileoperation.datas[new fileoperation().getFileID(filenameKey)].get(player.getName() + "." + dataKey);
        }else {
            return null;
        }
    }

    public static Object getData(String filenameKey, String dataKey, String playername){
        if(fileoperation.getFileID(filenameKey) != -1) {
            return fileoperation.datas[new fileoperation().getFileID(filenameKey)].get(playername + "." + dataKey);
        }else {
            return null;
        }
    }

    public static String getLang(Player player,String lang){
        try {
            String str = (String) fileoperation.langdatas[(Integer) getData("main","language",player)].get(lang);
            if(str == null){
                str = "%!%"+lang+"%!%";
                System.out.println("ERROR: "+str+", "+lang);
            }
            return str;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "%!%"+lang+"%!%";
        }
    }

    public static String getLang(String playername,String langstrname){
        Integer lang = (Integer) getData("main","language",playername);
        if(playername.equalsIgnoreCase("en_us")){
            lang = 0;
        } else if (playername.equalsIgnoreCase("zh_cn")) {
            lang = 1;
        }

        try{
            String str = (String) fileoperation.langdatas[lang].get(langstrname);
            if(str == null){
                str = "%!%"+langstrname+"%!%";
                System.out.println("ERROR: "+str+", "+lang);
            }
            return str;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "%!%"+lang+"%!%";
        }
    }

    public static Integer playerID(Player player){
        if(getData("main","LikiaPlayerID",player) == null){
            Integer newplayerid = -1;
            if(new fileoperation().datas[2].get("Server=LikiaPlayerID") != null) {
                newplayerid = (Integer) new fileoperation().datas[2].get("Server=LikiaPlayerID") + 1;
            }else {
                newplayerid = 0;
            }
            new fileoperation().datas[2].set("Server=LikiaPlayerID",newplayerid);
            setData("main","LikiaPlayerID",player,newplayerid);
            return newplayerid;
        }else {
            return (Integer) getData("main","LikiaPlayerID",player);
        }
    }

    @Override
    public void onLoad(){

    }

    private static Date today = Calendar.getInstance(TimeZone.getDefault()).getTime();

    @Override
    public void onEnable() {
        //stuff
        today = Calendar.getInstance(TimeZone.getDefault()).getTime();
        //configs
        this.saveResource("datanpc.yml",false);
        this.saveResource("datamain.yml",false);
        this.saveResource("dataconfig.yml",false);
        this.saveResource("datarecipe.yml",false);
        //register placeholders
        new PapiMain().register();
        new PapiDisplay().register();
        new PapiLang().register();
        new PapiSymbol().register();
        //register listeners
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockClickDetector(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenusListen(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new DeathRespawnStuff(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ConsumeListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockMineListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PickItemListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CombatSystem(), this);
        //register commands
        Bukkit.getPluginCommand("getnbt").setExecutor(new GetHeldItemNBT());
        Bukkit.getPluginCommand("filedata").setExecutor(new LoadData());
        Bukkit.getPluginCommand("util").setExecutor(new DebugCommand());
        Bukkit.getPluginCommand("spawn").setExecutor(new TeleportSpawn());
        Bukkit.getPluginCommand("survival").setExecutor(new TeleportSurvival());
        Bukkit.getPluginCommand("jail").setExecutor(new TeleportJail());
        Bukkit.getPluginCommand("test").setExecutor(new ChamberOpen());
        Bukkit.getPluginCommand("mm").setExecutor(new MenuOpen());
        Bukkit.getPluginCommand("menu").setExecutor(new MenuOpen());
        Bukkit.getPluginCommand("ui").setExecutor(new CustomUIOpen());
        Bukkit.getPluginCommand("purse").setExecutor(new PlayerCurrency());
        Bukkit.getPluginCommand("npc").setExecutor(new NpcInterface());
        Bukkit.getPluginCommand("getblocknbt").setExecutor(new GetBlockNBT());
        Bukkit.getPluginCommand("lang").setExecutor(new LangCommand());
        Bukkit.getPluginCommand("help").setExecutor(new VannilaReplace());
        Bukkit.getPluginCommand("whitelst").setExecutor(new VannilaReplace());

        //autosave
        new BukkitRunnable(){
            @Override
            public void run() {
                fileoperation.instantSave();
            }
        }.runTaskTimer(this,(Integer)new fileoperation().datas[2].get("Server=Autosave"),(Integer)new fileoperation().datas[2].get("Server=Autosave"));
        refreshDailyQuests();
        new BukkitRunnable(){
            @Override
            public void run() {
                if(today.getDay() < Calendar.getInstance(TimeZone.getDefault()).getTime().getDay()){
                    today = Calendar.getInstance(TimeZone.getDefault()).getTime();

                    refreshDailyQuests();
                }
            }
        }.runTaskTimer(getPlug(),1200,1200);
    }
    public void refreshDailyQuests(){
        StaticStorage.dailyQuests[0] = (String) StaticStorage.avalableDailyQuests[0][new Random().nextInt(StaticStorage.avalableDailyQuests[0].length)][0];
        StaticStorage.dailyQuests[1] = (String) StaticStorage.avalableDailyQuests[1][new Random().nextInt(StaticStorage.avalableDailyQuests[1].length)][0];
        StaticStorage.dailyQuests[2] = (String) StaticStorage.avalableDailyQuests[2][new Random().nextInt(StaticStorage.avalableDailyQuests[2].length)][0];
        usefuls.boardcastLangMessage("message_boardcast_refreshedDailyQuests");
        System.out.println("Refreshed Daily Quests");
    }
    @Override
    public void onDisable() {
        fileoperation.instantSave();
    }

    public static void SwitchDebugMode(@Nullable Boolean OnOrOff){
        if(OnOrOff == null){isDebugOn = !isDebugOn;}else {isDebugOn = OnOrOff;}
    }

    public static Plugin getPlug(){
        return getProvidingPlugin(likia.likianetwork.survival.main.class);
    }
}
