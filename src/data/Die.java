package data;

import java.util.Random;

public class Die {
	
	private static Random _random = new Random();
	
	private int _value;
	private boolean _locked;
	
	public Die() {
		_locked = false;
		roll();
	}
	
	//This constructor is only used for testing purposes
	public Die(int value) {
		_locked = false;
		_value = value;
	}
	
	public void roll() {
		_value = _random.nextInt(6) + 1;
	}
	
	public int getValue() {
		return _value;
	}
	
	public void setLocked(boolean locked) {
		_locked = locked;
	}
	
	public boolean isLocked() {
		return _locked;
	}
	
	public String[] visual() {
		String[] map = new String[5];
		map[0] = "-----";
		if(_value == 1) {
			map[1] = "|   |";
			map[2] = "| o |";
			map[3] = "|   |";
		} else if(_value == 2) {
			map[1] = "|o  |";
			map[2] = "|   |";
			map[3] = "|  o|";
		} else if(_value == 3) {
			map[1] = "|o  |";
			map[2] = "| o |";
			map[3] = "|  o|";
		} else if(_value == 4) {
			map[1] = "|o o|";
			map[2] = "|   |";
			map[3] = "|o o|";
		} else if(_value == 5) {
			map[1] = "|o o|";
			map[2] = "| o |";
			map[3] = "|o o|";
		} else if(_value == 6) {
			map[1] = "|o o|";
			map[2] = "|o o|";
			map[3] = "|o o|";
		}
		map[4] = "-----";
		return map;
	}
}
