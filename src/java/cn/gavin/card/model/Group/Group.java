package cn.gavin.card.model.Group;

import cn.gavin.card.manager.GroupManager;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;

import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
public interface Group {
    Location getLocation();
    Card push(Card card);
    void shuffle();
    List<Card> getMark(Mark mark);

    Card pop();

    int size();
}
