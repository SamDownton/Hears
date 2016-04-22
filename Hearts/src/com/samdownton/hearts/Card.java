package com.samdownton.hearts;

import java.util.ArrayList;

public class Card {
	private int value;
	public Card (int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public String getFaceValueString(){
		int number =  (value % 13) + 1;
		if (number == 1){
			return "A";
		} else if (number == 11){
			return "J";
		} else if (number == 12){
			return "Q";
		} else if (number == 13){
			return "K";
		} else {
			return "" + number;
		}
		
	}
	
	public int getFaceValue(){
		return value % 13 +1;
	}
	
	public String getSuit(){
		String suit;
		if(value < 13){
			suit = "H";
		} else if (value < 26){
			suit = "D";
		} else if (value < 39){
			suit = "S";
		} else {
			suit = "C";
		}
		return suit;
	}
	public String getCard(){
		String suit = this.getSuit();
		String value = this.getFaceValueString();
		return  value + suit;
	}
	public static String printCards(ArrayList<Card> cards){
		String str = "[";
		for (Card card : cards){
			String details = card.getCard();
			str = str + String.format("%3s", details) + ", ";
		}
		str = str + "]";
		return str;
	}
	

}
