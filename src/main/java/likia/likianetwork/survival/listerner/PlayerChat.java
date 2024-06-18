package likia.likianetwork.survival.listerner;

import likia.likianetwork.survival.api.usefuls;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Locale;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerHoweverChatThisFunctionWillBeAcavatedANDHOWDOYOUSEETHISIFYOUARENOTAADMIN(PlayerChatEvent event){
        event.setFormat("%1$s"+ ChatColor.WHITE+": %2$s");
        //filter
        String[] banwords = {"习近平","政治"};
        String[] banwords2 = {"",""};
        Integer range = 0;
        while (true) {
            if(range >= banwords.length){break;}

            if (event.getMessage().toLowerCase(Locale.ROOT).contains(banwords[range]) && event.getMessage().toLowerCase(Locale.ROOT).contains(banwords2[range])) {
                event.setCancelled(true);
                if(!banwords2[range].equals("")) {
                    usefuls.banplayer(10000, event.getPlayer(), "LikiaNetwork禁止讨论政治性话题！\n屏蔽词:" + banwords[range] +","+ banwords2[range]);
                }else{
                    usefuls.banplayer(10000, event.getPlayer(), "LikiaNetwork禁止讨论政治性话题！\n屏蔽词:" + banwords[range]);
                }
            }
            range++;
        }
        //replace
        String[][] replacements = {
                {"[doge]","\ud0fe"},{"[huaji]","\uaeee"}//,{"[xianbei]","\u1145"}   //emoji
                ,{"傻逼","**"},{"sb","**"},{"fuck","f***"},{"moron","L"},{"你妈","**"}  //banwords
        };
        range = 0;
        String message = event.getMessage();
        while (true){
            if(range >= replacements.length){break;}

            message.replace(replacements[range][0],replacements[range][1]);
            range++;
        }
        if(event.getMessage() != message){event.setMessage(message);}
    }
}
