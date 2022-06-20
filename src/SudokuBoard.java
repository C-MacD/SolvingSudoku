public class SudokuBoard {
	NumberSquare sudokuBoard[] = {};

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
	void updateNeighbors(NumberSquare solvedSquare) {
		for (NumberSquare currentSquare : sudokuBoard) {
			boolean isItself = currentSquare.equals(solvedSquare);
			// In same row, column, or subsquare
			boolean isNeighbor = currentSquare.getCords().getXCord() == solvedSquare.getCords().getXCord() ||
					currentSquare.getCords().getYCord() == solvedSquare.getCords().getYCord() ||
					currentSquare.getSubSquare() == solvedSquare.getSubSquare();

			if (!isItself && isNeighbor) {
				currentSquare.removePossibleValue(solvedSquare.getValue());
				// TODO: check others for completeness
			}
		}

	}
}
