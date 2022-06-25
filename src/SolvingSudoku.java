public class SolvingSudoku {
    // public static SudokuBoard board = new SudokuBoard(); // TODO: better access

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        SudokuFile sudokuFile = new SudokuFile();
        sudokuFile.importBoard();
        SudokuBoard.printBoard();

        if (SudokuBoard.getSudokuBoard().get(0).getValue() == -1) {
            SudokuBoard.bruteForce(SudokuBoard.getSudokuBoard().get(0));
        } else {
            SudokuBoard.bruteForce(SudokuBoard.getNextUnsolved(SudokuBoard.getSudokuBoard().get(0)));
        }
    }
}
