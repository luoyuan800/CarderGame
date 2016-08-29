package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;

/**
 * Created by gluo on 8/29/2016.
 */
public interface Group {
    Location getLocation();
    Card push(Card card);
}
