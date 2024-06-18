package likia.likianetwork.survival.listerner;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConsumeListener implements Listener {
    @EventHandler
    public void onCOnsume(PlayerItemConsumeEvent event){
        //get if item is smth
        NBTItem item = new NBTItem(event.getItem());
        String likiaid = item.getString("LikiaID");
        Object[][] list = {
                {"CRUDE_FLESH_BLOCK",true,0,0,PotionEffectType.ABSORPTION,0,0},
                {"COMPRESSED_BREAD",false,1,10,PotionEffectType.ABSORPTION,120,1}
        };
        Integer range = 0;
        while (true){
            if(list.length == range){break;}
            //if likiaid match
            if(list[range][0].equals(likiaid)){
                //script
                event.setCancelled((boolean) list[range][1]);

                //add stats
                Player player = event.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,(Integer) list[range][2],(Integer) list[range][3]));
                player.addPotionEffect(new PotionEffect((PotionEffectType) list[range][4],(Integer) list[range][5],(Integer) list[range][6]));
            }
            range++;
        }
    }
}
