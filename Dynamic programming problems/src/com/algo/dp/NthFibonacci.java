package com.algo.dp;

public class NthFibonacci {

	private static int prev2;

	/*
	 * Solution 1 : Recursion. Each layer is contributing 2 children at max. So the
	 * total nodes to traverse will be TC < = 1 + 2 + 2^2 + 2^3 + 2^4 = O(2^n),
	 * which is a very inefficient way of solving it. Problems in recursive approach
	 * : 1 : For large n ,program slows down and would cause stack overflow. 2 : The
	 * same subproblem gets executed again and again. fib1(4) , fib1(3)
	 */
	public static int fib1(int n) {
		int fib1, fib2;
		if (n <= 2)
			return 1;
		fib1 = fib1(n - 1);
		fib2 = fib1(n - 2);
		return fib1 + fib2;
	}

	/*
	 * Solution 2 : Can we not store the value returned by the subprogram into
	 * memory ? That way we don't have to call the same subproblem repeatedly. If
	 * value exists in memory, return it. Lets store the value returned by f(5) in
	 * the 5th array index for ease of computation. Index 5 will store the sum of
	 * fib(5) , i,e ( fib(4) + fib(3) ) Index 4 will store the sum of fib(4) , i,e (
	 * fib(3) + fib(2) ) Index 3 will store the sum of fib(3) , i,e ( fib(2) +
	 * fib(1) ) Index 2 will store the sum of fib(2) , 11 Index 1 will store the sum
	 * of fib(1) , 1 Index 0 will remain emty. So we should create an array of n+1
	 * size. We pass the memory while invoking the recursive function. We need to
	 * create an aux method in which we can pass the memory.
	 */
	public static int fib2(int n) {
		int[] mem = new int[n + 1];
		int fib1, fib2;
		if (n <= 2)
			return 1;
		fib1 = auxfib2(n - 1, mem);
		fib2 = auxfib2(n - 2, mem);
		return fib1 + fib2;
	}

	public static int auxfib2(int n, int[] mem) {
		int fib1, fib2;
		if (n <= 2)
			return 1;
		if (mem[n] != 0)
			return mem[n];
		mem[n] = fib1(n - 1) + fib1(n - 2);
		return mem[n];
	}

	// Solution 3 : Fill the memory without recursion.
	public static int fib31(int n) {
		int[] mem = new int[n + 1];
		mem[1] = mem[2] = 1;
		for (int i = 3; i <= n; ++i)
			mem[i] = mem[i - 1] + mem[i - 2];
		return mem[n];
	}

	// Solution 4: Iterative approach. Remove memory and use three variables.
	public static int fib4(int n) {
		int prev1, prev2, fibn = 0;
		prev1 = prev2 = 1;
		if (n <= 2)
			return 1; // fibn = 1
		while ((n - 2) > 0) {
			fibn = prev1 + prev2; // fibn = 2, 3, 5 , 8, 13, 21
			prev1 = prev2; // prev1 = 1, 2, 3, 5, 8 , 13,
			prev2 = fibn; // prev2 = 2, 3, 5, 8, 13 , 21
			n--;
		}
		return fibn;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println("fib1(n) =" + fib1(n));
		System.out.println("fib2(n) =" + fib2(n));
		System.out.println("fib31(n) =" + fib31(n));
		System.out.println("fib4(n) =" + fib4(n));
	}

}
