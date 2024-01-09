package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.AlreadyScoredException;
import data.Hand;
import data.Player;

public class Game {
	
	private static Scanner _in;
	private static Player[] _players;

	public static void main(String[] args) throws IOException {
		_in = new Scanner(System.in);
		
		initializePlayers();
		
		while(!allDone()) {
			for(int i = 0; i < _players.length; i++) {
				System.out.println();
				System.out.println("Player " + (i+1) + ", you're up!");
				turn(_players[i]);
			}
		}
		
		finalScore();
	}

	private static int initializePlayers() {
		System.out.println("Welcome! How many people are playing?");
		System.out.print(":");
		int numberOfPlayers;
		try {
			numberOfPlayers = Integer.parseInt(_in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Illegal input. Number of players must be an integer.\n");
			return initializePlayers();
		}
		if(numberOfPlayers >= 2) {
			initializePlayers(numberOfPlayers);
		}else {
			System.out.println("Illegal input. Number of players must be at least 2.\n");
			return initializePlayers();
		}
		return numberOfPlayers;
	}
	
	private static void initializePlayers(int numberOfPlayers) {
		_players = new Player[numberOfPlayers];
		for(int i = 0; i < numberOfPlayers; i++) {
			_players[i] = new Player();
		}
	}
	
	private static boolean allDone() {
		for(Player player : _players) {
			if(!player.isFull()) {
				return false;
			}
		}
		return true;
	}
	
	private static void turn(Player player) {
		System.out.println();
		System.out.println(player.toString());
		Hand hand = new Hand();
		roll(hand);
		score(player, hand);
	}

	private static void roll(Hand hand) {
		for(int t = 0; t < 2; t++) {
			System.out.println();
			System.out.println("Roll " + (t+1) + ":");
			System.out.println(hand);
			System.out.println("Which dice would you like to keep? Answer in the form '124'.\nPress Enter to reroll all dice. Write 'score' to score immediately.");
			System.out.print(":");
			String input = _in.nextLine();
			if(input.equals("score")) {
				break;
			}
			ArrayList<Integer> lockedDice = parseLocked(input);
			hand.lockDice(lockedDice);
			hand.roll();
		}
		System.out.println();
		System.out.println("Final Roll:");
		System.out.println(hand);
	}
	
	private static ArrayList<Integer> parseLocked(String input){
		ArrayList<Integer> result = new ArrayList<>();
		if(input.equals("")) {
			return result;
		}
		for(int i = 0; i < input.length(); i++) {
			int value = input.charAt(i) - 48;
			if(value < 1 || value > 5) {
				System.out.println("Input must be a sequence of integers between 1 and 5. Please try again.");
				System.out.print(":");
				return parseLocked(_in.nextLine());
			}
			result.add(value - 1);
		}
		return result;
	}
	
	private static void score(Player player, Hand hand) {
		boolean success = false;
		while (!success) {
			System.out.println(
					"How would you like to score your hand? Enter 'h' for house, '1' for ones, '2' for twos etc.");
			System.out.print(":");
			String input = _in.nextLine();
			success = true;
			try {
				if (input.equals("1")) {
					player.scoreOnes(hand);
				} else if (input.equals("2")) {
					player.scoreTwos(hand);
				} else if (input.equals("3")) {
					player.scoreThrees(hand);
				} else if (input.equals("4")) {
					player.scoreFours(hand);
				} else if (input.equals("5")) {
					player.scoreFives(hand);
				} else if (input.equals("6")) {
					player.scoreSixes(hand);
				} else if (input.equals("h")) {
					player.scoreHouse(hand);
				} else {
					System.out.println("Illegal input. Enter 'h' for house, '1' for ones, '2' for twos etc.");
					success = false;
				}
			} catch (AlreadyScoredException e) {
				System.out.println("You have already scored that field. Please pick another.");
				success = false;
			}
		}
	}
	
	private static void finalScore() {
		System.out.println();
		System.out.println("Final score: ");
		System.out.println();
		int maxScore = 0;
		int winningPlayer = 0;
		for(int i = 0; i < _players.length; i++) {
			if(_players[i].total() > maxScore) {
				maxScore = _players[i].total();
				winningPlayer = i;
			}
			System.out.println("Player " + (i+1) + ": " + _players[i].total() + " points");
		}
		System.out.println();
		System.out.println("Congratulations player " + (winningPlayer+1) + ", you are the winner!");
	}

}
