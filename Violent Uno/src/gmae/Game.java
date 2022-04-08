package gmae;

import java.sql.Array;
import java.util.ArrayList;

public class Game {
	private int currentPlayer;
	private String[] playerIDs;
	
	private Deck deck;
	//list of all the players hands which are lists of card
	private ArrayList<ArrayList<Card>> playerHand;
	
	private ArrayList<Card> stockpile;
	
	private Card.Color validColor;
	private Card.Value validValue;
	//reverse card direction
	boolean direction;
	
	public Game(String[] pIDs) {
		deck = new Deck();
		deck.shuffle();
		stockpile = new ArrayList<Card>();
		playerIDs = pIDs;
		currentPlayer = 0;
		direction = false;
		
		playerHand = new ArrayList<ArrayList<Card>>();
		//fills hands
		for(int i = 0; i<pIDs.length; i++) {
			ArrayList<Card> hand = new ArrayList<Card>(Array.asList(deck.drawCard(7)));
			playerHand.add(hand);
		}
	}
	
	
	

}
