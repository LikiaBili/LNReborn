package likia.likianetwork.survival.api.papi;

import likia.likianetwork.survival.main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PapiLang extends PlaceholderExpansion {
    @Override
    public String getAuthor() {
        return "Likia";
    }

    @Override
    public String getIdentifier() {
        return "lnslang";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        return main.getLang( player.getName(),params);
    }
}
