package cn.gavin.card.cards;

import android.content.Context;
import cn.gavin.card.db.DbHelper;
import cn.gavin.card.utils.FileUtils;
import cn.gavin.card.utils.IDParser;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Created by gluo on 4/18/2017.
 */
public class ExtendCardLoader {
    private Context context;
    private DbHelper dbHelper;

    public ExtendCardLoader(Context context) {
        this.context = context;
        dbHelper = DbHelper.instance();
    }

    public void extend() {
        File pkgs = FileUtils.getPkgPath();
        if (pkgs != null) {
            for (File file : pkgs.listFiles()) {
                extend(file);
            }
        }
    }

    public void extend(File file) {
        if (file.isFile() && "jar".equalsIgnoreCase(FileUtils.getType(file))) {
            try {
                JarFile jarFile = new JarFile(file);
                ZipEntry entry = jarFile.getEntry("pkg.properties");
                Properties properties = new Properties();
                properties.load(jarFile.getInputStream(entry));
                String group = properties.getProperty("group");
                String produce = properties.getProperty("produce");
                String type = properties.getProperty("type");
                String id = IDParser.buildTypeId(group,produce,type);
                String version = properties.getProperty("version");
                String clazz = properties.getProperty("class");
                if(dbHelper.isNeedToUpdate(id,version)){
                    dbHelper.insertType(id,clazz,file.getName(),group, version);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
