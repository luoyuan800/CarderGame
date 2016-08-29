
import java.model.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class CardStack {
	LinkedList<Card> cards;
	public Card draw(){
		return cards.poll();
	}
	public CardStack(List<Card> cards){
		this.cards = new LinkedList<Card>(cards);
	}
	public void shuffle(){
		Random r = new Random();
		for(int i =0;i<cards.size();i++){
			int j = r.nextInt(cards.size());
			Card c1 = cards.get(i);
			Card c2 =cards.get(j);
			cards.set(j, c1);
			cards.set(i,c2);
		}
	}
}
