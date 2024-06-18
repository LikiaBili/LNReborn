package likia.likianetwork.survival.api.papi;

import likia.likianetwork.survival.api.usefuls;
import likia.likianetwork.survival.main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Locale;

public class PapiMain extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "Likia";
    }

    @Override
    public String getIdentifier() {
        return "lnsmain";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        return String.valueOf(main.getData("main",params,player.getName())); //used playername save method beacuse player could be offline
    }
}
