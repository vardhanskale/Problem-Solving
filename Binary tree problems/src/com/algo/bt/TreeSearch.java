package com.algo.bt;

public class TreeSearch {
	

	 /* Recursion : Divide and Conquer.  
	  * Ask the left subtree if it has the searchKey
	  * Ask the right subtree if it has the searchKey. 
	  * Return true if any one of them has the search key.  
	  */ 
	 public static boolean treeSearch1(BTNode root , int searchKey) { 
	        if ( root == null ) 
	           return false;  
			
			if ( root.data == searchKey ) 
			   return true; 
			   
			boolean foundLeft = treeSearch1(root.left, searchKey) ; 
			if ( foundLeft ) 
			   return true; 
			   
		    // boolean foundRight = treeSize(root.left, int searchKey); 
			// return foundLeft || FoundRight
		    return treeSearch1(root.left, searchKey);
	}
	 
	public static void main(String[] args) {
		

	}

}
