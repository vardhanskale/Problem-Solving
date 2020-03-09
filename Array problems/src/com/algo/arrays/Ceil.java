package com.algo.arrays;

import java.util.Arrays;
import java.util.Random;

public class Ceil {
	
    /* 
 	 * Attempt1 : Single scan. TC : O(N). Avoid this kind of programming. 
	 */
	public static int ceil1(int[] in, double d) {
		for(int i=0 ; i < in.length ; i++) {
			if ( in[i] >= d ) 
				return in[i]; 
		}
		return Integer.MAX_VALUE;
	}
 
	/* 
	 * Attempt2 : Single scan. TC : O(N). Divide & Prune. 
	 * We want to find the ceil of x, i,e value greater than X. 
	 */
	public static int ceil2(int[] in, double d) {
		int low = 0, high = in.length -1, mid;  
		while( low  < high ) {
			mid = (low + high)/2;
			if ( in[mid] == d) return in[mid];
			if ( in[mid] < d) {
				// Mid is useless and all elements to the left of mid are useless. 
				low = mid + 1; 
			} else {
				high = mid; 
			}
		}
		if ( in[low] > d)
			return in[low];
		else 
		    return Integer.MAX_VALUE;
		
		// We return Integer.MAX_VALUE; because we want the next higher value after 
	} 
	
	/*
	 * Populate the array with random numbers.
	 * Sort it. 
	 * Return a random number back which needs to be searched. 
	 */
	public static int testcase1(int[] in){
       //Fill random numbers in array. 
		Random r = new Random();
		for(int i=0; i < in.length; i++ )
			// Generate random number from 1 to 100. Do +1 to avoid 0. 
			in[i] = r.nextInt(100) + 1;  
		Arrays.parallelSort(in);
		return r.nextInt(100) + 1;
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n]; 
		int searchElem = testcase1(in); 
		System.out.println("Array" + Arrays.toString(in) );
		System.out.println("searchElem : " + searchElem );
		System.out.println("ceil1 : "+ ceil1(in,searchElem));
		System.out.println("ceil2 : "+ ceil2(in,searchElem));
	}

}
