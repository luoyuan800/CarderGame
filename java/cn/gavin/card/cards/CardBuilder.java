package cn.gavin.card.cards;

import cn.gavin.card.db.DbHelper;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.TenchType;
import cn.gavin.card.utils.IDParser;

/**
 * Created by gluo on 4/17/2017.
 */
public class CardBuilder {
    private DbHelper dbHelper = DbHelper.instance();
    public Card buildCard(String id, TenchType tenchType){
        String typeId = IDParser.getType(id);
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
