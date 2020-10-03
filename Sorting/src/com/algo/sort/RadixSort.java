package com.algo.sort;


/* 
 * Goal : Lets understand why any comparison based algorithm must take min of O(NLogN) time ? 
 * Lets understand it by taking an array of three elements [e1,e2,e3]
 * Three elements in the array will have 6 possible permutations. 
 * 
 * [5,3,4]                [e1 < e2]                          -> First comparison at level 1  of tree  
 *                     true        false
 *                 [e2 < e3]             [e2>e3]             -> Second comparison at level 2 of tree
 *                true    false       true    false
 *            [e1,e2,e3]   [e1<e3]  [e3,e2,e1]     [e1<e3]   -> Third comparison at level 3 of tree
 *                       true   false            true    false 
 *                   [e1,e3,e2][e3,e1,e2]      [e2,e1,e3] [e2,e3,e1]
 *
 *  Height          Leaf Nodes
 *  -------        -----------
 *    0               1
 *    1               2
 *    2               2 square
 *    3               2 cube
 *    h               2 to the power h nodes
 *    
 *    Our goal is that our leaf nodes must be minimum of n! 
 *    At what h ( height ) will we get n! leaf nodes in the tree ?
 *    So, 2 pow h <= n!
 *    h >= logN! to base2. 
 *    h >= nlogN
 *    h Min (nlogn) 
 *    
 *    
 *    If you really need an N algorithm, you must employ a non comparison based sorting. 
 *    Lets investigate a non comparison based approach to sort an array.
 *    
 *    Radix Sort
 *    ==========
 *    
 *    
 *    Logic : 
 *    =======
 *    Think of each number as a string. 
 *    In the first pass we sort the numbers based on the value of least significant digit.
 *    In the second pass we sort the numbers based on the value of the most significant digit.
 *    In both the passes we maintain the order of the digits.
 *    
 *    Pass 1 : Sort by LSD ( Rightmost ) : 11,23,33,14,15,25,46
 *    Pass 2 : Sort by MSD ( Leftmost )  : 11,14,15,23,25,33,46
 *    
 *    How to sort numbers without comparing them. 
 *    1. Take a count array of size 10. 
 *       If the LSD is 2, then increment value at index 2.
 *       Do the same for all the digits in the original array.
 *       For Input : [ 11, 15, 23, 14, 18, 33, 46, 8 25 ] 
 *       You get   : [ 0, 1, 0, 2, 1, 2, 1, 0, 2, 0 ] 
 *       
 *    2. Perform cumulative sum : current value is added to previous value. 
 *       Cumulative sum    : [ 0, 1, 1, 3, 4, 6, 7, 7 , 9, 9 ] 
 *       Array Index value : [ 0  1  2  3  4  5  6  7   8  9 ] 
 *       What is the significance of cumulative sum ? 
 *       The value 6 at index 5 tells us that there are 6 numbers whose least significant digit 
 *       is less than or equal to 5. The number 9 in index 8 tells that there are 9 numbers in
 *       input array whose least significant digit is less than 8. 
 *       That means it is telling us the total number of case in the array whose least significant 
 *       digit is less than and equal to that given index.
 *       By doing cumulative sum you get a clarity on where the elements are going to land up in 
 *       sorted array.
 *       
 *    3. Create a output array. 
 *       3.a Take the last element from 25 i,e '5' from input array. 
 *       3.b Goto cumulative sum array and read value at index '5', which is 6.
 *           The value 6 tells us that there are 6 elements in input array whose LSD is going to be 5.
 *           That means if you want to keep 5 in output array it has to be the 6th element. 
 *           Go to 6th element at index 5 and copy 25 there. 
 *           Decrement count of 6 in cumulative array by 1.
 *           Take the next element 8 from input array. Repeat the process as shown below.
 *           Read the value of index 8 in cumulative array, which is 9. 
 *           Now store the input array value 8 in the 9th slot ( index 8 ) of output array. 
 *           When we repeat this process by traversing all the elements of input array backwards
 *           we get the following cumulative and output array.
 *           Cumulative Sum : [ 0, 0, 1 , 1, 3, 4 , 6 , 7 , 9 ] 
 *           OP array  : [ 11, 23, 33, 14, 15, 25, 46, 18, 8 ] 
 *       Notice that the output array is sorted by LSD and we have preserved the relative order of elements. 
 *       Also note that we have not done any comparison here.
 *          
 *       
 *    
 */
public class RadixSort {
	
	private static int getNumPasses(int[] in) {
		int max = 0;  // Maximum value in the array 
		for(int x : in ) {
			if ( x > max)
				max = x; 
		}
		int d = 0; // d is the number of digits in the biggest number
		while(max > 0) {
			max = max / 10; 
			++d; 
		}
		return d; 
	 }

	// (123/1) % 10 = 3   (123/10)%10 = 2   (123/100)%10 = 1
	private static void countingSort(int[] in, int divisor, int d) {
		// We have to maintain counts. 
		// Since we are using radix 10, there are 10 possibilities. 
		int[] counts = new int[10] ; 
		int[] out = new int[in.length]; 
		// Go through each element of input array. 
		
		// Perform counting of each possible radix value.
		for (int x : in) {
			// I need to get dth digit of x. 
			int tmp = (x/divisor) % 10; 
			counts[tmp]++;  // By end of this loop, we are scanning through the array 
			// and counting the occurrence of each element based on dth digit position 
			// d could be 1 or d could be 2. 
		} 		
    
		// We need to get cumulative sums.
		for ( int i = 1 ; i < counts.length ; ++i) {
			counts[i] += counts[i-1] ; 
		}
		
		// Place elements in right order. 
		for ( int i = in.length - 1 ; i >= 0 ; --i ) {
			int tmp1 = (in[i]/divisor) % 10; 
			int pos = --counts[tmp1]; 
			out[pos]  = in[i]; 
		}
		
		
		// Copy back 
		for ( int i = 0 ; i < in.length ; ++i) 
			in[i] = out[i]; 
		
		// Now with cumulative sum we can order the input data into output array in a proper sorted fashion. 
		
		
	}
	public static void radixSort(int[] in) {
		int npasses = getNumPasses(in); 
		int divisor = 1; 
		for(int d = 1; d  <= npasses ; ++d ) {
			countingSort(in,divisor, d); // d signifies which digit position we need to sort.
			ArrayUtils.display(in); 
			divisor *= 10;
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = ArrayUtils.uniqueRandomData(n); 
		ArrayUtils.display(in);
		radixSort(in); 
		

	}

}