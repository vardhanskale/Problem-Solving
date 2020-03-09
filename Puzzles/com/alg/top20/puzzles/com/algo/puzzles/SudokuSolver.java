package com.algo.puzzles;

import java.util.Arrays;

/* 
Problem : Solve the Sudoku puzzle. 
Requirements: 
1.	You are given 9x9 cells.
2.	Each row must have numbers from 1-9 without repetition.
3.	Each column must have numbers from 1-9 without repetition.   	
4.	Each 3x3 non overlapping grid must consist of 1-9 without repetition. 
*/

public class SudokuSolver {

	public static void sudokusolver1(int[][] in) {
		int[][] out = new int[9][9];
		auxSolver1(0, 0, in, out);
	}

	/*
	 * Terminating condition : We traverse from cell(0,0) till cell(8,8) in the
	 * grid. After reaching the last cell, the next cell is (9,0) which is out of
	 * bounds. To determine the depth of the tree the subprogram need two indices
	 * (row,column). in[][] is input array and contains the sudoku grid. out[][]
	 * contains root to leaf edge values. It holds the content we need to display as
	 * output.
	 */
	private static void auxSolver1(int row, int col, int in[][], int out[][]) {
		// Terminating condition : Sudoko grid has 8x8 cells.
		if (row == 9) {
			// Lets display the content of 2D array after finishing up 81 cells.
			for (int[] tmp : out)
				System.out.println(Arrays.toString(tmp));
			System.out.println();
			return;
		}

		/*
		 * If the Sodoku grid's cell already has a value, no need to make 9 recursive
		 * calls. Skip to the next cell, else add the edge value in the variable and
		 * pass it while making 9 recursive calls.
		 */
		if (in[row][col] != 0) {
			// Even though we are not making 1-9 recursive calls, we will store the edge
			// values in out array
			out[row][col] = in[row][col];
			// This will jump one column or row.
			auxSolver1(col == 8 ? row + 1 : row, (col + 1) % 9, in, out); // Jump to next cell of grid.

		} else {
			// Make 9 recursive calls.
			for (int d = 1; d < 9; ++d) {
				// populate edge/cell values in memory and pass the memory to sub-routine.
				out[row][col] = d;
				// We go from cell (0,0) to cell (0,8). Then the row index is incremented. Col
				// val resets after 8th cell.
				auxSolver1(col == 8 ? row + 1 : row, (col + 1) % 9, in, out);
			}
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		// create an empty array and make a call to sudoku solver.
		int[][] in = new int[9][9];
		sudokusolver1(in);
	}

}
