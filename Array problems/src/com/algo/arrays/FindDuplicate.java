package com.algo.arrays;

import java.util.Arrays;
import java.util.Random;

public class FindDuplicate {

	/*
	 * Problem : Given an array of n integers in which each element is in the range
	 * of 1 to n-1 find an efficient algorithm that returns a duplicate.
	 */

	public static void main(String[] args) {
		int arraySize = Integer.parseInt(args[0]);
		int[] arr = getArray(arraySize);
		System.out.println("Approach1 : Compare all elements : Duplicate Value is : " + findDuplicate1(arr));
		System.out.println("Approach2 : Sort and Compare     : Duplicate Value is : " + findDuplicate2(arr));
		System.out.println("Approach3 : Use second Array     : Duplicate Value is : " + findDuplicate3(arr));
		System.out.println("Approach4 : Use negation trick   : Duplicate Value is : " + findDuplicate4(arr));
	}

	static int[] getArray(int arraySize) {

		int[] array = new int[arraySize];
		Random rand = new Random(0);
		for (int i = 0; i < arraySize - 1; i++)
			array[i] = rand.nextInt(arraySize);
		array[arraySize - 1] = array[arraySize - 2];
		System.out.println("Contents of array" + Arrays.toString(array));
		return array;
	}

	/*
	 * Approach1 : Complete search. Compare each element with another to find
	 * duplicate. TC - O(n2). Not an acceptable solution. Returns the first
	 * duplicate element if found. Otherwise returns -1.
	 */
	private static int findDuplicate1(int[] array) {

		for (int i = 0; i < array.length - 1; ++i) {
			for (int j = i + 1; j < array.length; ++j) {
				if (array[i] == array[j])
					return array[i];
			}
		}
		return -1;
	}

	/*
	 * Approach 2 : Sort array and compare each element with next from first to
	 * last. TC : logN + n . Log N for sorting and N comparison. Crux of LogN is
	 * that as your input size doubles, the time to execute the algorithm increases
	 * by only 1 unit.
	 */
	private static int findDuplicate2(int[] array) {
		Arrays.parallelSort(array);
		for (int i = 0; i < array.length - 1; ++i) {
			if (array[i] == array[i + 1])
				return array[i];
		}
		return -1;
	}

	/*
	 * Approach 3 : Use a separate second array. Read input array from left to
	 * right. Set secondArray[ inputArray[i] ] = 1; Do this from left to right. If
	 * the second array already has 1, it means inputArray[i]has duplicate value.
	 * This solution will work for only this problem because the index value is less
	 * than n. TC : SC : N
	 */
	private static int findDuplicate3(int[] array) {
		byte secondArray[] = new byte[array.length];
		for (int i = 0; i < array.length; ++i) {
			if (secondArray[array[i]] == 1)
				return array[i]; // Duplicate found. Return it.
			else
				secondArray[array[i]] = 1; // set 1
		}
		return -1;
	}

	/*
	 * Approach 4 : My requirement is to do one scan and avoid extra array space.
	 * Use negation trick. Instead of going to other array negate the same arrays
	 * value. Since we are negating the array index value, we will take absolute
	 * values of indexes. For each value of i, set secondArray[ Math.abs(
	 * inputArray[i] ) ] *= -1; This solution is specific to problems where index is
	 * less than n. Do this from left to right. If the second array already has 1,
	 * it means inputArray[i] has duplicate value. TC : SC : N
	 */

	private static int findDuplicate4(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			int tmp = Math.abs(array[i]);
			if (array[tmp] < 0)
				// Duplicate found. Return it.
				return tmp;
			else
				array[array[i]] *= -1; // set 1
		}
		return -1;
	}
}
