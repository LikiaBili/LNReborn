package likia.likianetwork.survival.listerner;

import likia.likianetwork.survival.api.papi.PapiSymbol;
import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class DeathRespawnStuff implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        main.setDataTemp(new String[]{event.getPlayer().getName(),"Health"},main.getDataTemp(new String[]{event.getPlayer().getName(),"MaxHealth"},100));
        event.setRespawnLocation(new Location(Bukkit.getWorld("hub"),89.5,63,261.5,90,0));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        event.setDeathMessage(ChatColor.RED+"\u111b"+ChatColor.DARK_RED+event.getDeathMessage());
        //save data and stuff
        Player player = event.getEntity();
        Location location = player.getLocation();
        main.setData("main","worldLocation.World",player,location.getWorld().getName());
        main.setData("main","worldLocation.X",player,location.getX());
        main.setData("main","worldLocation.Y",player,location.getY());
        main.setData("main","worldLocation.Z",player,location.getZ());
        main.setData("main","worldLocation.Yaw",player,location.getYaw());
        main.setData("main","worldLocation.Pitch",player,location.getPitch());
        main.setData("main","worldLocation.Saved",player,true);
        main.setData("main","worldLocation.World",player,location.getWorld().getName());
        main.setData("main","death.X",player,location.getX());
        main.setData("main","death.Y",player,location.getY());
        main.setData("main","death.Z",player,location.getZ());
        main.setData("main","death.Yaw",player,location.getYaw());
        main.setData("main","death.Pitch",player,location.getPitch());
        main.setData("main","death.Saved",player,true);
        DecimalFormat df = new DecimalFormat("#.00"); // format
        main.setData("main","purse",player,Double.valueOf(df.format((double) main.getData("main","purse",player) / 2)));
        player.sendMessage(main.getLang(player,"message_death_lostcoin").replaceAll("@coins@",df.format((double) main.getData("main","purse"
                ,player) / 2)).replaceAll("@coinsymbol@",new PapiSymbol().onRequest(player,"coin")));
    }
}
