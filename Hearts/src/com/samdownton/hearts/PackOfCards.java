package com.samdownton.hearts;

import java.util.ArrayList;
import java.util.Random;

public class PackOfCards {

	private ArrayList<Card> pack = new ArrayList<>();

	public PackOfCards() {
		for (int i = 1; i < 53; i++) {
			this.pack.add(new Card(i));
		}
	}

	public ArrayList<Card> getPack() {
		return pack;
	}

	public void shuffle() {
		Random r = new Random();
		int i = 0;
		while (i < pack.size()) {
			int rand = r.nextInt(pack.size());
			if (rand != i) {
				Card temp = pack.get(rand);
				pack.set(rand, pack.get(i));
				pack.set(i, temp);
			}
			i++;
		}
	}

	public int getLength() {
		return pack.size();
	}

	public void deal(Player player, int numCards) {
		for (int i = 0; i < numCards; i++) {
			Card cardNum = pack.get(0);
			player.addToHand(cardNum);
			pack.remove(0);
		}
	}

	public Card getCard(int index) {
		return pack.get(index);
	}

}
