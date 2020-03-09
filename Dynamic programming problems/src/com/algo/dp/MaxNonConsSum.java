package com.algo.dp;

import java.util.Arrays;
import java.util.Random;

public class MaxNonConsSum {

	/*
	 * Solving by complete search approach. We converted the problem into tree
	 * problem and our analysis reveled that the solution was the longest root to
	 * leaf path. To find root to leaf path, we need to pass a variable from parent
	 * to child. pSum. We record a global maximum in another variable, gMax which is
	 * also passed from parent to child. We also need to pass an index i, which
	 * tells from where the left and right child should process array. When i >=
	 * in.length , its time to return, but we first update gMax =
	 * Math.max(pSum,gMax) gMax in
	 * 
	 */
	static int maxNonConsSum1(int[] in) {
		MyInteger gmax = new MyInteger(0);
		auxMaxNonConsSum1(in, 0, 0, gmax);
		return gmax.get();
	}

	// pSum = pathSum, gMax = global Max.
	// If your logic for the first level is right, program will run all fine.
	static void auxMaxNonConsSum1(int[] in, int i, int psum, MyInteger gmax) {

		if (i >= in.length) {
			// Set the global max and then return.
			gmax.set(Math.max(psum, gmax.get()));
			return;
		}
		auxMaxNonConsSum1(in, i + 2, psum + in[i], gmax);
		auxMaxNonConsSum1(in, i + 1, psum, gmax);
	}

	// Recursive solution.
	public static int maxNonConsSum2(int[] in) {
		return auxMaxNonConsSum2(in, 0);
	}

	public static int auxMaxNonConsSum2(int[] in, int i) {
		if (i >= in.length) {
			return 0;
		}
		int inclusive = auxMaxNonConsSum2(in, i + 2);
		int exclusive = auxMaxNonConsSum2(in, i + 1);
		return Math.max(inclusive + in[i], exclusive);
	}

	// Dynamic programming,aka memory filling - using recursion.
	public static int maxNonConsSum31(int[] in) {
		int[] mem = new int[in.length];
		return auxMaxNonConsSum31(in, 0, mem);
	}

	/*
	 * Changes done in the recursive program... Pass memory across all the
	 * subproblems. Subproblems store the sum in memory and return memory. Replace
	 * int with void in method signature. Replace
	 * "if (i >= in.length) return mem[i]" with...."if (mem[i] != 0 ) return mem[i]
	 * 
	 */

	public static int auxMaxNonConsSum31(int[] in, int i, int[] mem) {

		// If the following code is not added you get
		// java.lang.ArrayIndexOutOfBoundsException :-)
		if (i >= in.length) {
			return 0;
		}
		if (mem[i] != 0) {
			// return 0; -> This was recursive approach.
			// Following is the DP approach. If this subproblem is solved already, return
			// its value.
			return mem[i];
		}
		int inclusive = auxMaxNonConsSum31(in, i + 2, mem);
		int exclusive = auxMaxNonConsSum31(in, i + 1, mem);
		mem[i] = Math.max(inclusive + in[i], exclusive);
		return mem[i];
	}

	public static int maxNonConsSum32(int[] in) {
		int n = in.length;
		int[] mem = new int[n + 2];
		mem[n + 1] = mem[n] = 0;
		for (int i = n - 1; i >= 0; --i) {
			int inclusive = mem[i + 2] + in[i];
			int exclusive = mem[i + 1];
			mem[i] = Math.max(inclusive, exclusive);
		}
		System.out.println(Arrays.toString(mem));
		tracePath(mem, in);
		return mem[0];
	}

	private static void tracePath(int[] mem, int[] in) {
		for (int i = 0; i < in.length;) {
			if (mem[i] == mem[i + 1]) {
				i = i + 1;
			} else {
				System.out.print("(" + in[i] + "," + i + ")" + "->");
				i = i + 2;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// Get the size of the array.
		int n = Integer.parseInt(args[0]);
		// Construct the integer array
		int[] in = new int[n];
		// Populate values in the integer array
		Random rand = new Random();
		// We are adding 1 because we need the random numbers in array to be > 0.
		// If we don't add it , the random generator will produce values from 0 to n-1
		for (int i = 0; i < n; i++)
			in[i] = rand.nextInt(n) + 1;
		System.out.println(Arrays.toString(in));
		System.out.println("maxNonConsSum1(in) : " + maxNonConsSum1(in));
		System.out.println("maxNonConsSum2(in) : " + maxNonConsSum2(in));
		System.out.println("maxNonConsSum31(in) : " + maxNonConsSum31(in));
		System.out.println("maxNonConsSum32(in) : " + maxNonConsSum32(in));
	}

}
