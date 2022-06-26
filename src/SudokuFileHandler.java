import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SudokuFileHandler {
	/**
	 * Imports from a properly formatted file. Creates all necessary objects, and in
	 * the process tries to solve the puzzle through process of elimination.
	 * 
	 * @param file Formatted file containing known values and X's
	 */
	public void importBoard(File file) {
		try {
			SudokuBoard.clearBoard();
			// Create an empty board first
			// For each board row
			for (int i = 1; i <= 9; i++) {
				// For each number square
				for (int j = 1; j <= 9; j++) {
					SudokuBoard.insertToBoard(new NumberSquare(i, j));
				}
			}

			Scanner myReader = new Scanner(file);

			// For each file row
			for (int i = 1; i <= 9; i++) {
				String line = myReader.nextLine();
				// For each character
				for (int j = 1; j <= 9; j++) {
					char chr = line.charAt(j - 1);
					// TODO: better error checking
					if (chr != 'X') {
						// Sets the value of all given values
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

	/**
	 * Writes board values (the solution) to specified file.
	 * 
	 * @param file
	 */
	public void exportBoard(File file) {
		try {
			FileWriter myWriter = new FileWriter(file);
			String output = "";
			// For each board row
			for (int i = 1; i <= 9; i++) {
				// For each number square
				for (int j = 1; j <= 9; j++) {
					// Find each value in order and add it to output string
					NumberSquare numSquare = SudokuBoard.findSquare(new Coordinates(i, j));
					if (numSquare.getValue() == -1) {
						output += "X";
					} else {
						output += numSquare.getValue();
					}
				}
				output += "\n";
			}
			myWriter.write(output);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Looks in the Puzzles folder to create an empty folder with a unique name.
	 * 
	 * @return A folder represented as a File object.
	 */
	public File createSolutionFolder() {
		// Find the next unused folder name starting at 0.
		int i = 0;
		File dir = new File("./Puzzles/slnRun" + i + "/");
		try {
			do {
				dir = new File("./Puzzles/slnRun" + i + "/");
				i++;
			} while (dir.exists() && dir.isDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create that folder to hold the solutions.
		try {
			if (dir.mkdir()) {
				// System.out.println("Directory Created");
			} else {
				System.out.println("Directory is not created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dir;
	}
}
