package com.algo.sort;

import java.util.Random;

public class QuickSort {

    /* Detailed explanation of logic is at the end
   
     */ 
	
	
	public static void quickSort(int[] in) {
		
	/* 
	 *  In quickSort we determine the position of an element & identify left and right subproblems with a boundary. 
     *  This function only takes a single input array, so we cannot use it to specify boundary of left and right problem. 
     *  Lets make aux function. 
     *  
     */    
		auxQuickSort(in, 0, in.length - 1 ); 
		
	}
	
	/*   
	 *  We start by picking some element in the array called pivot, usually  its the first element.
	 *  Next step is to partition the array around the pivot.   
	 *  
	 */
	private static void auxQuickSort(int[] in, int l, int r) {
		
		/* Whenever a call is made, how does the content of the tree look like 
		System.out.println("Displaying elements left of pivot");
		for ( int i = l ;  i <= r ; ++i) {
			System.out.print(in[i] + " "); 
		}
		System.out.println();
		*/
		
	    /*   Recursive calls need to end when we get an empty array or one element.
	     *   if (l == r )   => Single element array. 
         *   if ( l < r )   => There are elements in the array. 
	     *   if ( l > r )   => There are no elements in the array 
	     */  
		if ( l >= r ) // Single element or zero element array. 
		   return; 
		int pindex = get_random_pivot_index(in , l, r); // Returns the pivot index. 
		int p = partition(in, l, r, pindex);     // p represents the index in array from where we need to partition.
		auxQuickSort(in, l , p-1); 
		auxQuickSort(in, p+1 , r ); 
		
	}
	
	private static int get_fixed_pivot_index(int[] in, int l, int r) {
		return l;  // Returning first element as pivot gives one sided complexity of n2.
	}
	
	// lets say l  = 2 , r = 3 
	private static int get_random_pivot_index(int[] in, int l, int r) {
		Random random  = new Random(); 
		// r-l+1 = 3-2+1 = 2 random numbers need to be generated. 
		// add l to it to get the range from l to r.
		return random.nextInt(r-l+1) + l ; 
	}
	
	/*
	  How to do partitioning ?
      [6,7,1,9,4,3,2,8]

      Our ultimate goal is : 
      [ All elements less than 6 ] 6 [ All elements greater than 6 ] 

      Our pivot element is 6. We need to place it in its correct location. 
      Let's create a pointer 'current' which initially points to right of pivot element, in our case 7 and keeps incrementing.
      Let's create a pointer 'lastMinium',  which points to last minimum before 6 and initially points at pivot. 
      Our goal is to move both pointers forward & swap elements such that 
      1) lastMinimum points to last element before the final index postion of 6.
      2) Current travels from start to end of array.
 
      Now start incrementing current, the pointer which points at 7. 
      If the value at 'current' is greater than 6, we don't have to do anything. Keep incrementing 'current'.
      If the value at 'current' is less than 6, we swap it with current so that bigger elements are pushed at the right partition. 
      
      At this stage all elements till lastMin are less than 6 and all elements greater then lastMin are greater than 6.
      To bring 6 to its correct position swap 6 with lastMin. You get : [2,1,4,3] 6 [9,8] 

	 */
	private static int partition(int[] in, int l, int r, int pindex) {
		 
		/*  
		 * int pivot = in[pindexl];
		 * This assumes partitioning is based on first index. 
		 * Causes stackOverflowException in worst case when array is sorted.
		 * To handle this problem, we swap the randomly selected pivot element with the first element.  
		 */
		ArrayUtils.swap(in, pindex, l);   
		int pivot = in[l];
		int lastMin = l; 
		for ( int current = l ; current <= r ; ++current) {
			if ( in[current] < pivot )
				ArrayUtils.swap(in, current, ++lastMin);
		}
		ArrayUtils.swap(in, l, lastMin);
		return lastMin; // Now lastMin is the position of the pivot after swapping.
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);        // Get the size of array. 
		/*
		int[] in = ArrayUtils.uniqueRandomData(n);  // Get random data. 
        ArrayUtils.display(in); 
        quickSort(in); 
        ArrayUtils.display(in); 
        */
		
        // Worst case scenario or QuickSort
        int[] sortedArray = ArrayUtils.sortedData(n); 
        long start = System.currentTimeMillis(); 
        quickSort(sortedArray); 
        long end = System.currentTimeMillis();
        System.out.println("Time taken in worst case : " + ( end - start ) / 1000.00  + " Seconds"); 
        
