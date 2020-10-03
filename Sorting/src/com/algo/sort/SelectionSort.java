package com.algo.sort;

public class SelectionSort {

	public static void selection_sort(int[] in) {
		/*
		 *  Find the smallest element in the array and bring to first position. 
		 *  We don't know which is the smallest element to begin with. 
		 *  Lets assume that the first element is smallest and compare the rest of the elements with it.
		 *  With that assumption we try to scan the array from the next element.
		 *  Keep doing this till the end
		 *  
		 *  Best Case Scenario :
		 *  Whatever input you give it needs to scan the entire array to get all items in order. 
		 *  Irrespective of what input we give to this algorithm ( sorted, unsorted ) it still scans all elements till the end.
		 *  So our time and space complexity will be the same.
		 *  Initially we do ( n-1 ) comparison then ( n-2 ) , then ( n - 3 ). 
		 *  Total Comparisions = (n-1) + (n-2) + (n-3) +.....+ 1   = (n-1)(n-2)/2  = O(N2)
		 */    
		
		for ( int i = 0 ; i < in.length ; ++i ) {
			int minIndex = i ; 
			for ( int j = i + 1 ; j < in.length ; ++j ) {
				if ( in[j] < in[minIndex])
					minIndex = j; 
			}
			ArrayUtils.swap(in, i, minIndex);
			ArrayUtils.display(in);
		}
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		/* Uncomment it to execute the algorithm
		int[] in = ArrayUtils.uniqueRandomData(n); 
		ArrayUtils.display(in);
		selection_sort(in);
		ArrayUtils.display(in);
		*/ 
		
		/* Test the performance of the algorithm */ 
		/* Change the input size from 10,1000, 100000 and measure the time complexity */ 
		
		int[] in = ArrayUtils.revSortData(n); 
		long start = System.currentTimeMillis(); 
		selection_sort(in); 
		long end = System.currentTimeMillis(); 
		System.out.println( "Time Taken " + (end - start)/1000.0 + "seconds");
	}

}
