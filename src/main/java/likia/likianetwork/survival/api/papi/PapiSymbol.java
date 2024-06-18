package likia.likianetwork.survival.api.papi;

import likia.likianetwork.survival.main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PapiSymbol extends PlaceholderExpansion {
    static String[] listnames ={"pencil","heart","defence","strength","coin"};
    static String[] listsymbols = {"ᄘ","ᄚ","ᄙ","ᄖ","ᄕ"};
    static String[] listnormalsymbols = {"✎","❤","❈","❁","G"};

    @Override
    public String getAuthor() {
        return "Likia";
    }

    @Override
    public String getIdentifier() {
        return "lnssymbol";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        Integer range = 0;
        String symbol = params;
        while (true){
            if(range >= listnames.length){
                break;
            }

            if(listnames[range].equals(params)){
                //get if player have spc symbol on
                if((Boolean) main.getData("config","packon",player.getName())){
                    symbol = listsymbols[range];
                }else {
                    symbol = listnormalsymbols[range];
                }
                break;
            }

            range++;
        }
        return symbol;
    }
}
