package cn.gavin.card.model.group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import cn.gavin.card.model.carder.Carder;

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
    Carder getOwner();
    void setOwner(Carder owner);
}
