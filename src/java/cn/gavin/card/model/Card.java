package cn.gavin.card.model;

import cn.gavin.card.manager.DuleManager;
import cn.gavin.card.model.Group.Group;
import cn.gavin.card.model.Group.MainArea;
import cn.gavin.card.model.carder.Carder;
import cn.gavin.card.model.effect.Effect;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public abstract class Card {
    //D = description
    private String name, level, mainD, secD, covD, id;
    private int value, cost, atk, defend, invokeLimit, totalLimit;
    private  Location currentLoc;
    private Set<Effect> immuneEffect;
    private Set<Effect> effects;
    private CardStatus status;
    private Carder owner;
    private List<Object> parameters;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append("\t").append(level).append("\n");
        sb.append("\t").append(mainD).append("\n");
        sb.append("\t").append(secD).append("\n");
        sb.append("\t").append(covD).append("\n");
        return sb.toString();
    }

    public  boolean move(Group target){
        currentLoc = target.getLocation();
        target.push(this);
        switch (currentLoc){
            case ABANDON:
                status = CardStatus.ABANDON;
                break;
        }
        return true;
    }

    public boolean move(MainArea group, int index){
        boolean isSuccess = false;
        if(status == CardStatus.COVER){
            isSuccess = group.palaceCardAsCover(this, index);
            if(isSuccess){
                status = CardStatus.COVER;
            }
        }else{
            isSuccess = group.palaceCardAsPositive(this,index);
            if(isSuccess){
                status = CardStatus.POSITIVE;
            }
        }
        if(isSuccess){
            currentLoc = group.getLocation();
        }
        return isSuccess;
    }

    public boolean destroy(){
        //Some action
        Group ababdon = DuleManager.duleManager.getGroupManager(owner).getGroup(Location.ABANDON);
        return move(ababdon);
    }

    public boolean isContainEffect(Effect e){
        if(getEffects() == null || getEffects().isEmpty()){
            return false;
        }
        return getEffects().contains(e);
    }

    public abstract boolean invoke();

}
