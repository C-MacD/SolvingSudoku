import java.util.ArrayList;

public class NumberSquare {
	private ArrayList<Integer> possibleValues = new ArrayList<Integer>();
	private Coordinates cords;
	// If this was used to eliminate possibilities in other squares, so the process
	// is done once.
	private boolean usedSolution = false;
	private int temporaryValue = -1;

	/**
	 * Constructor when value is given.
	 * 
	 * @param xCord x coordinate 1-9
	 * @param yCord y coordinate 1-9
	 * @param value Square value if known/given 1-9
	 */
	public NumberSquare(int xCord, int yCord, int value) {
		super();
		cords = new Coordinates(xCord, yCord);
		if (value != -1) {
			// If value is known, add only it to the list.
			this.possibleValues.add(value);
		}
	}

	/**
	 * Constructor when value is unknown. Value becomes -1.
	 * 
	 * @param xCord x coordinate 1-9
	 * @param yCord y coordinate 1-9
	 */
	public NumberSquare(int xCord, int yCord) {
		// Calls above constructor
		this(xCord, yCord, -1);
		// Adds 1-9 as possible values
		for (int i = 1; i <= 9; i++) {
			possibleValues.add(i);
		}
	}

	/**
	 * If there is only one possibilities, that is used as the value. Otherwise the
	 * temporaryValue is returned, which is either set to something while brute
	 * forcing, or is -1 for unknown.
	 * 
	 * @return The value of the square or -1 if unknown.
	 */
	public int getValue() {
		if (possibleValues.size() == 1) {
			return possibleValues.get(0);
		} else {
			return temporaryValue;
		}
	}

	/**
	 * If a clear value is determined, clear the list and add only it.
	 * 
	 * @param value Value to set the square to.
	 */
	public void setValue(int value) {
		possibleValues.clear();
		possibleValues.add(value);

		// Only once, eliminate the value as a possibility from all neighbors.
		if (!usedSolution) {
			usedSolution = true;
			SudokuBoard.updateNeighbors(this);
		}
	}

	/**
	 * Set temporary value while brute forcing.
	 * 
	 * @param value Temporary value to set.
	 */
	public void setTemporaryValue(int value) {
		temporaryValue = value;
	}

	/**
	 * Remove temporary value when backtracking from a wrong path in the recursion.
	 * Sets to -1.
	 */
	public void clearTemporaryValue() {
		temporaryValue = -1;
	}

	/**
	 * Get a list of all possible values
	 * 
	 * @return List of all possible values
	 */
	public ArrayList<Integer> getPossibleValues() {
		return this.possibleValues;
	}

	/**
	 * Eliminate a value as a possibility. No harm in trying to remove a value that
	 * has already been removed.
	 * 
	 * @param value Value to remove
	 */
	public void removePossibleValue(Integer value) {
		possibleValues.remove(value);
		if (possibleValues.size() == 1) {
			setValue(possibleValues.get(0));
		}
	}

	/**
	 * Getter for the square's coordinates
	 * 
	 * @return The coordinates
	 */
	public Coordinates getCords() {
		return this.cords;
	}

	/**
	 * Calculates and returns coordinates of the subsquare.
	 * 1-3 becomes 1.
	 * 4-6 becomes 2.
	 * 7-9 becomes 3.
	 * 
	 * @return Coordinates of the subsquare. x and y are 1-3.
	 */
	public Coordinates getSubSquare() {
		int x = (int) ((cords.getXCord() - 1) / 3) + 1;
		int y = (int) ((cords.getYCord() - 1) / 3) + 1;

		return new Coordinates(x, y);
	}

	/**
	 * Creating a new object with the same coordinates fails a .equals() check, so
	 * this checks if the x and y coordinates of two squares are the same.
	 * 
	 * @param comparisonSquare Another square to compare to.
	 * @return True if both squares have the same coordinates, false if they differ.
	 */
	public boolean isSameAs(NumberSquare comparisonSquare) {
		return this.getCords().getXCord() == comparisonSquare.getCords().getXCord() &&
				this.getCords().getYCord() == comparisonSquare.getCords().getYCord();
	}

	/**
	 * Checks all other neighbors to see if setting the given value would be legal.
	 * 
	 * @param value A value to check the legality of.
	 * @return True if it is a legal move, false otherwise.
	 */
	public boolean isMoveLegal(int value) {
		for (NumberSquare currentSquare : SudokuBoard.getSudokuBoard()) {
			boolean isItself = currentSquare.equals(this);
			boolean isNeighbor = isNeighbor(currentSquare);
			// Check if every other square in the same row, column, or subsquare has the
			// same value.
			if (!isItself && isNeighbor && currentSquare.getValue() == value) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks another numberSquare to see if it is in the same row, column, or
	 * subsquare.
	 * 
	 * @param comparisonSquare
	 * @return
	 */
	public boolean isNeighbor(NumberSquare comparisonSquare) {
		return comparisonSquare.getCords().getXCord() == this.getCords().getXCord() ||
				comparisonSquare.getCords().getYCord() == this.getCords().getYCord() ||
				(comparisonSquare.getSubSquare().getXCord() == this.getSubSquare().getXCord() &&
						comparisonSquare.getSubSquare().getYCord() == this.getSubSquare().getYCord());
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
