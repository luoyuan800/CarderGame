package cn.gavin.card.model.Group;

import cn.gavin.card.exp.EmptyCard;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
public class TempleGroup extends ArrayList<Card> implements Group {
    private Location location;

    @Override
    public Card push(Card card) {
        add(card);
        return card;
    }

    @Override
    public void shuffle() {
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

    public Card pop(){
        return EmptyCard.emptyCard;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
