package com.algo.bst;

/* Search for an item in BST */ 

public class BSTSearch {

	public static boolean treeSearch(TreeNode root, int x ) {
		
		while (root != null ) {
			if ( root.data == x )
				return true ;
			if ( x > root.data) {
				root = root.right;
			} else {
				root = root.left; 
			}
		}
		return false; 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
