package com.algo.bst;

import java.util.Random;

/* 
 * Problem  : Find the floor and ceil of a number in the given binary tree. 
 * Ceil means immediate higher element. If X is available return X. 
   So in the given tree Ceil(8) = 8.  
   Ceil(12) = 15. As 15 is the next highest integer after 12.
          20 
	   10    40 
     8  15	   60  
          17  50

    Given x = 12, lets explore how we are going to find 15. 
    Lets start from root node. 
    Since 20 > 15, either 20 can be the ceil or an item to the left of 20 could be the ceil. 
    So I need to remember 20 in a variable, but the right subtree is completely meaningless to try. 
    Now we come to node 10. 12 is greater than 10. So 10 and all subtree to the left are useless. 
    Then we come to node 15. Sine 15 is greater than 12, either 15 could be ceil or something to its left could be the ceil. 
     Since left side is empty, its over. The final answer is 15.
 * 
 * */ 

public class BST_FloorCeil {

    // TC : Theta(logn)  SC: O(1)
	public static int ceil(BSTNode root, int x ) {
	  int ceilValue = Integer.MIN_VALUE ; // Minus infinity
	   while ( root != null) {         // Step 3 : Ceil may not existing for some keys. Keep trying till the tree is empty.
		   if ( root.data == x ) {     // Step 1
			   return x; 
		   }
		   if (  x < root.data  ) {    // Setp 2 : Either root could be ceil or something on the left
			   ceilValue = root.data; 
			   root = root.left; 
		   }else {
			   root = root.right;      // Left side including root is useless, so we go to the right.
		   }	   
	   }
	   return ceilValue;
	}
	
	/* 
	 * Find floor of a value in the BST. 
	 * floor is the next smallest value of the given number.
	 * 1. If search key is equal to roots value, return. 
	 * 2. if searchKey is bigger than roots value, then 
	 *    root can be the floor. Store roots value and.... 
	 *    go to the right side of the root. 
	 * 
	 * 3. If searchKey is smaller than roots value, then 
	 *    ignore root. We cannot say if roots left is floor of x yet. It may be bigger. 
	 *    So lets go to roots left and go back to point 1 to process next node.  
	 */  
	
	public static int floor(TreeNode root, int x ) {
		int floorValue = 0;
		while ( root != null ) {
			if ( x == root.data)
				return x;
			if ( x > root.data) {
				floorValue = root.data; 
				root = root.right;
			} else {
				root = root.left; 
			}
		}
		return floorValue;
	}
	
	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]); 
		BSTNode root = BSTUtils.buildRBST(n); // Build a random BST
		BSTUtils.displayTree(root);
		
		// Test case for successful search
		int x = new Random().nextInt(n) + 1; 
		System.out.println(x);
		System.out.println(ceil(root, x));
		
		//ceil( root, 25 );
		//floor(root, 25 );
	}

}
