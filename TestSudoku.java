package sudokuapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSudoku {
	private SudokuSolver s;
	private SudokuSolver sFig1;
	// private int[][] gridFig1;

	@Before
	public void setUp() throws Exception {
		s = new SudokuSolver(); // 0 - Sudoku

		sFig1 = new SudokuSolver();
		int[][] b = { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 }, { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; i < 9; j++) {
				sFig1.setValue(i, j, b[i][j]);
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	@Test
	public void solveEmptySudoku() {
		assertTrue(s.solve());
	}

	@Test
	public void solveFig1() {
		assertTrue(sFig1.solve());
	}

	@Test
	public void testGetValue() {
		s.setValue(2, 2, 2);
		s.setValue(3, 5, 9);
		s.setValue(1, 1, 1);
		assertEquals("Inte samma värde", 2, s.getValue(2, 2));
		assertEquals("Inte samma värde", 9, s.getValue(3, 5));
		assertEquals("Inte samma värde", 1, s.getValue(1, 1));

	}

	@Test
	public void testSetValue() {
		s.setValue(3, 3, 5);
		s.setValue(6, 2, 8);
		// s.setValue(10, 1, -5);
		assertEquals("Inte samma värde", 5, s.getValue(3, 3));
		assertEquals("Inte samma värde", 8, s.getValue(6, 2));
		// assertNotEquals("Inte samma värde", -5, s.getValue(10, 1));
		assertEquals("Inte samma värde", 0, s.getValue(4, 3));
	}

	@Test
	public void testSolveNoneSolveable() {
		s.setValue(0, 2, 2);
		s.setValue(0, 3, 2);
		assertFalse(s.solve());
	}

}
