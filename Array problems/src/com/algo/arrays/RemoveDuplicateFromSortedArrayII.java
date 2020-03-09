package com.algo.arrays;

import java.util.Arrays;
import java.util.Random;

  /*
   * Given a sorted array, remove duplicates from the array in-place such that each 
   * element appears at most twice, and return the new length. Do not allocate extra 
   * space for another array,you must do this by modifying the input
   * array in-place with O(1) extra memory.
   * 
   * Given nums = [1,1,1,2,2,3],your function should return length = 5,
   * with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
   * 
   * Given array [1, 1, 1, 3, 5, 5, 7]
   * The output should be 6, with the first six elements of the array being [1, 1, 3, 5, 5, 7]
   * 
   * It doesn't matter what you leave beyond the returned length.
   * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
   */

public class RemoveDuplicateFromSortedArrayII {

	/*
	 * Attempt1 : This is an extension of RemoveDuplicateFromSortedArray problem .
	 * Imagine holding two fingers of right hand on the sorted array in an inverted
	 * V shape such that the index finger points to the first element i, and the
	 * middle finger pointing to i+2. If the two values are not equal, copy of the
	 * content of cell pointed to by index finger to second array's j index. Slide
	 * your fingers to the right. i,e increment i & j. This solution is not as
	 * intuitive as Attempt3.
	 * 
	 */

	public static int removeDuplicates1(int[] nums) {
		int n = nums.length - 2;
		int[] out = new int[nums.length];
		int j = 0; // Counter for second array

		for (int i = 0; i < n; ++i) {
			if (nums[i] != nums[i + 2])
				out[j] = nums[i];
			j++;
		}

		/*
		 * If the last two elements are same, the second last element would not be
		 * copied out array. If the last two elements are same, then the second last
		 * would have been copied, but not the last. So, in both the cases we need to
		 * copy the last element to the out array.
		 */
		out[j++] = nums[n];
		out[j] = nums[nums.length - 1];
		System.out.println("Modified contents of array after removing duplicates : " + Arrays.toString(out));
		return j;
	}

	/*
	 * Attempt 2 : Instead of using an external array we copy the values in the same
	 * array.
	 */
	public static int removeDuplicates2(int[] nums) {
		int n = nums.length - 2;
		int[] out = new int[nums.length];
		int j = 0; // Counter for second array

		for (int i = 0; i < n; ++i) {
			if (nums[i] != nums[i + 2])
				nums[j] = nums[i];
			j++;
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
	 * Attempt 3 : Lets make the solution more intuitive. [1,1,1,2,2,3] If the
	 * current element is equal to the element at index i+2, then skip the current
	 * element because it is repeated more than twice. If not, copy the content of
	 * index i into j and increment both indexes.
	 * 
	 */
	public static int removeDuplicates3(int[] nums) {
		int n = nums.length - 2;
		int[] out = new int[nums.length];
		int j = 0; // Counter for second array

		for (int i = 0; i < n; ++i) {
			if (nums[i] == nums[i + 2])
				continue;
			nums[j] = nums[i];
			j++;
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
	 * Attempt 5 : Using slow fast pointer
	 * https://medium.com/leetcode-cracker/80-remove-duplicates-from-sorted-array-ii
	 * -34ac6d25bc2a
	 */

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

}
