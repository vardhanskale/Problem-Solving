package com.algo.sort;

public class MergeSort {

	
	/* 
	 * Merge sort is a recursive thought in which we divide the given input array into two parts in each successful call.  
	 * Finally we merge parts to get sorted array.
	 * Input : 8 7 1 9 4 3 2 6
     * Instead of sorting the problem ,keep dividing it into smaller parts until there is only one element left in the array.
     * Then we merge the array such that the elements are sorted in them. 
	 * Lets create an aux method with custom arguments to create boundaries for left and right problem. 
	 * 
	 */
	
	public static void mergeSort(int[] in) {
		// The aux array will store the values from the array.
		int[] aux = new int[in.length];
		auxMergeSort(in, 0, in.length-1, aux); 
	}
	
	private static void auxMergeSort(int[] in , int l , int r , int[] aux ) {
	/* This loop will give the intution of the algo  
		for ( int i = l ;  i <= r ; ++i )
			System.out.print(in[i] + " ");
		System.out.println();
	*/
		
	/* 
	 *  Base Case to stop recursive call is when single element is available.  
	 *  This happens when left and right pointer is pointing to the same element. 
	*/
		if ( l >= r ) 
			return ;    // This happens when no element is available in array ( or when array is not divisible ).
		
		// Divide the array into two parts. 
		int m = (l + r) / 2; 
		auxMergeSort(in, l, m, aux);   // Left subproblem. Left index to middle index. 
		auxMergeSort(in, m + 1, r, aux); // Right subproblem. 
		merge(in, l, m, r, aux);
	}
	
	
   /*
    *  Merge these two parts
    *  Keep two pointers, one at 1 and other at 2 . 
    *  Compare elements and keep moving pointers forward.
    *  Store the value in the third array. 
    *  Case 1 : [ 1,3,5 | 2,4,6 ]
    *  Case 2 : [ 1,2,3 | 4,5,6 ] ,  In this case the remaining elements are copied to aux array. 
    *  Case 3 : [ 4,5,6 | 1,2,3 ] ,  In this case the first part is copies to aux array. 
    *   
    */
	
	private static void merge(int[] in, int l, int m, int r, int[] aux) {
		int i = l , j = m + 1 , k = 0; 
		// Keep repeating till you reach end of first part or you reach end of second part.
		while (i  <=m && j <= r ) {
		if ( in[i] < in[j] ) 
			aux[k++] = in[i++]; 
		else 
			aux[k++] = in[j++]; 
		}
		
	    // If we reach end of left part or right part there will be leftover elements. 
	    // You got to copy those elements into the auxiliary array. 
		
		while ( i <= m ) // There are leftover elements in the left part.
			aux[k++] = in[i++]; 
		while ( j <= r ) // There are leftover elements in the right part.	
		    aux[k++] = in[j++]; 
		
		// In recursion we are using same input array 
		// We need to copy from aux array back to original array.
		i = 0; 
		while ( i < k)
			in[l++] = aux[i++]; 
	}

	public static void main(String[] args) {

      int n = Integer.parseInt(args[0]);
      int[] in = ArrayUtils.uniqueRandomData(n);
      ArrayUtils.display(in); 
      mergeSort(in); 
      ArrayUtils.display(in); 
	}

}
