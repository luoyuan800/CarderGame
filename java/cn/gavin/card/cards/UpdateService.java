package cn.gavin.card.cards;

import android.content.Context;
import cn.gavin.card.utils.FileUtils;
import cn.gavin.card.utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by gluo on 4/19/2017.
 */
public abstract class UpdateService {
    private Context context;

    public UpdateService(Context context) {
        this.context = context;
    }

    public abstract boolean needUpdate();

    public abstract void update();

    public void markUpdate(String... pkgs) {
        File update = FileUtils.getUpdateMarkFile();
        if (update != null && update.exists()) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(update, true));
                for (String pkg : pkgs) {
                    writer.newLine();
                    writer.write(pkg);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void processUpdate(){
        File update = FileUtils.getUpdateMarkFile();
        if (update != null && update.exists()) {
            ExtendCardLoader extendCardLoader = new ExtendCardLoader(context);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(update));
                String pkg = reader.readLine();
                while (pkg!=null){
                    if(StringUtils.isNotEmpty(pkg)){
                        File pkgFile = FileUtils.buildPath(FileUtils.getPkgPath(), pkg);
                        if(pkgFile.exists()&&pkgFile.isFile()){
                            extendCardLoader.extend(pkgFile);
                        }
                    }
                    pkg = reader.readLine();
                }
                update.deleteOnExit();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