        // Avg case scenario of QuickSort
        // 1 lack = 0.25 , 1 million 0.152 , 10 million 1.354 , 100 million 14.024 
        int[] in = ArrayUtils.uniqueRandomData(n); 
        start = System.currentTimeMillis();
        quickSort(in);
        end = System.currentTimeMillis();
        System.out.println("Time taken in avg case : " + ( end - start ) / 1000.00  + " Seconds");    
	}

}

/*
 * QuickSort : Divide and conquer recursion technique.
    Main idea : 
    Take first element and do two things : 
    1) Place it in its respective position
    2) Partition the array such that items to the left are smaller than it and items to the right are greater than it. 
		   
    Idea in QuickSort is inversion of selection sort. 
    In selection sort, first element comes to the first position,second on the second slot and so on.
    So the idea in selection sort was to find the smallest element and put it in its respective position.
    Here we take the first element and put it in its respective location. We don't know its position in advance. 
    If somehow we know its position, it can help us divide the problem into two sets. So we use the 
    inversion of selection sort to bring reduction to the problem. 

    Input :[6, 7, 1, 9, 4, 3, 2, 8]
    To find the position of 6 in the output, we have to look at the whole data and find out how many elements  
    are smaller than 6 and place 6 in its respective position. Lets see what we get when we place 6 at its 
    correct location.
    [7, 1, 9, 4]  6   [3, 2, 8] 
    Now if we try to sort both the left and right part recursively , we wont get the desired result because 
    elements to the right of 6 need to be larger than 6. So we try a solution in which items to the left of 
    8 are smaller than it and items to the right are greater than it. That means along with the placement we 
    need to do partitioning as follows :
    Take first number "1", on its left there will be nothing. On its right we will have [4,3,2] 
    In [4,3,2] first element will be 4. To its left we have [3,2], to its right we have nothing.
    This can be represented as follows.  

  [(1),4,3,2]         6           [(7),9,8]  
[ ]    [(4),3,2]                  []   [9][8]
     [(3),2]  []                       [8]  [-] 
   [(2)][]

    So by the end of this recursive execution, all the elements at going to be at their right postition. 
    While returning back there is nothing to do.
 * 
 * 
 * Time complexity of QuickSort 
   Best Case Scenario : When you divide the array exactly in half, the height of the tree is log in. 


  [(-),-,-,-]         -> n comparison for n elements 
 [-,-]    [-,-]       -> n/2 + n/2  comparisons for n elements. 
[-][-]     [-][-]     -> n/4 + n/4 + n/4 + n/4 comparison for n elements.  

  TC : O( nlogn ) 
  SC : O( logn ) 
  Best case does not give you the performance of algorithm in reality. We discuss it for name sake. 

  Worst Case Scenario : 
  ---------------------
  In reality worst case gives us the guaranteed performance of an algorithm. 
  Let see how quickSort performs for an sorted array. 
    [(1),2,3,4,5]                    ->   n comparisons to do partition
   [-]       [(2),3,4,5]             ->   n-1 comparisons. 
            (-)      [3,4,5]         ->   n-2 comparisons.   
			       (-)    [4,5]      ->   n-3 comparisons.  
                        (-)   [5]    ->   1 Comparison. 
						
  Total comparisons : n + (n-1) + ....+ 1  = O(N2)
  SC : Is the depth of the recursion n. O(n) 
  Time complexity analysis below shows that for worst case this algo throws stack overflow error for only 10000 elements.
  This happens when the array is already sorted. But that kind of worst case is very remote in reality.
  The best way to measure the time complexity of quickSort is to measure its average case time complexity.
  The reason for the stack overflow in worst case scenario has to do with pivot selection. 
  
  Avg. case 
  ---------
  If there are n items in array, to compute average case we should take n! inputs 
  find time complexity for each of them and then take an average of it. 
  The time complexity will vary based on where the pivot element is placed.
  Consider that there are five slots in the array and we keep moving pivot ahead by one slot. 
  [-,-,-,-,-] 

  Then the partitions will happen as follows. 
  [0,4] => Represents that first partition will have 0 element and the second will have 4. 
  Similarly the other partitions will be : 
  [0,4] , [1,3] , [2,2] , [3,1]
  T = Total time taken to sort n elements  
  [0,4] - T(0) + T(5) + 5 ( 5 is Time taken to partition the array of size 5 )  
  [1,3] - T(1) + T(3) + 5 
  [2,2] - T(2) + T(2) + 5 
  [3,1] - T(3) + T(1) + 5

  T(n) = 1/n x Sum of [ T(i) + T( n - 1 - i ) ] + C*N
  TC =  O(nlogn) 
  SC =  O(logn) 
 */

