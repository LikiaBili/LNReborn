package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TeleportSurvival implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 1) {
            if (args[0].equalsIgnoreCase("RandomTP")) {
                new BukkitRunnable() {
                    @Override
                    public void run() {

                        sender.sendMessage(main.getLang(sender.getName(), "message_command_randomtp_randoming"));
                        Integer time = 0;
                        while (true) {
                            if(time >= 100){
                                sender.sendMessage(main.getLang(sender.getName(),"message_error_timesoverflow"));
                                break;
                            }
                            Integer rseed = new Random().nextInt(200000) - 100000;

                            Integer c1=rseed/rseed+rseed;
                            Integer c2=c1-c1-rseed+1000-rseed;
                            Integer c3=c1+rseed/rseed*c2/c1;
                            Integer c6=rseed*rseed/rseed/10;
                            Integer c9=rseed/c6*c1;
                            Integer c10=0-1;
                            Integer c11=c9/c10;

                            int X;
                            int Z;
                            if (new Random().nextBoolean()) {
                                X = c3 + new Random().nextInt(100);
                            } else {
                                X = c3 - new Random().nextInt(100);
                            }
                            if (new Random().nextBoolean()) {
                                Z = c11+ new Random().nextInt(100);
                            } else {
                                Z = c11 - new Random().nextInt(100);
                            }

                            Block YBlock = Bukkit.getWorld("world").getHighestBlockAt(X,Z);
                            int Y = YBlock.getY();
                            //must be more than 64 blocks height
                            //water's height is Y64
                            if(Y > 64 && Y <= 96) {
                                //must on safe blocks
                                if (YBlock.getType().equals(Material.GRASS_BLOCK) || YBlock.getType().equals(Material.STONE) || YBlock.getType().equals(Material.SAND)) {

                                    Block Block1 = Bukkit.getWorld("world").getBlockAt(X, Y + 1, Z);
                                    Block Block2 = Bukkit.getWorld("world").getBlockAt(X, Y + 2, Z);
                                    Block Block3 = Bukkit.getWorld("world").getBlockAt(X, Y - 1, Z);

                                    if (X >= 300000) {
                                        X = 299999;
                                    }
                                    if (Z >= 300000) {
                                        Z = 299999;
                                    }
                                    if(X <= -300000){
                                        X = -299999;
                                    }
                                    if(Z <= -300000){
                                        Z = -299999;
                                    }

                                    if (Block1.getType() == Material.AIR || Block1.getType() == null) {
                                        if (Block2.getType() == Material.AIR || Block2.getType() == null) {
                                            if (Block3.getType() != Material.AIR || Block3.getType() != null) {
                                                Player player = (Player) sender;
                                                Location tplocation = new Location(Bukkit.getWorld("world"), X + 0.5, Y + 1, Z + 0.5);
                                                player.teleport(tplocation);
                                                player.sendTitle(main.getLang(player, "ui_randomtp_done"), "§e" + main.getLang(player, "ui_randomtp_position") + "§6" + X + "," + Y + "," + Z, 10, 60, 10);
                                                player.playSound(tplocation, Sound.BLOCK_NOTE_BLOCK_BELL, 100, 1.5f);
                                                sender.sendMessage(main.getLang(sender.getName(), "message_done"));
                                                break;
                                            }
                                            time++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }.run();
                return true;
            }
        }
        Player player = (Player) sender;
        if((Boolean) main.getData("main","worldLocation.Saved",player)) {
            double pitch = (float) main.getData("main", "worldLocation.Pitch", sender.getName());
            double yaw = (float) main.getData("main", "worldLocation.Yaw", sender.getName());
            double x = (double) main.getData("main", "worldLocation.X", sender.getName());
            double y = (double) main.getData("main", "worldLocation.Y", sender.getName());
            double z = (double) main.getData("main", "worldLocation.Z", sender.getName());
            Location tplocation = new Location(
                    Bukkit.getWorld((String) main.getData("main", "worldLocation.World", sender.getName())),
                    (float) x,
                    (float) y,
                    (float) z,
                    (float) pitch,(float) yaw
            );
            player.teleport(tplocation);
            sender.sendMessage(ChatColor.GREEN + "你已被传送至生存世界！");
            sender.sendMessage("§c§l/!\\警告！请勿在服务器内造大型建筑和机器！服务器未来可能会删除生存世界存档并为每名玩家分配一个世界！");
        }else {
            sender.sendMessage(main.getLang(player,"message_command_error_nopositionfound"));
        }
        return false;
    }
}
