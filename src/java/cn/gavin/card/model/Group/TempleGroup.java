package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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
}
