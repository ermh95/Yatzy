package data;

public class Player {

	private Score _ones;
	private Score _twos;
	private Score _threes;
	private Score _fours;
	private Score _fives;
	private Score _sixes;
	private Score _house;

	public Player() {
		// Intentionally empty constructor
	}

	public int total() {
		int sum = 0;
		if(_ones != null) {
			sum += _ones.getValue();
		}
		if(_twos != null) {
			sum += _twos.getValue();
		}
		if(_threes != null) {
			sum += _threes.getValue();
		}
		if(_fours != null) {
			sum += _fours.getValue();
		}
		if(_fives != null) {
			sum += _fives.getValue();
		}
		if(_sixes != null) {
			sum += _sixes.getValue();
		}
		if(_house != null) {
			sum += _house.getValue();
		}
		return sum;
	}
	
	public boolean isFull() {
		return _ones != null && 
				_twos != null && 
				_threes != null && 
				_fours != null && 
				_fives != null && 
				_sixes != null && 
				_house != null; 
	}

	public void scoreOnes(Hand hand) throws AlreadyScoredException {
		if (_ones != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 1) {
				sum += die.getValue();
			}
		}
		_ones = new Score(sum);
	}

	public void scoreTwos(Hand hand) throws AlreadyScoredException {
		if (_twos != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 2) {
				sum += die.getValue();
			}
		}
		_twos = new Score(sum);
	}

	public void scoreThrees(Hand hand) throws AlreadyScoredException {
		if (_threes != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 3) {
				sum += die.getValue();
			}
		}
		_threes = new Score(sum);
	}

	public void scoreFours(Hand hand) throws AlreadyScoredException {
		if (_fours != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 4) {
				sum += die.getValue();
			}
		}
		_fours = new Score(sum);
	}

	public void scoreFives(Hand hand) throws AlreadyScoredException {
		if (_fives != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 5) {
				sum += die.getValue();
			}
		}
		_fives = new Score(sum);
	}

	public void scoreSixes(Hand hand) throws AlreadyScoredException {
		if (_sixes != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		for (Die die : hand.getDice()) {
			if (die.getValue() == 6) {
				sum += die.getValue();
			}
		}
		_sixes = new Score(sum);
	}

	public void scoreHouse(Hand hand) throws AlreadyScoredException {
		if (_house != null) {
			throw new AlreadyScoredException();
		}
		int sum = 0;
		if (hand.isHouse()) {
			for (Die die : hand.getDice()) {
				sum += die.getValue();
			}
		}
		_house = new Score(sum);
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("Ones: ");
		if(_ones != null) {
			bld.append(_ones.getValue());
		}
		bld.append("\n");
		bld.append("Twos: ");
		if(_twos != null) {
			bld.append(_twos.getValue());
		}
		bld.append("\n");
		bld.append("Threes: ");
		if(_threes != null) {
			bld.append(_threes.getValue());
		}
		bld.append("\n");
		bld.append("Fours: ");
		if(_fours != null) {
			bld.append(_fours.getValue());
		}
		bld.append("\n");
		bld.append("Fives: ");
		if(_fives != null) {
			bld.append(_fives.getValue());
		}
		bld.append("\n");
		bld.append("Sixes: ");
		if(_sixes != null) {
			bld.append(_sixes.getValue());
		}
		bld.append("\n");
		bld.append("House: ");
		if(_house != null) {
			bld.append(_house.getValue());
		}
		bld.append("\n\n");
		bld.append("Total Score: ");
		bld.append(total());
		return bld.toString();
	}
	
}
