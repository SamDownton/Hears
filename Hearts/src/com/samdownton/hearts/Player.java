package com.samdownton.hearts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
	
	private ArrayList<Card> hand;
	private ArrayList<Card> received;
	private String name;
	private int score;
	
	public Player(String name){
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.received = new ArrayList<Card>();
	}
	
	public void addToHand(Card cardNumber){
		hand.add(cardNumber);
	}
	
	public String printHand(){
		return hand.toString();
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public String getName(){
		return name;
	}
	
	public void sort(){
		Collections.sort(hand, new Comparator<Card>(){
			public int compare(Card c1, Card c2){
				return c1.getValue() - c2.getValue();
			}
		});
	}
	
	public int getHandSize(){
		return hand.size();
	}
	
	public void addToReceived(Card card){
		received.add(card);
	}
	
	public int calculateScore(){
		for (Card card : received){
			if (card.getSuit().equals("H")){
				score++;
			}
			if (card.getSuit().equals("S") && card.getFaceValue() == 12){
				score = score + 13;
			}
		}
		return score;
	}
		
}
