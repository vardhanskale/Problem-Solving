package com.algo.dp;

import java.util.Random;

/*
 * Problem: Given a grid of n x n integers,find an efficient algorithm that returns max 
 * sum with following conditions: 
 *	A.	You should start with top leftmost corner 
 *	B.	You are allowed to move right or down one cell at a time.
 */

public class MaxSumInGrid1 {

	/*
	 * Solution1 : Complete search. This is essentially a max root to leaf sum
	 * problem in trees. TC : 1 + 2^1 + 2^2 + 2^3 + 2^4 + .... 2^n-1. i,j are two
	 * dimensions of the array. gmax and psum are helper variables. gmax = Global
	 * max stores the highest root to leaf path. psum = Path Sum stores the root to
	 * leaf path.
	 */

	public static int maxSumInGrid1(int[][] in) {
		MyInteger gmax = new MyInteger(0);
		// auxMaxSumInGrid(i,j,psum,gmax,in);
		auxMaxSumInGrid1(0, 0, 0, gmax, in);
		return gmax.get();
	}

	public static void auxMaxSumInGrid1(int i, int j, int psum, MyInteger gmax, int[][] in) {

		// When indexes go out of bounds, return 0;
		if (i >= in.length || j >= in.length)
			return;

		/*
		 * Once you reach node [n-1][n-1], you have reached end of path. No need to go
		 * further. Instead of passing current node path to sub problem 1)add current
		 * node path to pSum 2)update gmax with max path 3)return.
		 */
		if (i == in.length - 1 && j == in.length - 1) {
			psum = psum + in[i][j];
			gmax.set(Math.max(gmax.get(), psum));
			return;
		}

		// We have not yet reached [2,2]. Keep going down and right.
		auxMaxSumInGrid1(i, j + 1, psum + in[i][j], gmax, in);
		auxMaxSumInGrid1(i + 1, j, psum + in[i][j], gmax, in);
		/*
		 * There is nothing to return. Just go from root to leaf, compute the path and
		 * store max value in gmax.
		 */
	}

	/*
	 * Solution 2 : Recursion which returns a value. To leverage the benefit of
	 * dynamic programming my subproblems must return a value. Instead of sending
	 * the path from parent to child, we invert the thought such that every node
	 * gets the max sum from its children, adds the value of current cell and
	 * returns the sum to its parent. Once this is implemented, we can use DP (aka,
	 * recursive and iterative memory fill approach). In this approach we don't keep
	 * adding the path of the tree and therefore the supporting variables psum, gmax
	 * need not be passed to the child subproblem.
	 */
	public static int maxSumInGrid2(int[][] in) {
		return auxMaxSumInGrid2(0, 0, in);
	}

	public static int auxMaxSumInGrid2(int i, int j, int[][] in) {

		// When indexes go out of bounds, return 0;
		if (i >= in.length || j >= in.length)
			return 0;
		/*
		 * Once you reach node [n-1][n-1], you have reached end of path. No need to go
		 * further. Return value of in[i][j] to parent.
		 */
		if (i == in.length - 1 && j == in.length - 1)
			return in[i][j];

		// We have not yet reached [2,2]. Keep going down and right.
		int leftTreeSum = auxMaxSumInGrid2(i, j + 1, in);
		int rightTreeSum = auxMaxSumInGrid2(i + 1, j, in);

		return (Math.max(leftTreeSum, rightTreeSum) + in[i][j]);
		// Just go from root to leaf, compute the path and store max value in gmax.
	}

	/*
	 * Solution 3 : Dynamic programming ( aka, memory fill ) using recursion. In
	 * this approach we optimize the recursive "Solution 2" coded above. Converting
	 * a recursive solution to DP is a mechanical task and involves the following
	 * steps : 1. Create a memory to hold the values returned by each subproblem. 2.
	 * Initialize each cell in memory to 0. ( This happens automatically in Java )
	 * 3. Call the subproblem only if it is not solved already. i,e memory value ==
	 * 0 for that subproblem. 4. Store the value returned from each subproblem into
	 * the memory cell so that we don't solve the same problem again. 5. Return the
	 * value in last cell of memory, which contains the required answer.
	 */

