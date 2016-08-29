package cn.gavin.card.exp;


import cn.gavin.card.model.Card;

/**
 * Created by gluo on 8/29/2016.
 */
public class EmptyCard extends Card {
    @Override
    public boolean invoke() {
        return false;
    }
}
