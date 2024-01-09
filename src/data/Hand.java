package data;

import java.util.HashMap;
import java.util.List;

public class Hand {
	
	private static final int HAND_SIZE = 5;
	
	private Die[] _dice;
	
	public Hand() {
		_dice = new Die[HAND_SIZE];
		for (int i = 0; i < HAND_SIZE; i++) {
			_dice[i] = new Die();
		}
	}
	
	//This constructor is only used for testing purposes
	public Hand(int[] values) {
		_dice = new Die[HAND_SIZE];
		for (int i = 0; i < HAND_SIZE; i++) {
			_dice[i] = new Die(values[i]);
		}
	}
	
	public void roll() {
		for(int i = 0; i < HAND_SIZE; i++) {
			if(!_dice[i].isLocked()) {
				_dice[i].roll();
			}
		}
	}
	
	public void lockDice(List<Integer> lockedDice) {
		unlockAll();
		for(int index : lockedDice) {
			_dice[index].setLocked(true);
		}
	}
	
	private void unlockAll() {
		for(int i = 0; i < HAND_SIZE; i++) {
			_dice[0].setLocked(false);
		}
	}
	
	public Die[] getDice() {
		return _dice;
	}
	
	public boolean isHouse() {
		HashMap<Integer, Integer> amounts = new HashMap<>();
		for(Die die : _dice) {
			int value = die.getValue();
			if(amounts.containsKey(value)) {
				amounts.put(value, amounts.get(value) + 1);
			} else {
				amounts.put(value, 1);
			}
		}
		return amounts.containsValue(2) && amounts.containsValue(3);
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("  1  ");
		bld.append("   ");
		bld.append("  2  ");
		bld.append("   ");
		bld.append("  3  ");
		bld.append("   ");
		bld.append("  4  ");
		bld.append("   ");
		bld.append("  5  ");
		bld.append("   ");
		bld.append("\n");
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < HAND_SIZE; j++) {
				bld.append(_dice[j].visual()[i]);
				bld.append("   ");
			}
			bld.append("\n");
		}
		return bld.toString();
	}
	
}
