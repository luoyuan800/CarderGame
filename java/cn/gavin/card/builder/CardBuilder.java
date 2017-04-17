package cn.gavin.card.builder;

import cn.gavin.card.model.Card;
import cn.gavin.card.model.Group.TenchType;

/**
 * Created by gluo on 4/17/2017.
 */
public class CardBuilder {
    public Card buildCard(String id, TenchType tenchType){
        int typeLength = id.charAt(4);
        String typeId = id.substring(5, 5+typeLength);
        Class clazz = getClass(typeId);
        Card card = null;
        try {
            card = (Card)clazz.newInstance();
            card.setId(id);
            card.setTenchType(tenchType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card;
    }

    private Class getClass(String typeId){
        return null;
    }
}
