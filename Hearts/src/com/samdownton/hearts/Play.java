package com.samdownton.hearts;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
	private static final Scanner in = new Scanner(System.in);
	private int lastLoser;

	public void playGame() {

		System.out.print("Please enter your name: ");
		
		Player human = new Player(in.next());
		Player player2 = new Player("Scarlet");
		Player player3 = new Player("Jane");
		Player player4 = new Player("Paul");
		System.out.println("Players are: " + human.getName() + ", " + player2.getName() + ", " + player3.getName()
				+ ", " + player4.getName() + ".");
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(human);
		lastLoser = 0;
		players.add(player2);
		players.add(player3);
		players.add(player4);

		PackOfCards pack = new PackOfCards();
		pack.shuffle();

		for (Player player : players) {
			pack.deal(player, 13);
			player.sort();
		}

		while (players.get(0).getHand().size() > 0 && players.get(1).getHand().size() > 0 && players.get(2).getHand().size() > 0 && players.get(3).getHand().size() > 0){
			showPlayerCards(human);
			playRound(players);
		}
		
		for (Player player : players){
			int score = player.calculateScore();
			System.out.println(player.getName() + "'s score is: " + score);
		}
		
		findWinner(players);
		
	}

	private void showPlayerCards(Player player) {
		System.out.println("Your cards are: ");
		System.out.println(Card.printCards(player.getHand()));
		String str = "[";
		for (int i = 0; i < player.getHand().size(); i++) {
			str = str + String.format("%3s", i) + ", ";
		}
		str = str + "]";
		System.out.print(str);
	}

	private void playRound(ArrayList<Player> players) {
		
		ArrayList<Card> onTable = new ArrayList<>();
		if (lastLoser == 0){
			System.out.println();
			System.out.print("Select card to play: ");
			int cardToPlay = in.nextInt();
			onTable.add(players.get(0).getHand().get(cardToPlay));
			players.get(0).getHand().remove(cardToPlay);
			for (int i = 1; i < players.size(); i++) {
				onTable.add(players.get(i).getHand().get((players.get(i).getHandSize()) - 1));
				players.get(i).getHand().remove(players.get(i).getHandSize() - 1);
			}
			System.out.println(Card.printCards(onTable));
		}
		else {
			for (int i = lastLoser; i < 4; i++){
				onTable.add(players.get(i).getHand().get((players.get(i).getHandSize())-1));
				players.get(i).getHand().remove(players.get(i).getHandSize() - 1);
			}
			
			System.out.println();
			System.out.println("Cards played so far" + Card.printCards(onTable));
			System.out.println();
			System.out.println("Select card to play: ");
			int cardToPlay = in.nextInt();
			onTable.add(0, players.get(0).getHand().get(cardToPlay));
			players.get(0).getHand().remove(cardToPlay);
			
			for (int i = 1; i < lastLoser; i++){
				onTable.add(i, players.get(i).getHand().get((players.get(i).getHandSize())-1));
				players.get(i).getHand().remove(players.get(i).getHandSize() - 1);
			}
			
			System.out.println();
			System.out.println( Card.printCards(onTable));
		}
		
		String suit = onTable.get(lastLoser).getSuit();
		int max = 0;
		int maxCard = 0;
		for (int i = 0; i < onTable.size(); i++) {
			if (onTable.get(i).getSuit().equals(suit)) {
				if (max < onTable.get(i).getFaceValue()) {
					max = onTable.get(i).getFaceValue();
					maxCard = i;
				}
			}
		}
		
		lastLoser = maxCard;
		System.out.println(players.get(maxCard).getName() + " looses this round!");
		for (Card card : onTable) {
			players.get(maxCard).addToReceived(card);
		}
		

	}
	private void findWinner(ArrayList<Player> players){
		int low = 50;
		Player winner = players.get(0);
		for (Player player : players){
			if (player.calculateScore() < low){
				low = player.calculateScore();
				winner = player;
			}
		}
		System.out.println("The Winner is: " + winner.getName());
	}

}
