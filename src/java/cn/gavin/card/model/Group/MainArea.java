package cn.gavin.card.model.Group;

import cn.gavin.card.exp.EmptyCard;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.CardStatus;
import cn.gavin.card.model.Location;
import cn.gavin.card.model.Mark;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gluo on 8/29/2016.
 */
@Data
public class MainArea implements Group{
    private static final Location location = Location.MAIN;
    private ArrayList<Card> first = new ArrayList<Card>(2);
    private ArrayList<Card> second = new ArrayList<Card>(2);
    private ArrayList<Card> third = new ArrayList<Card>(2);
    private ArrayList<Card> fourth = new ArrayList<Card>(2);
    private ArrayList<Card> fifth = new ArrayList<Card>(2);
    private ArrayList<Card> sixth = new ArrayList<Card>(2);

    public boolean placeCardAsPositive(Card card){
        if(placeCardAsCover(card)){
            card.setStatus(CardStatus.POSITIVE);
            card.turn();
            return true;
        }else{
            return false;
        }
    }

    public boolean placeCardAsCover(Card card){
        if(first.isEmpty()){
            first.add(card);
        }else if(second.isEmpty()){
            second.add(card);
        }else if(third.isEmpty()){
            third.add(card);
        }else if(fourth.isEmpty()){
            fourth.add(card);
        }else if(fifth.isEmpty()){
            fifth.add(card);
        }else if(sixth.isEmpty()){
            sixth.add(card);
        }else{
            return false;
        }
        card.setStatus(CardStatus.COVER);
        return true;
    }

    public boolean palaceCardAsPositive(Card card, int position) {
        if(palaceCardAsCover(card, position)){
            card.setStatus(CardStatus.POSITIVE);
            card.turn();
            return true;
        }else {
            return false;
        }
    }
    public boolean palaceCardAsCover(Card card, int position){
        ArrayList<Card> place = null;
        switch (position){
            case 1:
                place = first;
                break;
            case 2:
                place = second;
                break;
            case 3:
                place = third;
                break;
            case 4:
                place = fourth;
                break;
            case 5:
                place = fifth;
                break;
            case 6:
                place = sixth;
                break;
        }
        if(place!=null && place.isEmpty()){
            place.add(card);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Card push(Card card) {
        boolean success = false;
        if(card.getStatus() == CardStatus.COVER){
            success = placeCardAsCover(card);
        }else{
            success = placeCardAsPositive(card);
        }
        return success ? card : EmptyCard.emptyCard;
    }

    @Override
    public void shuffle() {
        //Do nothing
    }

    @Override
    public List<Card> getMark(Mark mark) {
        ArrayList<Card> cards = new ArrayList<Card>(6);
        if(!first.isEmpty() && first.get(0).getMark() == mark){
            cards.add(first.get(0));
        }
        if(!second.isEmpty() && second.get(0).getMark() == mark){
            cards.add(second.get(0));
        }
        if(!third.isEmpty() && third.get(0).getMark() == mark){
            cards.add(third.get(0));
        }
        if(!fourth.isEmpty() && fourth.get(0).getMark() == mark){
            cards.add(fourth.get(0));
        }
        if(!fifth.isEmpty() && fifth.get(0).getMark() == mark){
            cards.add(fifth.get(0));
        }
        if(!sixth.isEmpty() && sixth.get(0).getMark() == mark){
            cards.add(sixth.get(0));
        }
        return cards;
    }
}
