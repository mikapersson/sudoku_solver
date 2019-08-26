package sudokuapp;

public class SudokuSolver {
	int[][] board;

	public SudokuSolver() {
		board = new int[9][9]; // 0 - Matris
	}

	/**
	 * Gets value from row row and column col.
	 * 
	 * @param row
	 *            the row of interest
	 * @param col
	 *            the column of interest
	 * @return the integer value of the box
	 */
	public int getValue(int row, int col) {
		return board[row][col];
	}

	/**
	 * Sets value in row row and column col.
	 * 
	 * @param row
	 *            the row of interest (index)
	 * @param col
	 *            the column of interest (index)
	 */
	public void setValue(int row, int col, int x) {
		board[row][col] = x;
	}

	/**
	 * Solves a given sudoku.
	 * 
	 * @return true if the sudoku can be solved (and solves the sudoku) or false
	 *         if there is no solution 
	 */
	public boolean solve() {
		return solve(0, 0);
	}

	// Rekursiv hjälpmetod
	private boolean solve(int row, int col) {
		if (row == 9 && col == 0) { // Om sista rutan är ifylld
			return true;
		}
		if (getValue(row, col) != 0) { // Om det finns ett ifyllt värde
			if (tryAdd(board, row, col, board[row][col])) {
				if (col == 8) { // Om raden är ifylld
					return solve(row + 1, 0);
				} else {
					return solve(row, col + 1);
				}
			} else {
				return false;
			}
		} else { // Testar fylla en tom ruta
			for (int v = 1; v < 10; v++) { // Testar alla siffror
				if (tryAdd(board, row, col, v)) { // Om siffran är gilltig
					setValue(row, col, v);
					if (col == 8) { // Börjar på nästa rad
						if (solve(row + 1, 0)) { // Undersöker om det finns en
													// lösning med siffran i
													// denna ruta
							return true;
						} else {
							setValue(row, col, 0); // Om det inte fanns någon
													// lösning, går bakåt i
													// metoderna och tömmer
													// rutorna på vägen
						}
					} else { // Om raden ännu inte är ifylld
						if (solve(row, col + 1)) {
							return true;
						} else {
							setValue(row, col, 0);
						}
					}
				}
			}
			return false;
		}
	}

	// Checks if the value can be placed in the box without breaking the rules
	private boolean tryAdd(int[][] board, int row, int col, int value) {
		// int test = board[row][col];
		for (int i = 0; i < board.length; i++) { // Går igenom raden
			if (i != col && board[row][i] == value) {
				return false;
			}
		}
		for (int j = 0; j < board[row].length; j++) { // Går igenom kolonnen
			if (j != row && board[j][col] == value) {
				return false;
			}
		}
		int r = (row / 3) * 3;
		int c = (col / 3) * 3;
		for (int i = 0; i < 3; i++) { // Går igenom 3x3 området
			for (int j = 0; j < 3; j++) {
				if ((r + i) != row && (c + j) != col && board[r + i][c + j] == value) {
					return false;
				}
			}
		}
		return true;
	}
}
