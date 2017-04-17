package cn.gavin.card.exp;


import cn.gavin.card.model.Card;

/**
 * Created by gluo on 8/29/2016.
 */
public class EmptyCard extends Card {
    public static final EmptyCard emptyCard = new EmptyCard();
    private EmptyCard(){

    }
    @Override
    public boolean invoke() {
        return false;
    }
}
