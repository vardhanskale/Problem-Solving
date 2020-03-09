package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/*
 * Problem: Given an array of n integers that represent size of n files, 
 *          find an efficient algorithm that returns minimum cost of merging n files. 
 *	Constraint 1:  You are allowed to merge two files at a time.
 * 	Constraint 2:  Cost of merging is proportional to sum of individual file size.
 */

/*
 *  Solution : My analysis revealed that greedy is the correct approach to solve this. 
 *  Approach 1 : 
 *  1.	To get the smallest elements in the array we need to sort it. 
 *  2.	As a result of adding two file, an intermediate file is getting generated. We store it back in the array.
 *  3.	Again you need to sort the array to get the two smallest files. 
 *  4.	Greedy needs some kind of order based thought. This dilemma will always be there is greedy choice. 
 *  5.	Let’s try the sorting algorithm and also the sorted data structure and compare which is best. 
 *  
 *  Approach 2:
 *  1. Use a sorted data structure - Priority Queue.  
 */

class Mycomparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return -o1.compareTo(o2);
	}

}

public class MinCostMerging {

	/*
	 * Solution1 : Instead of using a sorted data structure we use an unsorted data
	 * structure, sort contents and solve the problem
	 */
	public static int minCostMerging(int[] in) {
		// 1. We need to sort the array to get the two smallest element, but....
		// 2. If we remove lowest two from the array, we need to re arrange all the
		// elements, which is a pain. Solution ?
		// 3. What if I replace the first two elements with an infinity value so that
		// they appear at the last after sort.
		// 4. But when do we stop is the biggest question mark. So array is a biggest
		// pain if you want to do changes.
		// 5. It is easy to find two smallest elements, but when we sort it up, I should
		// remove these elements which is a problem with arrays.
		// 6. What if we start from right to left and use the array. Array :
		// [7,6,5,4,3,2,1], Remove : 2,1. Add 2+1.
		// 7. Issue 2 : How do we tell the array to sort in descending order in Java ?
		// 8. Answer : We have to pass our comparison logic using comparator, which is
		// again painful in Java.
		// 9. Arrays.sort(in, new Mycomparator()); While this will work, removal in
		// dynamically changing array is painful.
		// 10. Lets take link list as it allows easy insertion, removal and it is easy
		// to sort it.
		// 11. We need to make a tradeoff choice. We choose list.

		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int x : in)
			list.add(x);

		Collections.sort(list); // sort the elements in the list in ascending order.
		int totalCost = 0;
		while (list.size() > 1) { // List should have at least two elements , otherwise I cannot remove and add
									// both elements.
			int smallest1 = list.remove(0); // Removes the element at the first position in this list.
			int smallest2 = list.remove(0); // Removes the element at the first position in this list.
			list.add(smallest1 + smallest2); // Appends the specified element to the end of this list.
			Collections.sort(list); // Sorts in ascending order.Sorting will take nlogn + (n-1)log(n-1) +
									// (n-2)log(n-2)+...1log1 = O(n^2logn)
			totalCost += (smallest1 + smallest2); // We need to maintain overall total cost as well.
		}
		return totalCost;

	}

	/*
	 * Solution 2 : Instead of taking an unsorted DS, sorting it and managing the
	 * contents within the DS why don't we use a sorted DS which can manage the
	 * elements internally dynamically. When we add and remove an element in the DS,
	 * the adjustment of elements is taken care of by the data structure. From
	 * sortedSet,SortedMap and PriorityQueue what should we choose ? If you want to
	 * add or remove the smallest the best data structure is PriorityQueue. If you
	 * need Ceil Floor kind of operations we choose other sorted DS. We are using a
	 * heap its, which is implemented as PriorityQueue in Java. In Priority queue my
	 * data itself is a priority. Building a priorityQueue of n elements will take
	 * nlogn. The process of adding and removing takes nlogn. This process continues
	 * n times, which is 3nlogn = O(nlogn). Previous solution was n^2Logn.
	 * 
	 */

	public static int minCostMerging2(int[] in) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int x : in)
			pq.add(x);

		// Collections.sort(list); // Now we dont have to sort it. Adding in
		// priorityQueue is sufficient.
		int totalCost = 0;
		while (pq.size() > 1) { // List should have at least two elements , otherwise I cannot remove and add
								// both elements.
			int smallest1 = pq.remove(); // Min element is at the top of priority queue. It removes first smallest.
			int smallest2 = pq.remove(); // Again removes the first smallest from what is left.
			pq.add(smallest1 + smallest2); // Adds the new elements to its correct position automatically.
			// Collections.sort(list); // When we add in a priorityQueue, no sorting is
			// necessary.
			totalCost += (smallest1 + smallest2); // We need to maintain overall total cost as well.
		}
		return totalCost;

	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(0);
		for (int i = 0; i < n; i++)
			in[i] = r.nextInt(10) + 1; // Random number between 1-10
		System.out.println(Arrays.toString(in));
		System.out.println(minCostMerging(in));
		System.out.println(minCostMerging2(in));

	}
}
