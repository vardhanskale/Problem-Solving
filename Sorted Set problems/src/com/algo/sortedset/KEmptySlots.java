package com.algo.sortedset;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

/*
  * There is a garden with N Slots. In each slot there is a flower. The N flowers will bloom one by one
  * in N days. In each day there will be exactly one flower blooming and it will be the status of blooming 
  * since then. 
  * Given an array flowers consists of numbers from 1 to N. Each number in the array represents the place where 
  * the flower will open in that day. For example, flower[i] = x means that the unique flower that blooms at day i 
  * will be at position x, where i and x will be in the range of 1 to N. 
  *
  * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming
  * and also the number of flowers between them is k and these flowers are not blooming. 
  */

public class KEmptySlots {

   /* 
    * in array consists of the order of blooming of flowers.
    * 0th index means first day. First index means second day and so on.
    * Strategy : Go to each position, check before k and after K.
    * If you dont find it there find the next element. 
    * 
    * TC : Theta(n^2) , SC : Theta(n)
    */  
	public int kEmptySlots1(int[] in, int k) {
		
		return -1;
	}
	
	
	// let current index is 2 and ceil is 3 . We need the gap between them by excluding both elements 
	// So we wont do 3-2 but ( (3-2) - 1 ). Thats what we do at line 43 and 48.
	// TC : At each slot we do floor and ceil which takes logN. So TC:Theta(n log n ) 
	// SC : Theta(n)
    public static int kEmptySlots2(int[] in, int k) {
    	
		TreeSet<Integer> tset = new TreeSet<Integer>(); 
    	for ( int day = 0 ; day < in.length ; ++day ) {
    		// What should we do each day ? 
    		// For this given position or slot , check the immediate previous or next slot where a flower bloomed. 
    		Integer floor = tset.floor(in[day]); // Which flower bloomed immediately before todays day ? It may or may not be there.
    		// If floor is available and the gap between floor and in[i] is k, then we found the day. Return it.
    		if ( floor != null && in[day]-floor-1 == k )
    			return day+1;  // Since day starts from index 0 we add 1 
    		
    		// Do the same for ceil, which may or may not exist.  
    		Integer ceil = tset.ceiling(in[day]); 
    		if ( ceil != null && ceil - in[day]-1 == k) // This is the only ticky part
    			return day + 1; 
    		// If both ceil and floor do not satisfy the condition
    		// add the element to treeSet , go to the next day and again 
    		// check if there are k empty slots between two blooming flowers.
    		tset.add(in[day]);
    	}
		return -1; // We don't have such kind of day where there are  k empty slots.
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); 
		// For N days lets give random data. 
		int[] in = ArrayUtils.uniqueRandomData(n);
		System.out.println("Array containing days on which flower bloomed " + Arrays.toString(in)); 
		int k = new Random().nextInt(n) + 1; 
		System.out.println("k = " + k);
		System.out.println("Day on which K empty slots are available "+ kEmptySlots2(in,k)); 
	}

}
