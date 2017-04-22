/**
 * Copyright @Luoyuan
 * Each card type should have a card class
 * Each card instance for each card id
 * Each card should have different card id event they are same card
 * such id = 3(group length)4(type length)008(group)08(produce NO.)0013(card type)0001(card id)
 */
package cn.gavin.card.model;

import cn.gavin.card.exp.EmptyCard;
import cn.gavin.card.model.carder.Carder;
import cn.gavin.card.model.effect.CoverEffect;
import cn.gavin.card.model.group.Group;
import cn.gavin.card.model.group.MainArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Card {
    //D = description
    //Below properties define by subclass, and could not be cover.
    protected static String name;
    protected static String level;
    protected static String mainD;
    protected static String secD;
    protected static String covD;
    protected static int value;
    protected static int cost;
    protected static int atk;
    protected static int defend;
    protected static int invokeLimit;//Each game invoke count
    protected static String img;
    private static int totalLimit;//The max number in one card group
    //Below properties read from database;
    protected String id;
    protected TenchType tenchType;
    //Below properties are set in runtime
    private Group currentGroup;
    private CardStatus status;
    private Carder owner;
    private List<Object> parameters;
    private Mark mark;
    private Map<String, Object> templeProperties;//Properties can be change while running,so we will save those dynamic value into here
    private Carder controller;

    protected Card() {
        parameters = new ArrayList<>();
        templeProperties = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append("\t").append(level).append("\n");
        sb.append("\t").append(mainD).append("\n");
        sb.append("\t").append(secD).append("\n");
        sb.append("\t").append(covD).append("\n");
        return sb.toString();
    }

    public boolean move(Group target) {
        Carder owner = target.getOwner();
        Location currentLoc = currentGroup.getLocation();
        if (currentLoc == Location.VALUE) {
            Carder currentOwner = currentGroup.getOwner();
            currentOwner.setPoint(owner.getPoint() - getCost());
        }
        currentLoc = target.getLocation();
        Card card = target.push(this);
        switch (currentLoc) {
            case STACK:
                status = CardStatus.CARD_STACK;
                break;
            case VALUE:
                status = CardStatus.VALUE;
                owner.setPoint(owner.getPoint() + getCost());
                break;
            case ABANDON:
                status = CardStatus.ABANDON;
                break;
            case MAIN:
                if (card instanceof EmptyCard) {
                    status = CardStatus.ABANDON;
                    return false;
                }
                break;
            case HAND:
                if (card instanceof EmptyCard) {
                    status = CardStatus.ABANDON;
                    return false;
                }
                break;
        }
        return true;
    }

    public boolean move(MainArea group, int index) {
        boolean isSuccess = false;
        if (status == CardStatus.COVER) {
            isSuccess = group.palaceCardAsCover(this, index);
            if (isSuccess) {
                status = CardStatus.COVER;
            }
        } else {
            isSuccess = group.palaceCardAsPositive(this, index);
            if (isSuccess) {
                status = CardStatus.POSITIVE;
            }
        }
        if (isSuccess) {
            currentGroup = group;
        }
        return isSuccess;
    }

    public boolean destroy() {
        //Some action
        /*Group ababdon = DuleManager.duleManager.getGroupManager(owner).getGroup(Location.ABANDON);
        return move(ababdon);*/
        return false;
    }

    public boolean isMainInvokeAble(){
        return true;
    }

    public boolean isSecondInvokeAble(){
        return true;
    }

    public boolean invokeAsMain(Map<String, Object> parameters){
        if(isMainInvokeAble()) {
            getController().setPoint(getController().getPoint() - getCost());
            return getController().getMain().placeCardAsPositive(this);
        }else{
            return false;
        }
    }

    public abstract boolean invokeAsSecond(Map<String, Object> parameters);

    public void turn(Map<String, Object> para) {
        owner.setPoint(owner.getPoint() - getCost());
        owner.setAtk(owner.getAtk() + getAtk());
        owner.setDef(owner.getDef() + getDefend());
        if (this instanceof CoverEffect) {
            ((CoverEffect)this).invokeWhileTurn(para);
        }
        setStatus(CardStatus.POSITIVE);
    }

    public void cover() {
        owner.setAtk(owner.getAtk() - getAtk());
        owner.setDef(owner.getDef() - getDefend());
        setStatus(CardStatus.COVER);
    }

    public String getName() {
        return getTemplePropertiesWithDefault("name", name);
    }

    public void setName(String name) {
        templeProperties.put("name", name);
    }

    public String getLevel() {
        return getTemplePropertiesWithDefault("level", level);
    }

    public void setLevel(String level) {
        templeProperties.put("level", level);
    }

    public String getMainD() {
        return getTemplePropertiesWithDefault("mainD", mainD);
    }

    public void setMainD(String mainD) {
        templeProperties.put("mainD", mainD);
    }

    public String getSecD() {
        return getTemplePropertiesWithDefault("secD", secD);
    }

    public void setSecD(String secD) {
        templeProperties.put("secD", secD);
    }

    public String getCovD() {
        return getTemplePropertiesWithDefault("covD", covD);
    }

    public void setCovD(String covD) {
        templeProperties.put("covD", covD);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return getTemplePropertiesWithDefault("value", value);

    }

    public void setValue(int value) {
        templeProperties.put("value", value);
    }

    public int getCost() {
        return getTemplePropertiesWithDefault("cost", cost);
    }

    public void setCost(int cost) {
        templeProperties.put("cost", cost);
    }

    public int getAtk() {
        return getTemplePropertiesWithDefault("atk", atk);
    }

    public void setAtk(int atk) {
        templeProperties.put("atk", atk);
    }

    public int getDefend() {
        return getTemplePropertiesWithDefault("defend", defend);
    }

    public void setDefend(int defend) {
        templeProperties.put("defend", defend);
    }

    public int getInvokeLimit() {
        return invokeLimit;
    }

    public int getTotalLimit() {
        return totalLimit;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Carder getOwner() {
        return owner;
    }

    public void setOwner(Carder owner) {
        this.owner = owner;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public TenchType getTenchType() {
        return tenchType;
    }

    public void setTenchType(TenchType tt) {
        this.tenchType = tt;
    }

    public Map<String, Object> getTempleProperties() {
        return templeProperties;
    }

    public void setTempleProperties(Map<String, Object> templeProperties) {
        this.templeProperties = templeProperties;
    }

    public <T> T getTemplePropertiesWithDefault(String pro, T def) {
        Object value = templeProperties.get(pro);
        if (value != null) {
            return ((T) value);
        }
        return def;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Group getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }

    public Carder getController() {
        return controller;
    }

    public void setController(Carder controller) {
        this.controller = controller;
    }
}
