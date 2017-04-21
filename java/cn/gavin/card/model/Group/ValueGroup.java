package cn.gavin.card.model.group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import cn.gavin.card.model.carder.Carder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by gluo on 8/29/2016.
 */
public class ValueGroup extends Stack<Card> implements cn.gavin.card.model.group.Group {
    private static final Location location = Location.VALUE;
    public void shuffle(){
        Collections.shuffle(this);
    }
    private Carder owner;

    public Carder getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Carder owner) {
        this.owner = owner;
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
