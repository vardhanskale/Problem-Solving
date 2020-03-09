package com.algo.combinatorics;

  /*
   * P1 : Find an effecient algorithm that enumerates all ternary sequences of length n. 
   * Ternary sequence means base 3. i,e the output will only have 0,1,2 in them.
   * We use binary sequeence in our code.
   * Input   : n=1 
   * Output  : { 0, 1 ,2 }  
   * Input   : n=2
   * Output  : { 00, 01 , 02, 10, 11, 12 ,20 ,21 ,22 } 
   * Input   : n=3
   * Output  : { 000, 001 , 002,010,011,012, 100, 101, 102 ,110, 111,112, 120, 121, 122, 200 ,201 ,202, 210,211, 212, 220,221,222  }
   */ 
   public class TernarySequences {

	// Solution 1 : Take two loops. 
	public static void ternarySeq0(int n) {
		for (int i=0; i<n ; i++ ) {
			for (int j=0 ; j<n ; j++ ) {
				System.out.println(i + "" + j);
			}
		}
		
	}
	
	/*
	* We need to bring some algorithmic pattern into the picture for generating characters. 
	* For combinatorics problems, lets adapt the virtual tree idea to build a solution. 
	* How do I visualize this in a virtual tree ? 
	* Initially there is an empty root node 0 and there are levels in trees. 
	* Slot1 followed by Slot2 followed by Slot3.  
	* Tree diagram a natural to depict something that follows something else. 
	* At Slot1, initially there are three possibilities, 0, 1, 2.
	* We pass 0 to root node. Root node passes 0,1,2 to its child. Each child passes 0,1,2 to their children. 

	 Slot1             Empty NODE
	                0 /   1 |    \2
	 Slot2	       Node   Node   Node 
	            0/ 1| 2\       0/ 1| \2
	 Slot3    Node    Node    Node   Node   
	       0/ 1| 2\                0/ 1| 2\ 
 		  Node   Node            Node Node Node  
	      000      002           220  221  222
		 
	 So now if n changes, the depth of a tree changes, not the width/children of a node. 
	 Because in a ternary sequence, we will always have three slots/children of the root node. ( 0,1,2 )	
    */
	
	public static void ternarySeq1(int n) {
		auxTernarySeq1(0, n, "");
	}
	
	/* 
	 * The following parameters are sent from parent to child...
 	 * 1. "result" holds the result.Result is path value sent from parent to child. Initially result="" or 0.
	 *     We pass result because it is the sum or appending of all the values sent from parent to root.  
	 * 2. "n" is the depth of the tree,given to us as in input. It does not change.
	 * 3. "depthOfRecursion" depicts the tree level. It keeps increasing as we go from top to bottom.  
	 * 4. Terminating condition : When depthOfRecursion = n, we need to print result.
	 */ 
    public static void auxTernarySeq1(int depthOfRecursion, int n, String result) {
	    
		// Terminating condition :  
		if ( depthOfRecursion == n ) {
			// I want the sequence to be shown. 
			System.out.println(result);
			return; 
		}
		
		// All nodes in the next slot will have the same value for slot. 
		// Initially the value of result is ""
		auxTernarySeq1(depthOfRecursion+1, n, result+"0");
		auxTernarySeq1(depthOfRecursion+1, n, result+"1");
		auxTernarySeq1(depthOfRecursion+1, n, result+"2");
	} 
	
    
    public static void ternarySeq12(int n) {
		auxTernarySeq12(0, n, "");
	}
	private static void auxTernarySeq12(int d, int n, String res) {
		if(d == n) {
			System.out.println(res);
			return;
		}
		for(int i = 0; i < 3; ++i)
			auxTernarySeq12(d+1, n, res+i);
	}

	public static void main(String[] args) {
	   int n = Integer.parseInt(args[0]);
       //ternarySeq0(n); 
	   ternarySeq12(n);
	}

}
