import java.util.ArrayList;

public class SudokuBoard {
	// Ordered by coordinates
	private static ArrayList<NumberSquare> sudokuBoard = new ArrayList<NumberSquare>();
	// If brute force results in every square having a permanent or temporary value,
	// every value is legal so the solution has been found.
	private static boolean boardTempSolved = false;

	/*
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
	 * SubSquare layout
	 * - - 1 -- 2 -- 3
	 * - |---||---||---|
	 * 1 | . || . || . |
	 * - |---||---||---|
	 * - |---||---||---|
	 * 2 | . || . || . |
	 * - |---||---||---|
	 * - |---||---||---|
	 * 3 | . || . || . |
	 * - |---||---||---|
	 * 
	 */

	/**
	 * Getter for sudokuBoard
	 * 
	 * @return An ArrayList of NumberSquares
	 */
	public static ArrayList<NumberSquare> getSudokuBoard() {
		return sudokuBoard;
	}

	/**
	 * When a square identifies its value for certain, update all other squares in
	 * that row, column, and subsquare by removing the value from their list of
	 * possible values.
	 * 
	 * @param solvedSquare A NumberSquare with its value already set.
	 */
	static void updateNeighbors(NumberSquare solvedSquare) {
		// For every square
		for (NumberSquare currentSquare : sudokuBoard) {
			boolean isItself = currentSquare.equals(solvedSquare);
			boolean isNeighbor = solvedSquare.isNeighbor(currentSquare);

			// Don't update itself, and only update neighbors
			if (!isItself && isNeighbor) {
				currentSquare.removePossibleValue(solvedSquare.getValue());
			}
		}

	}

	/**
	 * Adds a numberSquare to the board. Only for use by importing a board. Board
	 * must be ordered by coordinates, and this does not enforce any order (yet).
	 * 
	 * @param numberSquare numberSquare to add on the board's end.
	 */
	static void insertToBoard(NumberSquare numberSquare) {
		sudokuBoard.add(numberSquare);
	}

	/**
	 * Finds the numberSquare at the specified coordinates. Coordinates must be
	 * valid or null is returned.
	 * 
	 * @param cords Coordinates
	 * @return numberSquare at the coordinates
	 */
	static NumberSquare findSquare(Coordinates cords) {
		for (NumberSquare numberSquare : sudokuBoard) {
			if (numberSquare.getCords().getXCord() == cords.getXCord() &&
					numberSquare.getCords().getYCord() == cords.getYCord()) {
				return numberSquare;
			}
		}
		return null; // Should never return this
	}

	/**
	 * Prints current board to console for debugging.
	 */
	static void printBoard() {
		// For each board row
		for (int i = 1; i <= 9; i++) {
			// For each number square
			for (int j = 1; j <= 9; j++) {
				NumberSquare numSquare = SudokuBoard.findSquare(new Coordinates(i, j));
				System.out.print(numSquare.getValue());
			}
			System.out.println();
		}
	}

	/**
	 * Recursive function to solve a sudoku puzzle if process of elimination does
	 * not succeed. Basically a backtracking algorithm.
	 * 
	 * @param numSquare The first/next unsolved numberSquare.
	 */
	static void bruteForce(NumberSquare numSquare) {
		/*
		 * For all possibilities
		 * - Try first/next possibility if legal
		 * - - Set temporary value
		 * - - - Go to next numSquare (recursive call)
		 * 
		 */
		for (int possibility : numSquare.getPossibleValues()) {
			if (numSquare.isMoveLegal(possibility) && !boardTempSolved) {
				numSquare.setTemporaryValue(possibility);
				if (getNextUnsolved(numSquare) != null) {
					bruteForce(getNextUnsolved(numSquare));
				} else {
					boardTempSolved = true;
					break; // Break for loop, don't try more possibilities
				}
			}
		}
		// Clear the temporary value unless the board is solved.
		if (!boardTempSolved) {
			numSquare.clearTemporaryValue();
		}

	}

	/**
	 * Given a numberSquare, get the next in the list without a permanent or
	 * temporary value.
	 * 
	 * @param currentSquare A numberSquare of where start looking at the following
	 *                      ones.
	 * @return The next unsolved numberSquare, or null if the end of the list is
	 *         reached.
	 */
	static NumberSquare getNextUnsolved(NumberSquare currentSquare) {
		for (int i = sudokuBoard.indexOf(currentSquare) + 1; i < sudokuBoard.size(); i++) {
			if (sudokuBoard.get(i).getValue() == -1) {
				return sudokuBoard.get(i);
			}
		}

		// If reached the end
		return null;
	}

	/**
	 * Checks if the puzzle is solved.
	 * 
	 * @return True if every square has a permanent or temporary value, false
	 *         otherwise.
	 */
	static boolean isBoardSolved() {
		for (NumberSquare numberSquare : sudokuBoard) {
			if (numberSquare.getValue() == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Delete everything in the sudoku board.
	 */
	static void clearBoard() {
		sudokuBoard.clear();
	}
}
