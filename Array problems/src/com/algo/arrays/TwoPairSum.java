package com.algo.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/*
 * Problem: Given an array of n Integers find an efficient algorithm that determines 
 * whether there exists a pair of elements whose sum is S. 
 * I/P : S =9 
 * Array = [2,4,1,5,3,6,2] 
 * O/P : true 
 */

public class TwoPairSum {
	/* 
	 * Attempt 1 : Complete search / Brute Force Approach. TC O(N^2) 
	 * 9-2 = 7. Search if 7 is present in the array. N-1 comparisons.  
	 * 9-4 = 5. Search if 5 is present in the array. N-2 comparisons. 
	 * 9-1 = 8. Search if 8 is present in the array. N-3 comparisons. 
	 * Don't use such solution in production. 
	 */
	public static boolean twoPairSum1(int[] in, int s) {
		int n = in.length, itemToSearch;
		for ( int i = 0 ; i < n-1 ; ++i) {
		    itemToSearch = s - in[i]; 
		    // now search this item in the rest of the array and return true if found.
		    for ( int j = i + 1 ; j < n ; ++j)
	            if ( in[j] == itemToSearch )
		        	 return true;  
		}
		return false;
	}
	
	/*
	 * Attempt 2 : Sort and binary search. 
	 */
	
	public static boolean twoPairSum2(int[] arr, int s) {
		int n = arr.length, itemToSearch, searchValue;
		Arrays.parallelSort(arr);
		for ( int i = 0 ; i < n ; i++) {
		    itemToSearch = s - arr[i]; 
		    // now search this item in the rest of the array and return true if found.
		    // Arrays.binarySearch(arr,fromIndex,ToIndex,Key);
		    searchValue = Arrays.binarySearch(arr,i+1,n,itemToSearch);
		    if ( searchValue >= 0 ) 
		    	return true; 
		}
		return false;
	}
	
	/*
	 * Attempt 3 : Use HashSet -> Does not allow duplicates. 
	 * Has a add and contains methods to add an object and check if it exists. 
	    1) Similarities b/w HasTable, HashSet and HashTable. 
           a) None of them allow duplicate keys. 
        2) Similarities b/w HashMap and HashTable
           a) Both HashMap and HashTable store key-value pairs. 
           b) Both Hashset and HashMap stores only unique keys
           c) These unique keys can have duplicate values.
        3) Differences between HashMap and HashTable
 	       a) HashMap allows null for both key and value.
	       b) Hashtable does not allow null for both key and value. 
              It will throw NullPointerException.
	       c) Hashtable is synchronized & slow. 
              Only one thread can access in one time.
           d) HashMap is not synchronized & faster. 
           e) Use Collections.synchronizedMap(new HashMap<K, V> ())
           f) TC: O(N): Do one scan and add all array items to hashset.
	 * TC : O(N) : Do one scan and add all array items to hashset
	 */
	
	public static boolean twoPairSum3(int[] arr, int s) {
		HashSet<Integer> hset = new HashSet<>(); 
		for (int i=0; i < arr.length ; i++){    
		    // if hashSet contains the difference, another element of pair is found. 
		    if( hset.contains(s-arr[i]))
		       return true;
		        // add the element to hashSet.
		       hset.add(arr[i]);
		    }
		return false; 
	} 

	/*
	 * Attempt 3 : Use TreeSet -> Stores unique element as per natural sort order unless 
	 * a different sort order is specified in constructor using a comparator. 
	 * Why is treeSet faster than hashSet?
	 */ 
	public static boolean twoPairSum4(int[] arr, int s) {
		Set<Integer> hset = new TreeSet<>(); 
		for (int i=0; i < arr.length ; i++){    
		    // if hashSet contains the difference, another element of pair is found. 
		    if( hset.contains(s-arr[i]))
		       return true;
		        // add the element to hashSet.
		       hset.add(arr[i]);
		    }
		return false; 
	} 
	
	public static int[] testCase1(int n) {
		int[] in = new int[n];
		Random r = new Random(0);
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(100)+1;
		return in;
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);; 
		int[] in = testCase1(n);
		int s = 0;
		int [] array = {2,4,1,5,3,6,2}; 
		long startTime = System.currentTimeMillis(); 
		System.out.println("twoPairSum1: Bruite Force : " + twoPairSum1(in,s)); 
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken:" + (endTime-startTime)/1000.0 + " secs");
		
		startTime = System.currentTimeMillis(); 
		System.out.println("twoPairSum2: Sort & Binary Search : " + twoPairSum2(in,s)); 
		endTime = System.currentTimeMillis();
		System.out.println("Time taken:" + (endTime-startTime)/1000.0 + " secs");
		
		startTime = System.currentTimeMillis(); 
		System.out.println("twoPairSum3: HashSet : " + twoPairSum3(in,s)); 
		endTime = System.currentTimeMillis();
		System.out.println("Time taken:" + (endTime-startTime)/1000.0 + " secs");
		
		startTime = System.currentTimeMillis(); 
		System.out.println("twoPairSum3: TreeSet : " + twoPairSum3(in,s)); 
		endTime = System.currentTimeMillis();
		System.out.println("Time taken:" + (endTime-startTime)/1000.0 + " secs");
		
	}

}
