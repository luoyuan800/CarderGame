package cn.gavin.card.manager;

import cn.gavin.card.exp.EmptyCard;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.Group.Group;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Process;
import cn.gavin.card.model.carder.Carder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gluo on 8/29/2016.
 */
public class DuleManager {
    public static final DuleManager duleManager = new DuleManager();
    private Map<Carder, GroupManager> groupManagerMap;
    @Getter
    @Setter
    private Carder actioner;
    private Process process;

    @Getter private ArrayList<Card> invokeOnPrepare = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> invokeOnDraw = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> invokeOnMain = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> invokeOnChoose = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> invokeOnHarm = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> invokeOnEnd = new ArrayList<Card>(6);
    @Getter private ArrayList<Card> cardsChoose = new ArrayList<Card>(6);
    public GroupManager getGroupManager(Carder carder){
        return groupManagerMap.get(carder);
    }

    public synchronized void addGroupManager(Carder carder , GroupManager groupManager){
        if(groupManagerMap == null){
            groupManagerMap = new HashMap<Carder, GroupManager>(2);
        }
        groupManagerMap.put(carder, groupManager);
    }

    public void start(){
        for(GroupManager groupManager : groupManagerMap.values()){
            groupManager.getGroup(Location.STACK).shuffle();
        }
    }

    public void prepare(Carder carder){
        process = Process.PREPARE;
        for(Card card : invokeOnPrepare){
            card.invoke();
        }
    }

    public void draw(Carder carder){
        process = Process.DRAW;
        for(Card card : invokeOnDraw){
            card.invoke();
        }
        Group stack = groupManagerMap.get(carder).getGroup(Location.STACK);
        if(stack.size() == 0){
            lost(carder);
        }else {
            Card card = stack.pop();
            if(groupManagerMap.get(carder).getGroup(Location.HAND).push(card) == EmptyCard.emptyCard){

            }
        }

    }

    public void main(Carder carder){
        process = Process.MAIN;
        for(Card card : invokeOnPrepare){
            card.invoke();
        }
    }

    public void Choose(Carder carder){

    }

    public void harm(Carder carder, Carder target){
        int harm = carder.getAtk() - target.getDef();
        if(harm > 0){
            target.setPoint(target.getPoint() - harm);
        }else{
            carder.setPoint(carder.getPoint() + harm);
        }
    }

    public void end(Carder carder, Carder target){

    }

    public void lost(Carder carder){
        carder.setPoint(0);
    }
}
