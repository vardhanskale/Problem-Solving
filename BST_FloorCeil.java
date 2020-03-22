package com.algo.bst;

/* Find the floor and ceil of a number in the given binary tree */ 

public class BST_FloorCeil {

	/* 
	 * Finding Ceil of a value in BST.
	 * If searchKey is equal to root.data, return. 
	 * If searchKey is less than root.data , then 
	 *    root.data could be the ceil. Store it & Move down to the left subtree.
	 * If search key is greater than root data then root cannot be the ceil, so 
	 * move to the right of the root node. 
	 */
	public static int ceil(TreeNode root, int x ) {
	  int ceilValue = 0;
	   while ( root != null) {
		   if ( root.data == x ) {
			   return x; 
		   }
		   if (  root.data > x ) {
			   ceilValue = x; 
			   root = root.left; 
		   }else {
			   root = root.right; 
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
		//TreeNode root = BinaryTreeUtils.createBalancedBinaryTree(n); 
		//ceil( root, 25 );
		//floor(root, 25 );
	}

}
