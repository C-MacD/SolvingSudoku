public class NumberSquare {
	private int value;
	private int possibleValues[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private Coordinates cords;

	// Priority? Increase every time a possible value is eliminated?

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
		this.value = value;
		this.possibleValues = new int[] { value };
	}

	/**
	 * Constructor when value is unknown.
	 * 
	 * @param xCord
	 * @param yCord
	 */
	public NumberSquare(int xCord, int yCord) {
		this(xCord, yCord, -1);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int[] getPossibleValues() {
		return this.possibleValues;
	}

	public void removePossibleValue(int value) {
		// TODO
	}

	public Coordinates getCords() {
		return this.cords;
	}

}

/**
 * Keeps track of x and y coordinates of a square within the 9x9 board. X
 * increases to the right, y increases going down.
 * 
 * . 1 2 3 4 5 6 7 8 9
 * 1 □ □ □ □ □ □ □ □ □
 * 2 □ □ □ □ □ □ □ □ □
 * 3 □ □ □ □ □ □ □ □ □
 * 4 □ □ □ □ □ □ □ □ □
 * 5 □ □ □ □ □ □ □ □ □
 * 6 □ □ □ □ □ □ □ □ □
 * 7 □ □ □ □ □ □ □ □ □
 * 8 □ □ □ □ □ □ □ □ □
 * 9 □ □ □ □ □ □ □ □ □
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