	public static int maxSumInGrid3(int[][] in) {
		/*
		 * How big should our memory be ? Your 2x2 matrix will have 2x2 unique
		 * subproblems. Confirm by checking the tree diagram. Each cell in 2x2 matrix
		 * will hold value returned by each subproblem. So our memory size should equal
		 * to the size of the array. But if I see the tree the indexes are spilling out
		 * of array boundaries. One option is to provision for that as well and keep one
		 * row and column extra in memory. Second option is return 0, whenever index
		 * goes out of array boundary. While both options would work, we choose the
		 * second one because it saves memory.
		 */
		int[][] mem = new int[in.length][in.length];
		int maxSum = auxMaxSumInGrid3(0, 0, mem, in);

		// At the end of program mem[0][0] will store the result
		return mem[0][0];
	}

	/*
	 * Converting a recursive program into DP is a mechanical task. 1.Create a
	 * memory (array) to store values returned from sub problems. 2.Make a recursive
	 * call only if mem[n] == 0. 3.While making a recursive call to sub problems
	 * pass the memory values so that each subproblem can store results in mem.
	 * 4.Ensure to store the values returned from the recursive call into an array.
	 * 5.Finally, return the array index value.
	 */
	public static int auxMaxSumInGrid3(int i, int j, int[][] mem, int[][] in) {

		// When indexes goes out of bounds, store 0 in memory and return;
		if (i >= in.length || j >= in.length) {
			// mem[i][j] = 0;
			// return mem[i][j];
			return 0; // Option 2. Return 0 when index is out of bounds.
		}

		/*
		 * Once you reach node [n-1][n-1], you have reached end of path. No need to go
		 * further. Return value of in[i][j] to parent.
		 */
		if (i == in.length - 1 && j == in.length - 1) {
			// mem[i][j] = in[i][j] ;
			// return mem[i][j];
			return in[i][j]; // Return last cells value when index is out of bounds.
		}

		// if this problem is alreay solved , return.
		if (mem[i][j] != 0)
			return mem[i][j];
		// problem is not yet solved. Lets solve it.
		int downTreeSum = auxMaxSumInGrid3(i + 1, j, mem, in);
		int rightTreeSum = auxMaxSumInGrid3(i, j + 1, mem, in);
		mem[i][j] = Math.max(downTreeSum, rightTreeSum) + in[i][j];
		return mem[i][j];
	}

	private static int auxSumInGrid4(int[][] in) {

		int[][] mem = new int[in.length + 1][in.length + 1];

		// Set the value at the bottom right corner cell which was our terminating
		// condition.
		mem[in.length - 1][in.length - 1] = in[in.length - 1][in.length - 1];
		System.out.println("Content of memory : ");
		displayMemory(mem);

		// Lets start the party !
		for (int i = in.length - 1; i >= 0; i--)
			for (int j = in.length - 1; j >= 0; j--)
				mem[i][j] = Math.max(mem[i + 1][j], mem[i][j + 1]) + in[i][j];
		displayMemory(mem);
		return mem[0][0];
	}

	public static void displayMemory(int[][] mem) {
		System.out.println("Content of memory : ");
		for (int i = 0; i < mem.length; i++) {
			for (int j = 0; j < mem.length; j++) {
				System.out.print("[" + mem[i][j] + "]");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		// Populate random values in an n D array
		int in[][] = new int[n][n];
		Random rand = new Random(0);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				in[i][j] = rand.nextInt(9);

		System.out.println("Contents of 2D array : ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print("[" + in[i][j] + "]");
			System.out.println("");
		}

		System.out.println("Max sum in grid using complete search is : " + maxSumInGrid1(in));
		System.out.println("Max sum in grid using recursion is : " + maxSumInGrid2(in));
		System.out.println("Max sum in grid using recursive DP solution is : " + maxSumInGrid3(in));
		System.out.print("Max sum in grid using iterative solution is : " + auxSumInGrid4(in));
	}

}
