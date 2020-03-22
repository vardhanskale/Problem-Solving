package com.algo.bst;

import java.util.Random;
import java.util.Stack;

public class KthSmallestElement {

	
	/* Approach 1 : Inorder traversal of BST gives elements in sorted order. 
	 * Lets try iterative inorder traversal. 
	 * Push all left nodes in the stack till the notes are not null. 
	 * When the node is null, pop the next node from the stack & make its right link as root node.
	 * If the stack is empty no need to pop the node, just exit.
	 */
	public static int kSmallestElement1(TreeNode root, int k){
		
		Stack<TreeNode> stack = new Stack<>();
		int nodeCount = 0; 
		int kthElement = -1;
		while ( true ) { 
			if ( root != null ) {
				stack.push(root); 
				root = root.left;
			}else {
				// Root is empty. Now pop from stack if it is not null. 
				if ( stack.isEmpty())
					return kthElement;
				root = stack.pop();
				// if it's the kth element, return. 
				if ( ++nodeCount == k ) {
					kthElement = root.data; 
					return kthElement; 
				}
				root = root.right; 
			}
		}
	}
	
	
	/* 
	 * Approach 2 : Lets try recursive inorder traversal.  
	 * If every node knows what its position is in inorder traversal, 
	 * it can store the value at the kth position in a data structure 
	 * which is passed to it. Lets pass an array of two indexes
	 * to the function. The first index stores the count and the second
	 * index stores the value. This is the key point in this problem. 
	 */
	public static int kSmallestElement2(TreeNode root, int k) {
		int[] arr = new int[2];
		auxKSmallestElement2(root, k, arr );
		return arr[1];
	}
	
	
	public static void auxKSmallestElement2(TreeNode root, int k, int[] arr ){
		if ( root == null )
			return; 
		auxKSmallestElement2(root.left, k , arr);
		if (++arr[0] == k) 
			  arr[1] = root.data;
		auxKSmallestElement2(root.right, k, arr);
	}
	
	
	/* Approach 3 : Don't pass an array object. We need a global counter 
	   to measure the node count across all the nodes of the tree. Create
	   a custom MyInteger class which is created in the heap and shared by
	   all the stack frames. If the kth element is found in the left subtree
	   there is no point in continuing the recursion to right subtree. If 
	   the Kth element is found we return the nodes value, else -1.  
	 */   
	
	public static int kSmallestElement3(TreeNode root, int k) {
		MyInteger counter = new MyInteger(); 
		return auxKSmallestElement3(root, k, counter );
	}
	private static int auxKSmallestElement3(TreeNode root, int k, MyInteger counter) {

		  if ( root == null ) 
		      return -1; 
		  
		  auxKSmallestElement3(root.left, k, counter); 
		  // If kth element is found, break the recursion. 
		  counter.incr();
		  if ( counter.get() == k ) 
		     return root.data;
		  
		  return auxKSmallestElement3(root.right, k, counter);
		  
	}
	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		TreeNode root = BSTUtils.createUniqueBST(n);
		BSTUtils.displayTree(root);
		int k = new Random(0).nextInt(n) + 1;
		//System.out.println(k);
		System.out.println("Iterative kSmallestElement1 -> " + kSmallestElement1(root, 2));
		System.out.println("Recursive kSmallestElement2 -> " + kSmallestElement2(root, 2));
		System.out.println("Recursive kSmallestElement3 -> " + kSmallestElement3(root, 2));
	}

}
