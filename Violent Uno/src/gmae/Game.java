package gmae;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	private int currentPlayer;
	private String[] playerIDs;
	
	private Deck deck;
	//list of all the players hands which are lists of card
	private ArrayList<ArrayList<Card>> playerHand;
	
	private ArrayList<Card> stockpile;
	
	Card.Color validColor;
	Card.Value validValue;
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
		//fills hands a
		for(int i = 0; i<pIDs.length; i++) {
			ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
			//contains all hands
			playerHand.add(hand);
		}
	}
	public void start(Game game) {
		Card card = deck.drawCard();
		validColor = card.getColor();
		validValue = card.getValue();
		
		if(card.getValue() == Card.Value.Wild) {
			start(game);
		}
		if(card.getValue() == Card.Value.WildDrawFour||card.getValue()==Card.Value.DrawTwo) {
			start(game);
		}
		stockpile.add(card);
		
	}

	public boolean isGameOver() {
		for (String player : this.playerIDs) {
			if (hasEmptyHand(player)) {
				return true;
			}
		}
		return false;
	}
	public String getCurrentPlayer() {
		return this.playerIDs[this.currentPlayer];
	}
	public String getPreviusPlayer(int i) {
		int index = this.currentPlayer-1;
		if(index == -1) {
			index = playerIDs.length-1;
		}
		return this.playerIDs[index];
	}
	public String[] getPlayers() {
		return playerIDs;
	}
	public ArrayList<Card> getPlayersHand(String pid){
		int index = Arrays.asList(playerIDs).indexOf(pid);
		return playerHand.get(index);
	}
	public int getPlayersHandSize(String pid) {
		return getPlayersHand(pid).size();
	}
	public Card getPlayerCard(String pid, int choice) {
		ArrayList<Card> hand = getPlayersHand(pid);
		return hand.get(choice);
	}
	public boolean hasEmptyHand(String pid) {
		return getPlayersHand(pid).isEmpty();
	}
	public boolean validCardPlay(Card card) {
		return card.getColor() == validColor || card.getValue() == validValue;
	}
	public void submitDraw(String pid) {
		if(deck.isEmpty()) {
			deck.replaceDeckWith(stockpile);
			deck.shuffle();
		}
		getPlayersHand(pid).add(deck.drawCard());
		if (direction == false) {
			currentPlayer = (currentPlayer +1 )% playerIDs.length;
		}else if (direction == true) {
			currentPlayer = (currentPlayer -1)% playerIDs.length;
			if (currentPlayer == -1) {
				currentPlayer = playerIDs.length-1;
			}
		}
	}
	public void setCardColor(Card.Color color) {
		validColor = color;
	}
	public void subitPlayerCard(String pid, Card card, Card.Color declairedColor) {
		if(validCardPlay(card)) {
			playerHand.remove(card);//check this, might error
			validColor = card.getColor();
			validValue = card.getValue();
			stockpile.add(card);
			if (direction == false) {
				currentPlayer= (currentPlayer+1)%playerIDs.length;
			}else {
				currentPlayer= (currentPlayer-1)%playerIDs.length;
				if (currentPlayer==-1) {
					currentPlayer = playerIDs.length-1;
				}
			}
		}else if(Card.Color.Wild == card.getColor()|| Card.Value.WildDrawFour == card.getValue()) {
			playerHand.remove(card);//check this, might error
			validColor = declairedColor;
			validValue = card.getValue();
			stockpile.add(card);
			if (direction == false) {
				currentPlayer= (currentPlayer+1)%playerIDs.length;
			}else {
				currentPlayer= (currentPlayer-1)%playerIDs.length;
				if (currentPlayer==-1) {
					currentPlayer = playerIDs.length-1;
				}
			}
		}else {
			System.out.println("Invalid play");
		}
		if (hasEmptyHand(this.playerIDs[currentPlayer])) {
			System.out.println("Player "+playerIDs[currentPlayer]+" wins");
		}
		if (card.getValue()==Card.Value.DrawTwo) {
			pid = playerIDs[currentPlayer]; 
			getPlayersHand(pid).add(deck.drawCard());
			getPlayersHand(pid).add(deck.drawCard());
			System.out.println(pid+" got two cards");
		}
		if (card.getValue()==Card.Value.WildDrawFour) {
			pid = playerIDs[currentPlayer];
			getPlayersHand(pid).add(deck.drawCard());
			getPlayersHand(pid).add(deck.drawCard());
			getPlayersHand(pid).add(deck.drawCard());
			getPlayersHand(pid).add(deck.drawCard());
			System.out.println(pid+" got two cards");
		}
		if (card.getValue()==Card.Value.Skip) {
			System.out.println(pid+"got skipped");
			if (direction == false) {
				currentPlayer= (currentPlayer+1)%playerIDs.length;
			}else {
				currentPlayer= (currentPlayer-1)%playerIDs.length;
				if (currentPlayer==-1) {
					currentPlayer = playerIDs.length-1;
				}
			}
		}
		if (card.getValue()==Card.Value.Reverse) {
			direction ^= true;
			if (direction == false) {
				currentPlayer= (currentPlayer+2)%playerIDs.length;
			}else {
				currentPlayer= (currentPlayer-2)%playerIDs.length;
				if (currentPlayer==-1) {
					currentPlayer = playerIDs.length-1;
				}
				if (currentPlayer==-2) {
					currentPlayer = playerIDs.length-2;
				}
			}
		}
	}
}
