package com.algo.sort;

import java.util.TreeSet;

/* 
  This code sorts an array of integers using Balanced BST 
  Recall that each element in BBST is inserted such that the tree remains balanced.
  Whenever the tree becomes imbalanced, we rotate the tree at the leaf node to make it balanced. 
  An in-order traversal of a BBST gives us a sorted result. 
  Luckily Javas's TreeSet class uses a kind of BBST called Red Black Tree. Let's leverage TreeSet for tree based sorting.  
  All we need to do is to insert elements in the tree and print them to get sorted output.
  The problem with treeSet is that it will not sort an array which has duplicate elements. 
  
  Analyzing time complexity : 
  Irrespective of how the input is given ( sorted or unsorted ) the tree is constructed the same way. 
  So the best and worst case is the same here. 
  Inserting any element in a BST tree takes log(n) time where n is the number of elements in the tree. 
  So to insert n elements it would take : log(1) + log(2) + log(3) + ....+ log(n)  
  Time complexity for in order traversal is : n 
  Total time complexity is logn! + n = O( nlogn )  
  At the end of the program we calculated the execution time to be : 
  For 1 million : Execution time is 0.878 seconds.
  For 10 million : Execution time is 18.071 seconds.
  For 100 million : Execution time is 73.839seconds.
  So we see that its not scalable even though its better than selection sort and insertion sort. 
   
 */

public class TreeSort {
	
	
	public static void treeSort(int[] in) {
		TreeSet<Integer> tset = new TreeSet<Integer>(); 
		// Take each element one by one from array and insert into the BBST
		for(int x : in) {
			tset.add(x);
			//System.out.println(tset);
		} 
		
		// Now that all the elements have been inserted in the
        int i = 0; 
		for ( int x : tset  ) {
			in[i++] = x ; 
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		
		/* Lets create a random input using our custom method
		   int[] in = ArrayUtils.uniqueRandomData(n);
		   ArrayUtils.display(in); // Display array before sorting
		   treeSort(in); 
		   ArrayUtils.display(in); // Display array after sorting
		*/ 
		
		/*
		   For 1 million : Execution time is 0.878 seconds.
		   For 10 million : Execution time is 18.071 seconds.
		   For 100 million : Execution time is 73.839 seconds.
		   So we see that its not scalable even though its better than selection sort and insertion sort.
		*/
		
		System.out.println("TreeSort : Lets analyze the time complexity now...");  
		int[] in2 = ArrayUtils.uniqueRandomData(n); 
		long start = System.currentTimeMillis(); 
		treeSort(in2);
		long end = System.currentTimeMillis();
		System.out.println("Time Taken when n = " + n + " is " + (end - start) / 1000.0  + " seconds");
	}
}
