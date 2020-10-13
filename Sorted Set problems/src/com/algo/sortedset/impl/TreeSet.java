package com.algo.sortedset.impl;

import java.util.List;
public class TreeSet implements ISortedSet {

	/* 
	 * TreeSet will hold the root node and the size of tree. 
	 * If there are no element in tree, create a new node and store it as root.  
	 * If there are nodes, add elements to it as per rules of BST.
	 * 
	 */
	
	private BSTNode root;
	private int size;

	@Override
	/* This add method works but the problem is that it creates on sides BST. 
	 * Doesn't balance it.
	 */
	public boolean add(Integer x) {
		if (root == null) {
			root =  new BSTNode(x);
			++size;
			return true; 
		} 
		
		BSTNode current = root; 
		BSTNode parent = null; 
		while (current != null) {   // Repeat while tree may not be there
			if (x == current.data)  // When there  is duplicate element return false;
				return false;       // Nothing to do when duplicate element exists.
			parent = current;       // Current moves ahead and becomes null. parent holds the last nodes value. 
			if (x < current.data)
				current = current.left;
			else
				current = current.right;
		}
		// Now its time to insert the element. 
		if ( x < parent.data)
			parent.left = new BSTNode(x); 
		else 
			parent.right = new BSTNode(x);
		
		++size;                      // Increase the count of nodes in bst.  
		return true;
	}

	@Override
	public boolean contains(Integer x) {
		BSTNode current = root; 
		while (current != null) { // While tree may not be there
			if (x == current.data)
				return true;
			if (x < current.data)
				current = current.left;
			else
				current = current.right;
		}
		return false;
	}
	
	@Override
	public boolean remove(Integer x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	private static void auxDisplay(BSTNode root, int nspaces, String annotation) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println(root.data + "(" + annotation + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}
	
	@Override
	public void display() {
		auxDisplay(root, 0 , "root"); 
	}

	@Override
	public int min() {
		// min element is always the leftmost position.
		// But if root is null we cannot find minimum element. 
		if ( root == null ) return Integer.MIN_VALUE; 
		BSTNode current = root; 
		while ( current.left != null ) {
			current = current.left; 
		}
		return current.data;
	}

	@Override
	public int ceil(Integer x) {
		 int ceilValue = Integer.MIN_VALUE ; // Minus infinity
		 // We don't want to update root. 
		 BSTNode current = root;
		   while ( current != null) {         // Step 3 : Ceil may not existing for some keys. Keep trying till the tree is empty.
			   if ( current.data == x ) {     // Step 1
				   return x; 
			   }
			   if (  x < current.data  ) {    // Setp 2 : Either root could be ceil or something on the left
				   ceilValue = current.data; 
				   current = current.left; 
			   }else {
				   current = current.right;      // Left side including root is useless, so we go to the right.
			   }	   
		   }
		   return ceilValue;
	}

	@Override
	public int select(int k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> rangeSearch(int k1, int k2) {
		// TODO Auto-generated method stub
		return null;
	}

}
