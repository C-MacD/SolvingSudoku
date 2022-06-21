import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuFile {
	public void importBoard() {
		try {

			File file = new File("./Puzzles", "puzzle1.txt"); // TODO: multiple files or everything in a folder

			Scanner myReader = new Scanner(file);
			NumberSquare numSquare;
			// For each file row
			for (int i = 1; i <= 9; i++) {
				String line = myReader.nextLine();
				// For each character
				for (int j = 1; j <= 9; j++) {
					char chr = line.charAt(j - 1);
					// TODO: better error checking
					if (chr == 'X') {
						numSquare = new NumberSquare(i, j);
					} else {
						numSquare = new NumberSquare(i, j, Integer.parseInt(chr + ""));
					}
					SolvingSudoku.board.insertToBoard(numSquare);
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
