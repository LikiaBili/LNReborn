package likia.likianetwork.survival.api;

import likia.likianetwork.survival.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class fileoperation {

    public static final String studiosDataDisk = "C:";

    public static boolean saveTmpFile = true;

    public static File[] files = {new File(main.getPlug().getDataFolder(), "datamain.yml"),
            new File(main.getPlug().getDataFolder(), "datanpc.yml"),
            new File(main.getPlug().getDataFolder(), "dataconfig.yml"),
            new File(main.getPlug().getDataFolder(), "datarecipe.yml")};
    public static FileConfiguration[] datas = {YamlConfiguration.loadConfiguration(files[0]),
            YamlConfiguration.loadConfiguration(files[1]),
            YamlConfiguration.loadConfiguration(files[2]),
            YamlConfiguration.loadConfiguration(files[3])};


    public static File[] langfiles = {
            new File(studiosDataDisk+"/LikiaStudiosData/LikiaNetwork/Lang/Survival","en_us.yml"),
            new File(studiosDataDisk+"/LikiaStudiosData/LikiaNetwork/Lang/Survival","zh_cn.yml")};

    public static FileConfiguration tempfile = YamlConfiguration.loadConfiguration(new File(main.getPlug().getDataFolder(),"tempory.yml"));
    public static FileConfiguration[] langdatas = {YamlConfiguration.loadConfiguration(langfiles[0]),YamlConfiguration.loadConfiguration(langfiles[1])};

    public static String[][] filedatanames = {{"main","datarecipe.yml"},{"npc","datanpc.yml"},{"config","dataconfig.yml"},{"recipe","datarecipe.yml"}};


    public static Integer getFileID(String providedname){
        Integer range = 0;
        while (true){
            if(range >= files.length){return -1;}

            if(filedatanames[range][0].equals(providedname)){
                break;
            }

            range++;
        }
        return range;
    }

    public static void instantSave(){
        // save data
        int range = 0;

        while (true) {
            if(range == datas.length){break;}

            if(filedatanames[range][1].equalsIgnoreCase("tempory")){
                if(saveTmpFile){
                    try {
                        datas[range].save(files[range]);
                        System.out.println("saved temp file!");
                    }catch (IOException e){}
                }
            }else {
                try {
                    datas[range].save(files[range]);
                    System.out.println("saved file:" + filedatanames[range][1] + "datas length:" + datas.length);
                } catch (IOException e) {
                    System.out.println("______   ______   ______ ");
                    System.out.println("| ____|  |  _  |  |  _  |");
                    System.out.println("| |____  | |_| |  | |_| |");
                    System.out.println("| ____|  | ____|  | ____|");
                    System.out.println("| |____  |   |__  |   |__");
                    System.out.println("|_____|  |_|___|  |_|___|");
                    System.out.println(" ");
                    System.out.println("===============================");
                    System.out.println(" ");
                    System.out.println("ERROR: Can't save file " + filedatanames[range][1] + "!");
                    System.out.println(e.getMessage());
                }
            }

            range++;
        }
    }
}
