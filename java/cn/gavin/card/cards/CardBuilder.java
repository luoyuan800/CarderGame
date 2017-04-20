package cn.gavin.card.cards;

import cn.gavin.card.db.DbHelper;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.TenchType;

/**
 * Created by gluo on 4/17/2017.
 */
public class CardBuilder {
    private DbHelper dbHelper = DbHelper.instance();
    public Card buildCard(String id, TenchType tenchType){
        int groupLength = id.charAt(0);
        int typeLength = id.charAt(2);

        String typeId = id.substring(5, 5+typeLength);
        Class clazz = getClass(typeId);
        Card card = null;
        if(clazz!=null) {
            try {
                card = (Card) clazz.newInstance();
                card.setId(id);
                card.setTenchType(tenchType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return card;
    }

    public Card buildCard(String id) {
        return buildCard(id, dbHelper.queryCardTT(id));
    }

    private Class getClass(String typeId){
        return dbHelper.queryClass(typeId);
    }
}
