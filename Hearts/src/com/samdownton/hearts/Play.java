package com.samdownton.hearts;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
	private static final Scanner in = new Scanner(System.in);
	private int lastWinner;

	public void playGame() {

		System.out.print("Please enter player one name: ");
		Player player1 = new Player(in.next());
		System.out.print("Please enter player two name: ");
		Player player2 = new Player(in.next());
		System.out.print("Please enter player three name: ");
		Player player3 = new Player(in.next());
		System.out.print("Please enter player four name: ");
		Player player4 = new Player(in.next());
		System.out.println("Players are: " + player1.getName() + ", " + player2.getName() + ", " + player3.getName()
				+ ", " + player4.getName() + ".");
		ArrayList<Player> players = new ArrayList<>();
		players.add(player1);
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
			showPlayerCards(player1);
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
		System.out.println("Select card to play: ");
		int cardToPlay = in.nextInt();
		ArrayList<Card> onTable = new ArrayList<>();
		onTable.add(players.get(0).getHand().get(cardToPlay));
		players.get(0).getHand().remove(cardToPlay);
		for (int i = 1; i < players.size(); i++) {
			onTable.add(players.get(i).getHand().get((players.get(i).getHandSize()) - 1));
			players.get(i).getHand().remove(players.get(i).getHandSize() - 1);
		}
		System.out.println(Card.printCards(onTable));

		String suit = onTable.get(0).getSuit();
		int max = onTable.get(0).getFaceValue();
		int maxCard = 0;
		for (int i = 1; i < onTable.size(); i++) {
			if (onTable.get(i).getSuit().equals(suit)) {
				if (max < onTable.get(i).getFaceValue()) {
					max = onTable.get(i).getFaceValue();
					maxCard = i;
				}
			}
		}
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
