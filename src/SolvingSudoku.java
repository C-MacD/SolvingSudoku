import java.io.File;

public class SolvingSudoku {
    public static void main(String[] args) throws Exception {
        SudokuFile sudokuFile = new SudokuFile();
        // Create new folder to hold solutions every run
        File solutionDirectory = sudokuFile.createSolutionFolder();

        // For all puzzles
        for (final File fileEntry : new File("./Puzzles/").listFiles()) {
            // Skip solution directories
            if (!fileEntry.isDirectory()) {
                // Import board from file and attempt to solve without guessing.
                sudokuFile.importBoard(fileEntry);

                // Brute force if there are still unsolved squares.
                if (!SudokuBoard.isBoardSolved()) {
                    // getNextUnsolved would skip the first square, so manually check it
                    if (SudokuBoard.getSudokuBoard().get(0).getValue() == -1) {
                        SudokuBoard.bruteForce(SudokuBoard.getSudokuBoard().get(0));
                    } else {
                        SudokuBoard.bruteForce(SudokuBoard.getNextUnsolved(SudokuBoard.getSudokuBoard().get(0)));
                    }
                }

                // Write solution to file in a solution directory, use same name with .sln added
                sudokuFile.exportBoard(
                        new File(solutionDirectory.toString(), fileEntry.getName().replace(".txt", ".sln.txt")));
            }
        }

    }

}
