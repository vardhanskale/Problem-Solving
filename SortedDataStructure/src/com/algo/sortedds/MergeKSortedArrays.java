package com.algo.sortedds;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem : Merge K sorted Arrays.
 *
 * Lets first solve this problem for two arrays, K = 2. 
 * 
 * The first  thought that comes to mind is this : 
 * 1. Find the smallest element in two arrays. 
 * 2. Extract that element from array. 
 * 3. Store the extracted element in the third array which has a size equal to sum of both the array sizes. 
 * 4. Go back to step 1 and repeat till end of any one array. 
 * 5. Copy the remaining contents of the second array into the final array. 
 * 
 * When K gets big, finding the smallest element in K arrays gets is not trivial. 
 * Thats where sorted data structures like min - heap come to our rescue. 
 * If we push/store all the first K elements of K arrays in a sorted data structure,
 * and ask the data structure to give us the smallest element, it simplifies things. 
 *
 * However, removing the first element from each array to insert in sorted DS does not seems to be a 
 * great strategy for two reasons :  
 * 1) We don't want to end up rearranging the elements of the array again.
 * 2) After removing the smallest element from PriorityQueue, how do we know the array which contained that 
 *    element. There is no way to find that. So we need a way to remember the elements in each of these K 
 *    arrays that need to be compared with rest of the array indexes. 
 *     
 * Lets create a wrapper class 'ArrayContainer' which contains the array and an index value. 
 * The index will point to the smallest element in the array which is not stored in the third target array.
 * Once we remove the object from the sorted data structure, extract the first array element and store it in 
 * target array, the  index value in 'ArrayContainer' would be incremented to remember the next smallest element 
 * in that array.  
 * 
 * These are our steps :
 * 1. Add the 'ArrayContainer' instances in a priorityQueue. 
 *    They would be sorted based on the smallest element in their arrays.
 * 2. pop the first object, get the first element from its array and store it in the target array.    
 * 3. If ArrayContainer's index is less than the size of its array : 
 *      3.1 Create a new instance of this object by passing the array and ++Index to it. 
 *      3.2 Store the object back to the prirityQueue.
 * 4. Keep repeating steps 2 and 3 till the priorityQueue is not empty.  
 * 5. When the priorityQueue is empty, our target array has all the sorted elements.     
 */ 


class ArrayContainer implements Comparable<ArrayContainer> {
	protected int index = 0; 
	protected int[] arr;
	
	public ArrayContainer(int[] arr, int index) {
		this.arr = arr; 
		this.index = index;
	}

	@Override
	public int compareTo(ArrayContainer ac) {
		return this.arr[this.index] -  ac.arr[ac.index];
	}
}

public class MergeKSortedArrays {

	// Accepts a 2D array which needs to be merged and returns a sorted array.  
    public static int[] mergeKSortedArrays(int[][] arr) {
		// PriorityQueue is heap in java 
    	int total = 0; // Will hold the total size of target array. 
    	PriorityQueue<ArrayContainer> pq = new PriorityQueue<>(); 
    	
    	//add arrays to heap. 
    	for ( int i = 0 ;  i < arr.length ; i++ ) {
    		pq.add(new ArrayContainer(arr[i], 0));
    		total = total + arr[i].length; 
    	}
    		
    	int[] result = new int[total];
    	int m = 0; 
    	while ( !pq.isEmpty() ) {
    		ArrayContainer ac = pq.poll(); // remove the element from the array
    		result[m++] = ac.arr[ac.index];
    		
    		// If the array still has more elements which are not part of result array,
    		// put it back in pqueue. 
    		if (ac.index < ac.arr.length -1) {
    			pq.add(new ArrayContainer(ac.arr, ac.index+1)); 
    		}
    	}
    	return result; 
	}
	
	
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };
		int[] result =  mergeKSortedArrays(new int[][] { arr1, arr2, arr3}); 
        System.out.println(Arrays.toString(result)); 
	}

}
