package cn.gavin.card.model.Group;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import lombok.Data;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by gluo on 8/29/2016.
 */
@Data
public class CardStack extends Stack<Card> implements Group {
    private static final Location location = Location.STACK;
    public void shuffle(){
        Collections.shuffle(this);
    }

    @Override
    public Location getLocation() {
        return location;
    }
}