package cn.gavin.card.manager;

import cn.gavin.card.model.Group.Group;
import cn.gavin.card.model.Location;

import java.util.Map;

/**
 * Created by gluo on 8/29/2016.
 */
public class GroupManager {
    private Map<Location, Group> groupMap;
    public Group getGroup(Location location){
        return groupMap.get(location);
    }
}
