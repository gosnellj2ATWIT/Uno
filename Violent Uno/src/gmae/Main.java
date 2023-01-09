package gmae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	Scanner keyboard = new Scanner(System.in);
	String[] PLayers = {"josh", "joe", "bob"};
	Game mygame=new Game(PLayers);
	mygame.start(mygame);
	int thingcolor;
	int thing;
	Card.Color color = null;
	
	while(mygame.isGameOver()==false) {
		System.out.printf("play card: %s %s%n", mygame.validColor, mygame.validValue);
		System.out.printf("%s is up%n", mygame.getCurrentPlayer());
		ArrayList<Card> hand = mygame.getPlayersHand(mygame.getCurrentPlayer());
		System.out.printf("Player hand: %n");
		for(int i=0; i< mygame.getPlayersHand(mygame.getCurrentPlayer()).size();i++ ) {
			System.out.printf("|%s %s (%d)|", hand.get(i).getColor(), hand.get(i).getValue(), i);
		}
		
		System.out.printf("%nput a number a card to play or -1 to draw%n");
		thing = keyboard.nextInt();
		if (thing <0 ) {
			mygame.submitDraw(mygame.getCurrentPlayer());
		}else if(Card.Color.Wild==hand.get(thing).getColor()) {
			System.out.printf("Give us a color then: 1=Red, 2=Blue, 3=Green, 4=Yellow%n");
			thingcolor = keyboard.nextInt();
			if(thingcolor==1) {
				color = Card.Color.Red;
			}else if(thingcolor==2) {
				color = Card.Color.Blue;
			}else if(thingcolor==3) {
				color = Card.Color.Green;
			}else if(thingcolor==4) {
				color = Card.Color.Yellow;
			}
			mygame.subitPlayerCard(mygame.getCurrentPlayer(), hand.get(thing), color, thing);
		}else {
			color = hand.get(thing).getColor();
			mygame.subitPlayerCard(mygame.getCurrentPlayer(), hand.get(thing), color, thing);
		}
	

		

		//System.out.printf("%s is up", mygame.getCurrentPlayer());
	}

}}
