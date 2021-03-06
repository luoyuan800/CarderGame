package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by gluo on 8/29/2016.
 */
public class ValueGroup extends Stack<Card> implements Group {
    private static final Location location = Location.VALUE;
    public void shuffle(){
        Collections.shuffle(this);
    }

    @Override
    public List<Card> getMark(Mark mark) {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        for(Card card : this){
            if(card.getMark() == mark){
                cards.add(card);
            }
        }
        return cards;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
