package cn.gavin.card.model.carder;


import cn.gavin.card.model.Group.AbandonGroup;
import cn.gavin.card.model.Group.CardStack;
import cn.gavin.card.model.Group.HandCards;
import cn.gavin.card.model.Group.MainArea;
import cn.gavin.card.model.Group.TempleGroup;
import cn.gavin.card.model.Group.ValueGroup;
import cn.gavin.card.model.Process;

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

}
