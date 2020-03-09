package com.algo.arrays;

public class ContainsDuplicateII {

	/* https://leetcode.com/problems/contains-duplicate-ii/description/
	 * Given an array of integers and an integer k, 
	 * find out whether there are two distinct indices i and j 
	 * in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     * Example 1:
	 * Input: nums = [1,2,3,1], k = 3
     * Output: true
     * Example 2:
     * Input: nums = [1,0,1,1], k = 1
     * Output: true
	 */
	
	/*
	 * Solution1: Worst Solution. TC o(n2)
	 * First find if there are duplicates by comparing each element with the next. 
	 * If there are, then if i - j < k , return true.  
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
	   int n = nums.length ; 
	   for (int i = 0 ; i < n ; i++)
		   for (int j = i+1 ; j < n ; j++)
			   if( nums[i] == nums[j] )
				  if(  Math.abs(i - j) <= k ) 
					  return true; 
	   
	   return false;
	}
	
	/*
	 * Solution2 : Keep adding element in hashSet one by one. 
	 * If the 
	 * 
	 */
	
	  
	public static void main(String[] args) {
		int[] nums = {1,2,3,1,2,3};
		int k = 2;
		//int[] nums = {1,2,3,1};
		//int k = 3;
		System.out.println("Contains Duplicate Returned  : " + containsNearbyDuplicate(nums,k));
	}

}
