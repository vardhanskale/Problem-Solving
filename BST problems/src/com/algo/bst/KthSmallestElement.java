package com.algo.bst;

import java.util.Random;
import java.util.Stack;

public class KthSmallestElement {

	
	/* Approach 1 : 
	 * Inorder traversal of BST gives elements in sorted order. 
	 * Keep incrementing a counter as you traverse each node.
	 * When the count equals K, return the value of the node.
	 * Lets try iterative inorder traversal. 
	 * Push all left nodes in the stack till the notes are not null. 
	 * When the node is null, pop the next node from the stack & make its right link as root node.
	 * If the stack is empty no need to pop the node, just exit.
	 */
	
	
	public static int kSmallestElement1(BSTNode root, int k){
		
		Stack<BSTNode> stack = new Stack<>();
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
	 * Approach 2 : 
	 * Lets try recursive inorder traversal.  
	 * If every node knows what its position is in inorder traversal, 
	 * it can store the value at the kth position in a data structure 
	 * which is passed to it. Lets pass an array of two indexes
	 * to the function. The first index stores the count and the second
	 * index stores the value. This is the key point in this problem. 
	 */
	
	// TC : Theta(n)  SC: Theta (log n ) 
	public static int kSmallestElement2(BSTNode root, int k) {
		int[] arr = new int[2];
		auxKSmallestElement2(root, k, arr );
		return arr[1];
	}
	
	// In in-order traversal we call left subtree, do some processing and then call right subtree.
	public static void auxKSmallestElement2(BSTNode root, int k, int[] arr){
		if ( root == null )                       // Step 3 : Base case : If root is equal to null
			return; 
		auxKSmallestElement2(root.left, k , arr); // Step 1 :  Call left subtree
		if (++arr[0] == k) 
			  arr[1] = root.data; 
		auxKSmallestElement2(root.right, k, arr); // Step 2 : Call right subtree
	}
	
	
	/* Approach 3 : Adhoc Strategy : Recursive Inorder Traversal  
	 *  We know that In order traversal of tree gives us value in sorted order.
	 *  Our goal is to make an inorder call and increment a count when you access the node. 
     *  But how do you increment a count in the recursive function call ? 
     *  We need a shared counter across all the calls. 
     *  We know that we never use static and global variables in recursive calls. 
     *  They will fail when you invoke the methods within multiple threads. 
     *  So we maintain a local counter in the heap section and share that 
     *  variable across all the recursive calls. 
	 *  Lets create a custom MyInteger class which is created in the heap and shared by
	 *  all the stack frames. If the kth element is found in the left subtree
	 *  there is no point in continuing the recursion to right subtree. If 
	 *  the Kth element is found we return the nodes value, else return -1.  
	 *  
	 *  TC : If the tree is very big, in the worst case scenario you have to 
	 *  go through all the elements of the tree. So TC : O(n) 
	 *  We are not leveraging divide and prune strategy here.
	 */   
	
	public static int kSmallestElement3(BSTNode root, int k) {
		MyInteger counter = new MyInteger(); 
		return auxKSmallestElement3(root, k, counter );
	}
	private static int auxKSmallestElement3(BSTNode root, int k, MyInteger counter) {

		  if ( root == null )                                   // Step 3 : Base case : If root is equal element not found.
		      return -1; 
		  
		  auxKSmallestElement3(root.left, k, counter);          // Step 1 :  Call left subtree
		  // If kth element is found, break the recursion. 
		  // count.setValue(count.getValue() + 1 ) ;  or 
		  counter.incr();   // This headache is needed in java because Integer is immutable.
		  if ( counter.get() == k ) 
		     return root.data;
		  
		  return auxKSmallestElement3(root.right, k, counter);  // Step 2 : Call right subtree
		  
	}
	
	/* Approach 4 : Augmenting the value in the node to Divide and Conquer. 
       Draw a tree. 
                  50 
	        40         60 
	     30    45    55  80 
                 48
     * Input K = 3  O/P : 45 
     * Our aim is to find the third smallest element. Start from root node. 
     * At the root node only has data. 
     * There is no way to know that the right side definitely does not have the third smallest element. 
     * There is no extra information available to make a judgment that right side is useless.
     * To do pruning at root node some information has to be added at the root level. 
     * What information should we add at each node ? 
     * By looking at the tree we derive that the third smallest is at the left side. 
     * This is because there are 4 elements at the left in sorted order.
     * If we store this information ( size of left subtree ) with each node, we can take a pruning decision at the node level. 
     * Or we can maintain the rank of each node. For example 50 has a rank 5.
     * By reading the rank value 5 we can know that we have to go to its left to reach element 3.   
     * So we can either store 1) Size of LST 2) Maintain node rank at each node. 
     * But how do you know which information makes more sense ? 
     * The problem with ranking approach is that once you add or insert a new node 
     * you have to change the rank of all the other node which will increase time complexity from TC : O(log(n)) to O(n).
     * This is not acceptable as we want both methods to be log(n) complexity. 
     * Lets evaluate the second approach of storing the size of left subtree. 
     * Draw a tree with size of left sub tree in brackets. 
          (4) 50 
	 (1) 40       (1) 60 
  (1)30    45 	 (0)55  80 
           (0)48
		      
     * Now in this approach whenever you insert a new node in the tree, say at the bottom left, 
     * as you traverse from root to leftmost leaf node you keep incrementing the count of LST at each node.  
     * No other nodes gets affected except the nodes which are in the path of insertion. 
     * This way in the same log(n) complexity element can be added up. 
     * But how does the LST approach help us in getting Kth smallest element? 

     * The (4) besides 50 in the tree above says that there are 4 elements to the left of 50.	
     * Which also means that 50 is the 5th smallest element in the tree and 40 is the second.
     * Now that you know that 40 is the second smallest element, the 3rd smallest element will be to its right.   

     * Let's assume you are at node 40, which has LSTvalue = 1. Its current position in tree is (LSTvalue+1) = 2. 
     * To get the 3rd smallest node, we do the following : 
     * K=3 - [ Position of 40 which is (LSTvalue+1) ] or 3 - [ 1+1 ] = 1. 
     * This value of 1 implies that we need 1st smallest element to the right of 40 to get the 3rd smallest element. 
     * How do we know which is the 1st smallest element to right. We look at the tree and find that its 48. 
     * i,e the element whose (LSTvalue+1) = 1.  So at each node we need to check  
     * K - (LSTValue + 1)
	 */
	
	
	 /* TC : Theta(Log n )  SC:Theta (n) 
	  * Strategy : Augment value of at root node to prune left or right subtree.
	  */
	public static int kthSmallest4(BSTNode root, int k) {
		
		while ( root != null ) {  
		// If that is the rank of current root node 
		if ( k == root.lst_size+1 )            // Step 1 
		   return root.data;
		
		// Think of pruning logic. 
		if ( k < root.lst_size + 1)            // Step 2  
           root = root.left;   
		else {
			k = k - ( root.lst_size + 1) ;     // Step 3
			root = root.right ; 
		}	
	  } // Hitting null means Kth smallest element is not in the tree. 
	 return Integer.MIN_VALUE; 	
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		BSTNode root = BSTUtils.createUniqueBST(n);
		BSTUtils.displayTree(root);
		int k = new Random(0).nextInt(n) + 1;
		//System.out.println(k);
		System.out.println("Iterative kSmallestElement1 -> " + kSmallestElement1(root, 2));
		System.out.println("Recursive kSmallestElement2 -> " + kSmallestElement2(root, 2));
		System.out.println("Recursive kSmallestElement3 -> " + kSmallestElement3(root, 2));
	}

}
