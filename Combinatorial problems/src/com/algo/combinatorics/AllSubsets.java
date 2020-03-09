package com.algo.combinatorics;

public class AllSubsets {
	
	/*
	 * Solution 1 : Slot based approach. 
	 * Make a tree diagram such that in the first slot you include and exclude A. 
	 * In the second though you include and exclusive B and keep doing it for the next slots. 
	 * This approach leads itself to a fixed length tree. 
	 * Once a tree is formed, finding a solution is easy. Its just a complete search tree problem 
	 * in which we need to do a root to leaf path computation. While going from root to leaf,  
	 * every subproblem needs to know the total depth of the tree and the current depth of recursion
	 * in order to terminate the recursion. So we need to pass those two variables to sub problems. 
	 * In this case the total depth of the tree is the length of the string. Each character in the 
	 * string represents the depth. 
	 *  
	 */
	
	public static void allSubsets1(String in) {
	       auxAllSubsets1(0, in, ""); 
	} 
	private static void auxAllSubsets1(int depthOfRecursion, String in, String result) {
	      if (depthOfRecursion == in.length() ) {
	         System.out.println(result); 
	         return; 
	      }
	      // Left Subtree  : Exclude: Pass to child whatever you get from parent.
	      auxAllSubsets1(depthOfRecursion+1, in, result);  
	      // Right Subtree : Include  : Add “A”, “B”, “C” to the variable passed by parent. 
	      auxAllSubsets1(depthOfRecursion+1, in, result + in.charAt(depthOfRecursion)) ; 
	}

	/* 
	 * Solution2 : Recursion based approach. In this approach all subsets of “abcd” equals all subsets starting “a” + 
	 * all subsets starting “b” + all subsets starting “c” + all subsets starting “d”.
	 * This second approach produces variable length tree and terminates when the leaf node is empty.
	 * So I don't have to pass a variable to check the depth of the tree. An empty string is a terminating condition. 
	 * Also I need to print the string value at each node level instead of printing at the last. 
	 * This is because each node itself is a subset. 
	 * 
	 */
	 
	public static void allSubsets2(String in) {
	       auxAllSubsets2(in, ""); 
	} 
	private static void auxAllSubsets2(String in, String result) {
		System.out.println(result); 
		if (0 == in.length())
	       return; 
	    for (int i = 0 ; i < in.length() ; ++i )  
	    auxAllSubsets2(in.substring(i+1), result + in.charAt(i)) ; 
	}
	
	public static void main(String[] args) {
		allSubsets1(args[0]);
		System.out.println("----------------");
		allSubsets2(args[0]);
	}
}
