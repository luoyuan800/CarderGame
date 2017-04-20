package cn.gavin.card.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import cn.gavin.card.model.Card;

import java.io.File;
import java.io.IOException;

/**
 * Created by gluo on 4/19/2017.
 */
public class FileUtils {
    public static File[] buildPaths(File[] base, String... segments) {
        File[] result = new File[base.length];
        for (int i = 0; i < base.length; i++) {
            result[i] = buildPath(base[i], segments);
        }
        return result;
    }

    public static File buildPath(File base, String... segments) {
        File cur = base;
        for (String segment : segments) {
            if (cur == null) {
                cur = new File(segment);
            } else {
                cur = new File(cur, segment);
            }
        }
        return cur;
    }

    public static File getPkgPath() {
        File directory = FileUtils.buildPath(Environment.getDataDirectory(), "carder", "pkg");
        boolean exist = true;
        if (!directory.exists() || !directory.isDirectory()) {
            exist = directory.mkdirs();
        }
        if (exist) {
            return directory;
        } else {
            return null;
        }
    }

    public static String getType(File file){
        int lastDot = file.getName().lastIndexOf(".");
        if(lastDot > 0){
            return file.getName().substring(lastDot + 1);
        }
        return "";
    }

    public static File getUpdateMarkFile(){
        File update = buildPath(Environment.getDataDirectory(), "carder", "update.config");
        if(update.exists() && update.isFile()){
            return update;
        }else{
            File parent = update.getParentFile();
            if(!parent.exists()|| !parent.isDirectory()){
                parent.mkdirs();
            }
            try {
                update.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return update;
        }
    }

    public static File getCardStackFile(String name){
        File stack = buildPath(Environment.getDataDirectory(), "carder", "stack", name);
        if(!stack.exists()){
            makeParentDirectory(stack);
            try {
                stack.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stack;
    }

    public static File getCardStackDirectory(){
        File stack = buildPath(Environment.getDataDirectory(), "carder", "stack");
        if(!stack.exists()){
            stack.mkdirs();
        }
        return stack;
    }

    public static boolean makeParentDirectory(File file) {
        File parent = file.getParentFile();
        return !(!parent.exists() || !parent.isDirectory()) || parent.mkdirs();
    }

    public static Bitmap loadImage(Card card){
        return BitmapFactory.decodeStream(card.getClass().getClassLoader().getResourceAsStream(card.getImg()));
    }
}
