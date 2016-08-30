package cn.gavin.card.model;


import cn.gavin.card.exp.EmptyCard;

import java.util.Stack;

/**
 * Created by gluo on 8/29/2016.
 */
public enum Location {
    ABANDON, MAIN, VALUE, HAND, STACK, EFFECTING, TEMPLE;

    private Stack<Card> stack = new Stack<Card>();
    public void push(Card card){
        stack.push(card);
    }

    public Card pop(){
        if(stack.isEmpty()){
            return EmptyCard.emptyCard;
        }
        return stack.pop();
    }
}
