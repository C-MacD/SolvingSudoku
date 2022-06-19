import java.nio.charset.CoderResult;
import java.util.ArrayList;

public class SudokuBoard {
	NumberSquare sudokuBoard[] = {};

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
	void updateNeighbors(Coordinates cords, int value) {
		for (NumberSquare currentSquare : sudokuBoard) {
			boolean isItself = currentSquare.getValue() == value;
			boolean isNeighbor = currentSquare.getCords().getXCord() == cords.getXCord() ||
					currentSquare.getCords().getYCord() == cords.getYCord();
			// TODO: or in subsquare

			if (!isItself && isNeighbor) {
				currentSquare.removePossibleValue(value);
				// TODO: check others for completeness
			}
		}

	}
}
