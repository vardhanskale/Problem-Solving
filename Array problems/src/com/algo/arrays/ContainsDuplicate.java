package com.algo.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 * 
 * Example 1 : 
 *   Input: [1,2,3,1]
 *   Output: true
 *
 * Example 2 : 
 *   Input: [1,2,3,4]
 *   Output: false
 *  
 * Example 3 :   
 *   Input: [1,1,1,3,3,4,3,2,4,2]
 *   Output: true 
 */

public class ContainsDuplicate {

	/*
	 * Attempt 1 : Worst solution. TC (N2) : Compare each element with other.
	 * 
	 */
	public static boolean containsDuplicate1(int[] nums) {
		boolean containsDuplicate = false;
		int arrLength = nums.length;
		for (int i = 0; i < arrLength - 1; i++) {
			for (int j = i + 1; j < arrLength; j++) {
				if (nums[i] == nums[j])
					containsDuplicate = true;
				break;
			}
		}
		return containsDuplicate;
	}

	/*
	 * Attempt 2 : Sort and compare each adjacent element. Advantage -> Only single
	 * scan is needed. TC of sort is O(nLog n). TC for scan is O(n). Total TC : O(n log N + N ) = O(n Log n).
	 */
	public static boolean containsDuplicate2(int[] nums) {
		boolean containsDuplicate = false;
		Arrays.parallelSort(nums);
		int n = nums.length - 1;
		for (int i = 0; i < n; i++) {
			if (nums[i] == nums[i + 1]) {
				containsDuplicate = true;
				break;
			}
		}
		return containsDuplicate;
	}

	/*
	 * Attempt 3 : Use a hashset. Take each element in the array. If it exists in
	 * HashSet, return true, else add the element in hashset. 
	 * Advantage -> Search and Insert both have time
	 * TC -> O(n) -> When there are no duplicates. 
	 * SC -> O(n) -> In the worst case you may have to add N elements.
	 */
	public static boolean containsDuplicate3(int[] nums) {
		boolean containsDuplicate = false;
		Set<Integer> hset = new HashSet<>(nums.length);
		for (int x : nums) {
			if (hset.contains(x))
				return true;
		hset.add(x);
		}
		return containsDuplicate;
	}

	static int[] getArray(int arraySize) {

		int[] array = new int[arraySize];
		Random rand = new Random(0);
		for (int i = 0; i < arraySize - 1; i++)
			array[i] = rand.nextInt(arraySize);
		array[arraySize - 1] = array[arraySize - 2];
		System.out.println("Original contents of array" + Arrays.toString(array));
		return array;

	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] array = getArray(n);
		int j = 0;
		System.out.println("Contains duplicate two for loops: " + containsDuplicate1(array));
		System.out.println("Contains duplicate sort and compare : " + containsDuplicate2(array));
		System.out.println("Contains duplicate using hashSet : " + containsDuplicate3(array));
	}

}
