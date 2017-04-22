package cn.luo.yuan.carder;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.CardStatus;
import cn.gavin.card.model.carder.Carder;
import cn.gavin.card.model.effect.AfterDrawEffect;
import cn.gavin.card.model.effect.CoverEffect;

import java.util.List;
import java.util.Map;

public class Assassinator extends Card implements AfterDrawEffect,CoverEffect {

    public Assassinator(){
        name = "暗杀者";
        level = "3";
        value = 120;
        cost = 120;
        atk = 170;
        defend = 0;

    }
    @Override
    public boolean invokeAsMain(Map<String, Object> parameters) {
        int cost = getCost();
        Carder other = (Carder) parameters.get("other");
        if (other != null) {
            other.setPoint(other.getPoint() - cost);
        } else {
            getController().setPoint(getController().getPoint() - cost);
        }
        move(getController().getMain());
        return false;
    }

    @Override
    public boolean invokeAsSecond(Map<String, Object> parameters) {
        return false;
    }

    @Override
    public void invokeAfterDraw(Map<String, Object> para) {
        List<Card> draw = (List<Card>) para.get("draw");
        Carder drawer = (Carder) para.get("drawer");
        if(draw!=null){
            for(Card card : draw){
                drawer.setPoint(drawer.getPoint() - card.getAtk());
            }
        }
    }

    public boolean isSecondInvokeAble(Map<String, Object> para){
        Carder target = (Carder) para.get("target");
        for(Card card : target.getMain()){
            if(card.getStatus() == CardStatus.COVER){
                return true;
            }
        }
        return false;
    }

    @Override
    public void invokeWhileTurn(Map<String, Object> para) {
        //TODO Show choose view
        Card choose = null;
        choose.destroy();
        //TODO Show conform dialog
        boolean conform = true;
        if(conform){
            getController().setPoint(getController().getPoint() - 90);
            Carder carder = (Carder)para.get("target");
            carder.getStack().pop().destroy();
        }
    }
}
