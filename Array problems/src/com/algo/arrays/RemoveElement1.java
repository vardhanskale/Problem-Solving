package com.algo.arrays;

import java.util.Arrays;
import java.util.Random;

public class RemoveElement1 {
  /*
   * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
   * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
   * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
   * 
   * Given nums = [3,2,2,3], val = 3,
   * Your function should return length = 2, with the first two elements of nums being 2.
   * It doesn't matter what you leave beyond the returned length.
   * https://leetcode.com/problems/remove-element/description/
   */
	
   /* Attempt 1 : We start by using two arrays and then modify it to use one array in next attempt. 
    * Keep moving single pointer from left to right. If content in array cell is not equal to 'val', copy it to second array
	*   
	*/
    public static int removeElement1(int[] nums, int val) {
        int i =0, j=0, n = nums.length;
        int[] out = new int[n]; 
        for ( i = 0 ; i < n ; i++ )
        {
          if (nums[i] == val )
        	continue;
          out[j] = nums[i]; 
          j++; 
        }
		System.out.println("Elements in array after removing value: " + Arrays.toString(out)); 
		return j;
    }
    
    
    /* Attempt 2 : Instead of using a second array, copy the contents in the first array itself. 
     * Keep moving single pointer from left to right. If content in array cell is not equal to 'val', copy it to second array
 	*   
 	*/
     public static int removeElement2(int[] nums, int val) {
         int i =0, j=0, n = nums.length;
         for ( i = 0 ; i < n ; i++ )
         {
           if (nums[i] == val )
         	continue;
           nums[j++] = nums[i]; 
         }
 		System.out.println("Elements in array after removing value: " + Arrays.toString(nums)); 
 		return j;
     }
     
     
    static int[] getArray(int arraySize) {
		
		int[] array = new int[arraySize];
		Random rand = new Random(0);	
		for ( int i = 0 ; i < arraySize-1 ; i++ ) 
		   array[i] = rand.nextInt(arraySize);
		   array[arraySize-1] = array[arraySize -2]; 
		   System.out.println("Original contents of array" + Arrays.toString(array));
		 return array;	
		 
	 }
		
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] array = getArray(n);
		int j = 0;
		System.out.println("Removing element : " + array[n - 1]);
		System.out.println("ArraySize : " + removeElement1(array, 0));
		System.out.println("ArraySize : " + removeElement2(array, 0));
	}

}
