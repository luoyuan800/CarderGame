package cn.gavin.card.cards;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gluo on 4/18/2017.
 */
public class CardClassLoader extends URLClassLoader {
    private Set<String> alreadyLoad = new HashSet<>();
    public CardClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        for(URL url : urls){
            alreadyLoad.add(url.getFile());
        }
    }

    public Class loadClass(String type, String pkg){
        File file = new File(pkg);
        try {
            URL url = file.toURI().toURL();
            if(!alreadyLoad.contains(url.getFile())){
                addURL(url);
                alreadyLoad.add(url.getFile());
            }

            return loadClass(type);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
