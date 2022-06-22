import java.util.ArrayList;

public class SudokuBoard {
	private static ArrayList<NumberSquare> sudokuBoard = new ArrayList<NumberSquare>();

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
	 * |---||---||---|
	 * | 1 || 2 || 3 |
	 * |---||---||---|
	 * |---||---||---|
	 * | 4 || 5 || 6 |
	 * |---||---||---|
	 * |---||---||---|
	 * | 7 || 8 || 9 |
	 * |---||---||---|
	 * 
	 */
	public SudokuBoard() {
		super();
	}

	/**
	 * When a square identifies its value for certain, update all other squares in
	 * that row, column, and subsquare by removing the value from their list of
	 * possible values.
	 * 
	 * @param cords
	 * @param value
	 */
	static void updateNeighbors(NumberSquare solvedSquare) {
		for (NumberSquare currentSquare : sudokuBoard) {
			boolean isItself = currentSquare.equals(solvedSquare);
			// In same row, column, or subsquare
			boolean isNeighbor = currentSquare.getCords().getXCord() == solvedSquare.getCords().getXCord() ||
					currentSquare.getCords().getYCord() == solvedSquare.getCords().getYCord() ||
					currentSquare.getSubSquare() == solvedSquare.getSubSquare();

			if (!isItself && isNeighbor) {
				currentSquare.removePossibleValue(solvedSquare.getValue());
			}
		}

	}

	static void insertToBoard(NumberSquare numberSquare) {
		sudokuBoard.add(numberSquare);
	}

	static NumberSquare findSquare(Coordinates cords) {
		for (NumberSquare numberSquare : sudokuBoard) {
			if (numberSquare.getCords().getXCord() == cords.getXCord() &&
					numberSquare.getCords().getYCord() == cords.getYCord()) {
				return numberSquare;
			}
		}
		return null; // Should never return this
	}

	static void printBoard() {
		// For each board row
		for (int i = 1; i <= 9; i++) {
			// For each number square
			for (int j = 1; j <= 9; j++) {
				NumberSquare numSquare = SudokuBoard.findSquare(new Coordinates(i, j));
				// System.out.print(numSquare.getValue());
				System.out.println(numSquare.getPossibleValues().toString());
			}
			System.out.println();
		}
	}
}
