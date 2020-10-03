package com.algo.sort;

import java.util.PriorityQueue;

/*
Sorting Solution 4 : Sorting using priorityQueue 
Lets now sort an array using Heap (PriorityQueue ) and analyze time complexity. 

Input : 8 7 1 9 4 3 2 6 
Output : 1 2 3 4 5 6 7 8 9 

Strategy : Insert element one after the another in heap based priorityQueue.

PriorityQueue supports an operation called deleteMin() which is an order based operation.  
Since we need the data to be sorted in increasing order, we will use min heap or min PriorityQueue. 
Heap is a complete binary tree which maintains the following properties : 
  1. Parent nodes value is always smaller than the child node's value.
  2. Maintains structure property - That every root node will have two childs.
  3. Maintains order property - That the root node will be the smallest.
To get values of tree, we traverse the tree in level order to get sorted data. 
This is how the value is stored in min heap : 

  8                       7         7                              1
 /    =>  [Swap 8 & 7]   /   =>    / \    => [Swap 7 & 1]   =>    / \    => Output of level order 1 , 8, 7
7                       8         8   1                          8   7

Note that the content is not arraigned in sorted order. To get elements in sorted order, 
use the deleteMin() API of the heap. Note that the smallest element in a min heap is always at the top. 
If you take it out, heap will automatically adjust itself to get the smallest element at the top. 
Again if you take it out, it will adjust itself to get the third smallest element at the top. 
So by doing a bunch off deleteMin(), you get a sorted output. Lets see how delete min changes the heap. 

       1                                                         9
     /   \                                                     /    \
    4     2     =>  Delete 1. Bring last element 9 to top =>  4      2   => Now structure property intact. Order property is violated.
   / \   / \                                                 / \    / \
  6   8 7   3                                               6   8  7   3
 /
9 

To fix the order property we rearrainge the heap again internally by replacing last element 9 with Min(4,2) = 2. 

      2                                                           2
	/   \                                                       /   \
   4     9        =>  Replace 9 with Min ( 7,3)  =>            4     3      This is what you get after first delete min. 
  / \   / \                                                   / \   / \
 6   8 7   3                                                 6   8  7  9
 
 
call deleteMin() again to delete 2 and bring bottom right 9 to top. Then the state of min heap will be 

        3                                                             9                                              4   
	  /  \                                                           / \                                            / \
     4    7     deleteMin() => deletes 3 and 9 goes to top =>       4   7     =>   Replace 9 with Min(4,7 )  =>    9   7
    /\    /                                                        / \                                            / \
   6  8  9                                                        6  8                                           6   8
   
   
Replace 9 with Min (6,8)   

       4 
	  / \
     6   7     => When you display this you get 4,6,7,9,8
	/ \ 
   9   8	
   
To summarize, sorting an array using priority queue is a two step process 
Stage 1 : Construct a heap by inserting elements one by one in it. 
Stage 2 : Sort Phase :  Perform multiple deleteMin() operation which gives you smallest element and causes adjustment to happen in the priorityQueue. 

Time Complexity : 
=================
In whichever order you pass the data in the priority queue ( sorted , unsorted ) the time the algorithm will take will be the same. 
For Best, Average and Worst case it will perform the same. But how much is the complexity. 
Construction Phase : 
 1. To insert N element in heap we need logarithmic complexity.
 2. Log1 + Log2 + Log3 + Log(N - 1)  => Log ( n - 1 ) !
Adjustment Phase : 
 1. To remove element from the bottom and re-adjust the heap we need logN complexity. 
 2. Time complexity for each successive delete will be log(n) + log(n-1) + log ( n -2 ) + ....+ log 1  => Log(n) !
 = 2 log n!  = o( nlogn ) 
 
 
Space Complexity 
================
We need to allocate N elements. So space complexity is O(n)  

*/ 

public class HeapSort {

	
	public static void heapSort(int[] in) {
	    // Java gives us a min priorityQueue by default. Lets construct a heap.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); 
		//System.out.println("Construction phase"); 
		for (int x : in) {
			pq.add(x);
			//System.out.println(pq); 
		}
		
		// Get smallest element from priorityQueue till priority queue gets empty
		// Add those elements in array. 
		//System.out.println("Sort phase"); 
		int i = 0; 
		while ( !pq.isEmpty() ) {
			in[i++] = pq.remove(); 
			//System.out.println(pq);
		}
	}
	
	
	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]); 
		/*
		int[] in = ArrayUtils.uniqueRandomData(n);
		ArrayUtils.display(in);
		heapSort(in); 
		ArrayUtils.display(in);
	    */
		
		
		// 1 million = 0.852 seconds
		// 10 million = 0.966 seconds
		int[] in = ArrayUtils.uniqueRandomData(n); 
		long start = System.currentTimeMillis();
		heapSort(in); 
		long end = System.currentTimeMillis(); 
		System.out.println("Time Taken :" + (end - start)/1000.0 + "seconds"); 
	}

}
