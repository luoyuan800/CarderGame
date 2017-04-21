package cn.gavin.card.model.group;

import cn.gavin.card.exp.EmptyCard;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import cn.gavin.card.model.carder.Carder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
public class HandCards extends ArrayList<Card> implements cn.gavin.card.model.group.Group {
    private static final Location location = Location.HAND;
    private Carder owner;

    public Carder getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Carder owner) {
        this.owner = owner;
    }
    private static final int limit =6;
    public void shuffle(){
        Collections.shuffle(this);
    }

    @Override
    public List<Card> getMark(Mark mark) {
        ArrayList<Card> cards =new ArrayList<Card>(6);
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

    public Card push(Card card){
        if(size() < limit) {
            add(card);
            return card;
        }else {
            return EmptyCard.emptyCard;
        }
    }

    public Card pop(){
        return EmptyCard.emptyCard;
    }
}
