package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
public class TempleGroup extends ArrayList<Card> implements Group {
    @Getter
    @Setter
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
}
