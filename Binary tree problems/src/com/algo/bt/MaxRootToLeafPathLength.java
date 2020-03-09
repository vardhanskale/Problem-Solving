package com.algo.bt;

import java.util.LinkedList;
import java.util.Queue;

public class MaxRootToLeafPathLength {

	public static int gmax = 0; 
	
	/* Attempt 1 : Complete search. Try all root to leaf paths.
	 * Parent conveys 'root to child' distance to child through an argument.
	 * Each node passes that information to its child till we reach leaf node.
	 * Let an int variable gmax hold the largest root to 'leaf path'. Initially gmax=0;
	 * At leaf node we check if its distance from root node is larger than the value in gmax.
	 * Finally we update gmax is updated with the longest path and return. No value is returned. 
	 * In this solution gmax is a static variable. This is not the recommended approach as its not 
	 * thread safe.
	 */
	public static int maxRoot2LeafCount(BTNode root){
		 auxMaxRoot2LeafCount(root, 1);
		return gmax; 
	}
	
	public static void auxMaxRoot2LeafCount(BTNode root, int pathLength){
		if ( root == null)
			return; 
		if ( root.left == null && root.right == null ) {
			gmax = Math.max(pathLength, gmax);
			return;
		}	
		// Let the max root to leaf path of the left & right subree 
		auxMaxRoot2LeafCount(root.left,  ++pathLength );
		auxMaxRoot2LeafCount(root.right, ++pathLength);
	}
	
	
	/* Attempt 2 : Complete Search. Same approach as above. 
	 * Instead of using a static global variable, pass an object to update 
	 * global max 'root to leaf' path. 
	 */
	
	public static int maxRoot2LeafCount2(BTNode root){
		MyInteger gmax = new MyInteger(1); 
		auxMaxRoot2LeafCount2(root, 1, gmax);
		return gmax.get();
	}
	
	public static void auxMaxRoot2LeafCount2(BTNode root, int depth, MyInteger gmax){
		if ( root == null)
			return; 
		if ( root.left == null && root.right == null ) {
			gmax.set(Math.max(depth, gmax.get()));
			return;
		}	
		// Let the max root to leaf path of the left & right subree 
		auxMaxRoot2LeafCount2(root.left,  ++depth, gmax);
		auxMaxRoot2LeafCount2(root.right, ++depth, gmax );
	}
	
	/* Attempt 3 : Use Recursion ( Divide and Conquer )
	 * In recursion we don't solve the whole problem. 
	 * We don't know what is the longest root to leaf path.
	 * So we divide the problem by asking the left and right subtree this question:  
	 * 	1.	What is the longest root to leaf path in your left subtree? 
	 * 	2.	What is the longest root to leaf path in your right subtree?  
	 * Math.Max(Root to leaf path of left subtree, Root to leaf path of right subtree ) + 1  
	 */ 
     public static int maxRootToLeafPath3(BTNode root) {
		
    	 if ( root == null )
    		 return 0; 
    	 if ( root.left == null && root.right == null ) 
    		 return 1;
		
    	int lpath = maxRootToLeafPath3(root.left);
    	int rpath = maxRootToLeafPath3(root.right);
    	 return  Math.max(lpath,rpath) + 1 ; 
	 }
	
	/* Attempt 4 : Use BFS. Longest root to leaf path = Number of levels in the tree.  
	 * While we can use BFS to traverse entire tree level by level...
	   How do we know that one level has ended and other has started ? Thats the trick here.
	   Can we add a delimiter in the queue for it ? Lets add a null marker to remember that a level has ended. 
	   So this is the plan if we execute sequentially: 
		  1. Insert the root node. 
		  2. Insert the null marker. 
		  3. Remove the item from queue ( root node ) .
		  4. Add the left and right children of the node in the queue if they are not null. 
		  5. At this stage, we have the null marker followed by both the left and right sub child in the queue.
		  6. Remove the next item ( null marker ) 
		  7. If it is a null marker 
			 7.1 Increment the level count and 
			 7.2 Add another null marker after both the child nodes.    
		  8. Keep repeating this 
	*/
	
	
	public static int maxRootToLeafPath4(BTNode root) {
	    Queue<BTNode> q = new LinkedList<BTNode>(); 
	    q.add(root); 
	    q.add(null);  // Add null marker. New point. 
		int levelCount = 0; 
		while ( !q.isEmpty() ) {
		   BTNode tmp = q.remove() ;
		   if ( tmp == null ) {
			   levelCount++; 
			   // IMP : After removing the last null in the queue, we should not add another null. Corner case.
			   // Testing Note : supply a tree with a single root node
			   if ( !q.isEmpty())
			      q.add(null);
		   } else { 
			   if ( tmp.left != null )
		           q.add(tmp.left);
			     
			   if ( tmp.right != null )
		           q.add(tmp.right); 
		   }
		}
		return levelCount; 
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		//BTNode root = BinaryTreeUtils.createOneSidedBinaryTree(n);
		BTNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree(root);
		//System.out.println(maxRoot2LeafCount(root));
		System.out.println(maxRootToLeafPath3(root));
    }

}
