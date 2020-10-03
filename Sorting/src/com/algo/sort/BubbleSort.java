package com.algo.sort;

/* 
 *  This code demonstrates the inefficient bubble sort algorithm.
 *  Irrespective of the type of input, the time and space complexity remains O(N2) .
 *  Logic : Keep comparing two adjacent elements of the array. 
 *  Either bubble biggest element to bottom or smallest one to the top.
 *  After n-1 comparisons the largest element will reach the end of array.
 *  After n-2 comparisons the second largest will reach second largest slot. 
 *  Total comparisons (n-1) + (n-2) + (n-3) + ... + 1 = (n-1)(n-2)/2 = O(N2) 
 *  The first for loop ensures that the largest element reaches the end of the array. 
 *  We need to keep doing that for the remaining elements. 
 */

public class BubbleSort {

	public static void bubbleSort(int[] in ) {
		
		for( int i = 0 ; i < in.length ; ++i) {
			for( int j = 0 ; j < (in.length - 1) - i ; ++j) {
				if ( in[j] > in[j+1] )
				ArrayUtils.swap(in, j, j+1);
			}
		}
		
	}
	
	public static void main(String[] args) {
	    int n = Integer.parseInt(args[0]);	
	    int in[] = ArrayUtils.uniqueRandomData(n);
		ArrayUtils.display(in);
	    bubbleSort(in); 
	    ArrayUtils.display(in);
	}

}
