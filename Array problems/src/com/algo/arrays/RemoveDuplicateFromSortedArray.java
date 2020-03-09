package com.algo.arrays;

import java.util.Arrays;
import java.util.Random;

public class RemoveDuplicateFromSortedArray {
	/*
	 * Given a sorted array nums, remove the duplicates in-place such that each
	 * element appear only once and return the new length.Do not allocate extra
	 * space for another array, you must do this by modifying the input array
	 * in-place with O(1) extra memory.
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
	 * description/
	 */

	/*
	 * Attempt 1 : Lets first solve with two arrays and then optimize it. Imagine
	 * placing two fingers of right hand on the array and sliding it to the right.
	 * The index finger representing the index i. When i != i+1 we have skipped all
	 * duplicates values copy i element to second array's first index and increment
	 * the second arrays index j. If consecutive elements are same we have to keep
	 * moving first element ahead and no nothing. Keep comparing two consecutive
	 * elements till first element reaches second last slot of array. This solution
	 * is not as intuitive as Attempt 3 below
	 */
	public static int removeDuplicates1(int[] nums) {
		int n = nums.length - 1;
		int[] out = new int[nums.length];
		int j = 0; // Counter for second array

		for (int i = 0; i < n; ++i) {
			if (nums[i] != nums[i + 1])
				out[j] = nums[i];
			j++;
		}

		/*
		 * If the last two elements are same, the second last element would not be
		 * copied out array. If the last two elements are same, then the second last
		 * would have been copied, but not the last. So, in both the cases we need to
		 * copy the last element to the out array.
		 */
		out[j] = nums[n];
		System.out.println("Modified contents of array after removing duplicates : " + Arrays.toString(out));
		return j;
	}

	/*
	 * Approach 2 : Use a single array to remove duplicates. Instead of copying
	 * value from first array to second arrays j'th index copy the value in the jth
	 * element of the first array itself. Everything else is the same. So we start
	 * pushing unique values in index 0,1,2.
	 */

	public static int removeDuplicates2(int[] nums) {
		int n = nums.length - 1;
		int j = 0; // Counter for second array

		for (int i = 0; i < n; ++i) {
			if (nums[i] != nums[i + 1])
				nums[j] = nums[i];
			j++;
		}

		/*
		 * If the last two elements are same, the second last element would not be
		 * copied out array. If the last two elements are same, then the second last
		 * would have been copied, but not the last. So, in both the cases we need to
		 * copy the last element to the out array.
		 */
		nums[j] = nums[n];
		System.out.println("Modified contents of array after removing duplicates : " + Arrays.toString(nums));
		return j;
	}

	/*
	 * Attempt 3 : Another way to solve the same problem is to keep skipping
	 * duplicate values.
	 */

	public static int removeDuplicates3(int[] nums) {
		int n = nums.length - 1;
		int j = 0, i; // Counter for second array

		for (i = 0; i < n; ++i) {
			if (nums[i] == nums[i + 1]) // skip and move ahead.
				continue;
			nums[j++] = nums[i];
		}
		/*
		 * If the last two elements are same, the second last element would not be
		 * copied out array. If the last two elements are same, then the second last
		 * would have been copied, but not the last. So, in both the cases we need to
		 * copy the last element to the out array.
		 */
		System.out.println("Modified contents of array after removing duplicates : " + Arrays.toString(nums));
		return j;
	}

	/*
	 * Attempt 4 : Runner Technique : Finding cycles in the array.
	 * https://medium.com/solvingalgo/solving-algorithmic-problems-find-a-duplicate-
	 * in-an-array-3d9edad5ad41
	 */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] array = getArray(n);
		int j = 0;

		System.out.println("1. Unique elements in aray : " + j);
		j = removeDuplicates1(array);

		System.out.println("2. Remove duplicates in second array : " + j);
		j = removeDuplicates2(array);

		System.out.println("3. Remove duplicates in second array : " + j);
		j = removeDuplicates3(array);

	}

	static int[] getArray(int arraySize) {

		int[] array = new int[arraySize];
		Random rand = new Random(0);
		for (int i = 0; i < arraySize - 1; i++)
			array[i] = rand.nextInt(arraySize);
		array[arraySize - 1] = array[arraySize - 2];
		Arrays.parallelSort(array);
		System.out.println("Original contents of array" + Arrays.toString(array));
		return array;

	}
}
