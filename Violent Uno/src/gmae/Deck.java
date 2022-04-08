package gmae;

import java.util.ArrayList;
import java.util.Random;

import gmae.Card.Value;

public class Deck {
	private Card[] cards;
	private int cardsInDeck;
	
	public Deck() {
		cards = new Card[108];
	}
	public void reset() {
		Card.Color[] colors = Card.Color.values();
		cardsInDeck = 0;
		for (int i = 0; i<colors.length-1; i++) {
			Card.Color color = colors[i];
			//1 card for the zeros
			cards[cardsInDeck++] = new Card(color, Card.Value.getValue(0));
			for (int j = 1; j<10; j++) {
				//2 cards for 1-9
				cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
				cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
			}
			//special cards
			Card.Value[] values = new Card.Value[] {Card.Value.DrawTwo, Card.Value.Skip, Card.Value.Reverse};
			for(Card.Value value: values) {
				cards[cardsInDeck++] = new Card(color, value);
				cards[cardsInDeck++] = new Card(color, value);
			}
			
		}
		//wild cards
		Card.Value[] values = new Card.Value[] {Card.Value.Wild, Card.Value.WildDrawFour};
		for(Card.Value value: values) {
			for(int i = 0; i<4; i++) {
				cards[cardsInDeck++] = new Card(Card.Color.Wild, value);
			}
		}
	}
	//replaces the deck with an array of cards
	public void replaceDeckWith(ArrayList<Card> cards) {
		this.cards = cards.toArray(new Card[cards.size()]);
		this.cardsInDeck = this.cards.length;
	}
	//gee I dont know
	public boolean isEmpty() {
		return cardsInDeck ==0;
	}
	public void shuffle() {
		int n = cards.length;
		Random random = new Random();
		for(int i = 0; i<cards.length; i++) {
			int randomValue = i + random.nextInt(n-i); //random numver
			Card randomCard = cards[randomValue]; //hmm [random card[
			cards[randomValue] = cards[i]; //swap
			cards[i]= randomCard;
		}
	}
	//draw one
	public Card drawCard() throws IllegalArgumentException{
		if(isEmpty()) {
			throw new IllegalArgumentException("No more cards to draw");
		}
		return cards[--cardsInDeck];
	}
	//draw more then one
	public Card[] drawCard(int n) {
		if(n<0) {
			throw new IllegalArgumentException("Only positive draws");
		}
		if(n>cardsInDeck) {
			throw new IllegalArgumentException("not enough cards, only: "+cardsInDeck+" cards in the deck");
		}
		//makes an array of cards on the top ro return a
		Card[] drawn = new Card[n];
		
		for(int i = 0; i<n; i++) {
			drawn[i] = cards[--cardsInDeck];
		}
		return drawn;
		}
	}

