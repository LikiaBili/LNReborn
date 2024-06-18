package likia.likianetwork.survival.api.papi;

import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Locale;

public class PapiDisplay extends PlaceholderExpansion {

    public static String[] prestigeColor = {"§7","§9","§9","§9","§9","§e","§e","§e","§e","§e","§6","§6","§6","§6","§6","§6","§c","§c","§c","§c","§c","§c","§5","§5","§5","§5","§5","§5","§d","§d","§d",
    "§d","§d","§d","§f","§f","§f","§f","§f","§f","§b","§b","§b","§b","§b","§b","§b","§1","§1","§1","§1","§1","§1","§1","§1","§1","§1","§0"};


    @Override
    public String getAuthor() {
        return "Likia";
    }

    @Override
    public String getIdentifier() {
        return "lnsdisplay";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("levelprefix")){
            int prestigeCount = (Integer) main.getData("main","prestige",player.getName());
            int level = (Integer) main.getData("main","level",player.getName());
            return prestigeColor[prestigeCount]+"["+usefuls.getlevelcolor(level)+level+prestigeColor[prestigeCount]+"]";
        }

        if(params.toLowerCase(Locale.ROOT).contains("xpneed")) {
            if((Integer) main.getData("main","level",player.getName()) >= 60){
                return  main.getLang(player.getName(),"ui_xp_maxed");
            }
            Integer value = usefuls.getXpNeedForLevelUp(usefuls.getXpNeedForLevelUp((Integer) main.getData("main","level",player.getName()))) - (Integer) main.getData("main","exp",player.getName());
            return value+"";
        }
        if(params.toLowerCase(Locale.ROOT).contains("xpneedpercent")) {
            Integer percent = (Integer) main.getData("main","exp",player.getName()) / usefuls.getXpNeedForLevelUp((Integer) main.getData("main","level",player.getName()));
            System.out.println((Integer) main.getData("main","exp",player.getName())+"/"+usefuls.getXpNeedForLevelUp((Integer) main.getData("main","level",player.getName())));
            return String.valueOf(percent*100);
        }
        if(params.toLowerCase(Locale.ROOT).contains("level")) {
            Integer level = (Integer) main.getData("main","level",player.getName());
            return usefuls.getlevelcolor(level)+level;
        }
        if(params.equalsIgnoreCase("quest")){
            if((Integer) main.getData("main","questid",player.getName()) > 0){
                return main.getLang(player.getName(),"ui_quest_"+main.getData("main","questid",player.getName()));
            }else {
                return main.getLang(player.getName(),"ui_quest_none");
            }
        }
        if(params.equalsIgnoreCase("version")){
            return "Pv0.1";
        }
        return "§cN/A";
    }
}
