package cn.gavin.card.manager;

import cn.gavin.card.model.carder.Carder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gluo on 8/29/2016.
 */
public class DuleManager {
    public static final DuleManager duleManager = new DuleManager();
    private Map<Carder, GroupManager> groupManagerMap;
    public GroupManager getGroupManager(Carder carder){
        return groupManagerMap.get(carder);
    }

    public synchronized void addGroupManager(Carder carder , GroupManager groupManager){
        if(groupManagerMap == null){
            groupManagerMap = new HashMap<Carder, GroupManager>(2);
        }
        groupManagerMap.put(carder, groupManager);
    }
}
