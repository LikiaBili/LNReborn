package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LangCommand implements CommandExecutor,TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(args[0].equalsIgnoreCase("english") || args[0].equalsIgnoreCase("en_us")){
            main.setData("main","language",player,0);
            player.sendMessage(main.getLang(player,"message_done"));
        } else if (args[0].equalsIgnoreCase("chinese") || args[0].equalsIgnoreCase("zh_cn")) {
            main.setData("main","language",player,1);
            player.sendMessage(main.getLang(player,"message_done"));
        }else {
            player.sendMessage(main.getLang(player,"message_nolangfound"));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        tab.add("english");
        tab.add("en_us");
        tab.add("chinese");
        tab.add("zh_cn");
        return tab;
    }
}
