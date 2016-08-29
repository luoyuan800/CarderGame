package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gluo on 8/29/2016.
 */
public class HandCards extends ArrayList<Card> implements Group {
    private static final Location location = Location.HAND;
    public void shuffle(){
        Collections.shuffle(this);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public Card push(Card card){
        add(card);
        return card;
    }
}
