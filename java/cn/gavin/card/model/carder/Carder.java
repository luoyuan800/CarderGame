package cn.gavin.card.model.carder;


import cn.gavin.card.model.Card;
import cn.gavin.card.model.group.AbandonGroup;
import cn.gavin.card.model.group.CardStack;
import cn.gavin.card.model.group.HandCards;
import cn.gavin.card.model.group.MainArea;
import cn.gavin.card.model.group.TempleGroup;
import cn.gavin.card.model.group.ValueGroup;
import cn.gavin.card.model.Location;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
public class Carder {
    private int point;
    private int atk;
    private int def;
    private CardStack stack;
    private AbandonGroup abandon;
    private HandCards hand;
    private MainArea main;
    private ValueGroup value;
    private TempleGroup temple;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public TempleGroup getTemple() {
        return temple;
    }

    public void setTemple(TempleGroup temple) {
        this.temple = temple;
    }

    public ValueGroup getValue() {
        return value;
    }

    public void setValue(ValueGroup value) {
        this.value = value;
    }

    public MainArea getMain() {
        return main;
    }

    public void setMain(MainArea main) {
        this.main = main;
    }

    public HandCards getHand() {
        return hand;
    }

    public void setHand(HandCards hand) {
        this.hand = hand;
    }

    public AbandonGroup getAbandon() {
        return abandon;
    }

    public void setAbandon(AbandonGroup abandon) {
        this.abandon = abandon;
    }

    public CardStack getStack() {
        return stack;
    }

    public void setStack(CardStack stack) {
        this.stack = stack;
    }

    public List<Card> drawCard(Location location, int num) {
        List<Card> cards = new ArrayList<>(num);
        try {
            switch (location) {
                case STACK:
                    while (num-- > 0) {
                        Card card = stack.pop();
                        if(card!=null) {
                            cards.add(card);
                            card.move(hand);
                        }else{
                            break;
                        }
                    }
                    break;

            }
        }catch (EmptyStackException e){

        }
        return cards;
    }
}
