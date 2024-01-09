package test;

import java.util.ArrayList;

import data.AlreadyScoredException;
import data.Hand;
import data.Player;

public class TestSuite {

	private static final int[] NON_HOUSE_VALUES = new int[] {1, 2, 3, 3, 6};
	private static final int[] HOUSE_VALUES = new int[] {2, 2, 3, 3, 3};
	

	public static void main(String[] args) {
		
		int success = 0;
		int total = 0;
		
		if(lockTest()) {
			success++;
		} else {
			System.out.println("lockTest failed");
		}
		total++;
		
		if(houseTest()) {
			success++;
		} else {
			System.out.println("houseTest failed");
		}
		total++;
		
		if(scoringTwiceTest()) {
			success++;
		} else {
			System.out.println("scoringTwiceTest failed");
		}
		total++;
		
		if(pointTest()) {
			success++;
		} else {
			System.out.println("pointTest failed");
		}
		total++;
		
		System.out.println(success + "/" + total + " tests succeeded");
	}
	
	private static boolean lockTest() {
		Hand hand = new Hand();
		ArrayList<Integer> locked = new ArrayList<>();
		locked.add(2);
		locked.add(4);
		ArrayList<ArrayList<Integer>> diceRecords = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			diceRecords.add(new ArrayList<>());
		}
		hand.lockDice(locked);
		for(int i = 0; i < 10000; i++) {
			hand.roll();
			for(int j = 0; j < 5; j++) {
				if(!diceRecords.get(j).contains(hand.getDice()[j].getValue())) {
					diceRecords.get(j).add(hand.getDice()[j].getValue());
				}
			}
		}
		return diceRecords.get(0).size() == 6 && diceRecords.get(1).size() == 6 && diceRecords.get(2).size() == 1
				&& diceRecords.get(3).size() == 6 && diceRecords.get(4).size() == 1;
	}
	
	private static boolean houseTest() {
		Hand nonHouseHand = new Hand(NON_HOUSE_VALUES);
		Hand houseHand = new Hand(HOUSE_VALUES);
		return !nonHouseHand.isHouse() && houseHand.isHouse();
	}
	
	private static boolean scoringTwiceTest() {
		Player player = new Player();
		Hand hand = new Hand();
		try {
			player.scoreOnes(hand);
		} catch (AlreadyScoredException e) {
			return false;
		}
		try {
			player.scoreOnes(hand);
		} catch (AlreadyScoredException e) {
			return true;
		}
		return false;
	}
	
	private static boolean pointTest() {
		Player player = new Player();
		Hand hand = new Hand(NON_HOUSE_VALUES);
		try {
			player.scoreOnes(hand);
			player.scoreThrees(hand);
			player.scoreFives(hand);
		} catch (AlreadyScoredException e) {
			return false;
		}
		return player.total() == 7;
	}
	
}
