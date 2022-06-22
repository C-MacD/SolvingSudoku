import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuFile {
	public void importBoard() {
		try {

			// Create an empty board first
			// For each board row
			for (int i = 1; i <= 9; i++) {
				// For each number square
				for (int j = 1; j <= 9; j++) {
					SudokuBoard.insertToBoard(new NumberSquare(i, j));

				}
			}

			File file = new File("./Puzzles", "puzzle1.txt"); // TODO: multiple files or everything in a folder
			Scanner myReader = new Scanner(file);

			// For each file row
			for (int i = 1; i <= 9; i++) {
				String line = myReader.nextLine();
				// For each character
				for (int j = 1; j <= 9; j++) {
					char chr = line.charAt(j - 1);
					// TODO: better error checking
					if (chr != 'X') {
						NumberSquare numSquare = SudokuBoard.findSquare(new Coordinates(i, j));
						numSquare.setValue(Integer.parseInt(chr + ""));
					}
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void exportBoard() {
		// TODO
	}
}
