package cn.gavin.card.db;

import android.content.Context;
import android.database.Cursor;
import cn.gavin.card.cards.CardClassLoader;
import cn.gavin.card.model.TenchType;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.net.URL;

/**
 * Created by gluo on 4/18/2017.
 */
public class DbHelper{
    private static final int version = 1;
    private static final String password = "carder";
    private static DbHelper instance;
    private CardClassLoader loader;
    public static DbHelper instance(){
        if(instance == null){
            throw new RuntimeException("Need to init db first!");
        }
        return instance;
    }
    public synchronized static void init(Context context){
        if(instance == null) {
            instance = new DbHelper(context);
        }
    }
    private SQLiteDatabase database;
    private DbHelper(Context context){
        super();
        initializeSQLCipher(context);
        loader = new CardClassLoader(new URL[0], this.getClass().getClassLoader());
    }

    public boolean isNeedToUpdate(String id, String version) {
        return false;
    }

    public TenchType queryCardTT(String id) {
        return null;
    }

    public void deleteCard(String id) {

    }

    private void initializeSQLCipher(Context context) {
        SQLiteDatabase.loadLibs(context);
        File databaseFile = context.getFileStreamPath("demo.db");
        if(!databaseFile.exists()) {
            databaseFile.mkdirs();
        }
        database = SQLiteDatabase.openOrCreateDatabase(databaseFile, password, null);
        if(database.getVersion() <= 0){
            onCreate();
        }else if(database.getVersion() < version){
            onUpgrade();
        }

    }

    private void onUpgrade() {
        database.setVersion(version);
    }

    private void onCreate(){
        database.execSQL("create table t1(a, b)");
        database.execSQL("insert into t1(a, b) values(?, ?)", new Object[]{"one for the money",
                "two for the show"});
        database.setVersion(version);
    }

    public void close(){
        database.close();
    }

    public Class queryClass(String t_id){
        Cursor cursor = database.rawQuery("select * from TYPE where T_ID = '" + t_id + "'", null);
        try {
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                String type = cursor.getString(cursor.getColumnIndex("T_CLASS"));
                String pkg = cursor.getString(cursor.getColumnIndex("T_FILE"));
                return loader.loadClass(type, pkg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        return null;
    }

    public void insertCard(String c_id, String tt){

    }

    public void insertType(String t_id, String t_class, String t_file, String t_group, String t_version){

    }

}
