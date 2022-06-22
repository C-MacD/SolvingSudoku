import java.util.ArrayList;

public class NumberSquare {
	private ArrayList<Integer> possibleValues = new ArrayList<Integer>();
	private Coordinates cords;
	private boolean usedSolution = false;

	/**
	 * Constructor when value is given.
	 * 
	 * @param xCord
	 * @param yCord
	 * @param value
	 */
	public NumberSquare(int xCord, int yCord, int value) {
		super();
		cords = new Coordinates(xCord, yCord);
		if (value != -1) {
			this.possibleValues.add(value);
		}
	}

	/**
	 * Constructor when value is unknown.
	 * 
	 * @param xCord
	 * @param yCord
	 */
	public NumberSquare(int xCord, int yCord) {
		this(xCord, yCord, -1);
		for (int i = 1; i <= 9; i++) {
			possibleValues.add(i);
		}
	}

	public int getValue() {
		if (possibleValues.size() == 1) {
			return possibleValues.get(0);
		} else {
			return -1;
		}
	}

	public void setValue(int value) {
		possibleValues.clear();
		possibleValues.add(value);

		if (!usedSolution) {
			SudokuBoard.updateNeighbors(this);
			usedSolution = true;
		}
	}

	public ArrayList<Integer> getPossibleValues() {
		return this.possibleValues;
	}

	public void removePossibleValue(Integer value) {
		possibleValues.remove(value);
		if (possibleValues.size() == 1) {
			setValue(possibleValues.get(0));
		}
	}

	public Coordinates getCords() {
		return this.cords;
	}

	public Coordinates getSubSquare() {
		/*
		 * 1-3 becomes 1.
		 * 4-6 becomes 2.
		 * 7-9 becomes 3.
		 */
		int x = (int) ((cords.getXCord() - 1) / 3) + 1;
		int y = (int) ((cords.getYCord() - 1) / 3) + 1;

		return new Coordinates(x, y);
	}

}

/**
 * Keeps track of x and y coordinates of a square within the 9x9 board. X
 * increases to the right, y increases going down, starts at 1.
 * 
 */
class Coordinates {
	private int xCord;
	private int yCord;

	public Coordinates(int xCord, int yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
	}

	public int getXCord() {
		return this.xCord;
	}

	public int getYCord() {
		return this.yCord;
	}
}
