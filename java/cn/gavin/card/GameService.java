package cn.gavin.card;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Process;
import cn.gavin.card.model.carder.Carder;
import cn.gavin.card.model.effect.AfterDrawEffect;
import cn.gavin.card.model.effect.MainEffect;
import cn.gavin.card.model.effect.PrepareProcessEffect;
import cn.gavin.card.model.effect.ReceiveHarmEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gluo on 4/20/2017.
 */
public class GameService {
    private ArrayList<Carder> carders = new ArrayList<>(2);
    private Process currentProcess;
    private Carder harmTrigger;//收到超过50%伤害的玩家
    private Carder roundOwner;
    private int round;
    private boolean jump;

    //Progress: prepare -> prepareEffect -> beforeDraw -> draw|
//            afterDrawEffect -> main |
//            harm -> harmEffect -> choose -> end
    public void prepare() {
        if (isJump()) {
            jump = false;
            beforeDraw();
        } else {
            currentProcess = Process.PREPARE;
            prepareEffect();
        }
    }

    public void draw() {
        if (isJump()) {
            jump = false;
            main();
        } else {
            currentProcess = Process.DRAW;
            //TODO Select draw location
            Location location = Location.STACK;
            //Wait for user click draw
            List<Card> cards = roundOwner.drawCard(location, 1);
            afterDrawEffect(cards);
        }
    }

    public void main() {
        currentProcess = Process.MAIN;
        //Wait for action click next action
    }

    public void harm(Carder atker, Carder defener) {
        if (isJump()) {
            jump = false;
            end();
        }
        currentProcess = Process.END;
        int harm = atker.getAtk() - defener.getDef();
        if (harm > 0) {
            int lastPoint = defener.getPoint();
            if (lastPoint / 2 < harm) {
                harmTrigger = defener;
            }
            defener.setPoint(defener.getPoint() - harm);
            harmEffect(defener, atker, harm);
        } else {
            int lastPoint = atker.getPoint();
            if (lastPoint / 2 < harm) {
                harmTrigger = atker;
            }
            atker.setPoint(atker.getPoint() + harm);
            harmEffect(atker, defener, harm);
        }
    }

    public void harmEffect(Carder harmer, Carder harmMaker, int harm) {
        //TODO effect trigger while harming
        Map<String, Object> para = new HashMap<>();
        para.put("harmMaker", harmMaker);
        para.put("harm", harm);
        for (Card card : harmer.getMain()) {
            if (card instanceof ReceiveHarmEffect) {
                ((ReceiveHarmEffect) card).invokeWhenHarm(para);
            }
        }
        if(harmTrigger!=null) {
            choose(harmTrigger);
        }else{
            end();
        }
    }

    public void end() {
        currentProcess = Process.END;
        //Wait for the choose finished and then trigger Card effect on end process

    }

    public void choose(Carder chooser) {
        currentProcess = Process.END;
            //Choose process
            //TODO
            Card choose = null;
        //Show dialog to ask choose card owner whether send the card into abandon
        harmTrigger = null;
        end();
    }

    public void start() {
        for (Carder carder : carders) {
            carder.drawCard(Location.STACK, 5);
        }
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    private void prepareEffect() {
        Map<String, Object> para = buildPara();
        for (Carder carder : carders) {
            for (Card card : carder.getMain()) {
                if(card instanceof PrepareProcessEffect){
                    ((PrepareProcessEffect) card).invokeWhenPrepare(para);
                }
            }
        }
        beforeDraw();
    }

    private Map<String, Object> buildPara() {
        Map<String, Object> para = new HashMap<>();
        para.put("roundOwner", roundOwner);
        para.put("process", currentProcess);
        para.put("round", round);
        para.put("game", this);
        return para;
    }

    private void afterDrawEffect(List<Card> cards) {
        Map<String, Object> para = buildPara();
        para.put("draw", cards);
        for (Carder carder : carders) {
            for (Card card : carder.getMain()) {
                if(card instanceof AfterDrawEffect){
                    ((AfterDrawEffect) card).invokeAfterDraw(para);
                }
            }
        }
        main();
    }

    private void beforeDraw() {

    }

}
