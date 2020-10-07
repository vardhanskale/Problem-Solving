package com.algo.bst;

import java.util.Random;

/* 
 *  Problem  : Find an efficient algorithm to search for an element X in a given BBST. 
    When we say balanced BST, we always mean height balanced 1 tree. 
			
    Input :          
    50       X = 45. Expected output is true.
                 / \
               40   60
			  /  \    \
		 	10	 45	  90 
			
    We need to find if the given element is equal to the root node or not. 
    If it is less than 50, search in the left subtree. If it is greater than 50, search in the right subtree. 
    So a BBST is a sorted array kind of problem. In an array the sorted-ness is given in linear form but in BST 
    the sorted-ness is given in a non linear form. We know that divide and prune is a natural choice 
    for a sorted array problem, similarly for a BST or BBST divide and conquer becomes a natural choice. 
    All you need to decide is whether you need to choose the left part or the right part. 
  
    Solution : Divide and Prune Pattern. 
    Dividing : LST, RST 
    Pruning : At any node 
          if ( x < data ) then prune right subtree. 
		  if ( x > data ) then prune left subtree. 
		  if ( x == data ) then return true. 
 * Search for an item in BST */ 

public class BSTSearch {

	
	// TC : Theta( logn )  SC : O(1) 
	public static boolean search(BSTNode root, int x ) {
		
		if (x == root.data) return true; 
			
		while (root != null ) {                     // Step 4 : Continue till tree is empty
			if ( root.data == x )  return true;     // Step 1 : Start from here 
			if ( x > root.data) root = root.right;  // Step 2 
			else root = root.left;                  // Step 3 
		}
		return false; 
	}
	
	public static void main(String[] args) {
		// Take the size of the BST from the command line. 
		int n = Integer.parseInt(args[0]); 
		BSTNode root = BSTUtils.buildBST(n);
		BSTUtils.displayTree(root);
		
		// Test case for successful search
		int x = new Random().nextInt(n) + 1; 
		System.out.println(x);
		System.out.println(search(root, x));
		
		// Test case for unsuccessful search 
		x = 0 ; 
		System.out.println(x);
		System.out.println(search(root, x));		
	}

}
