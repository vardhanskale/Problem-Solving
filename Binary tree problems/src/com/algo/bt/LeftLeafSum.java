package com.algo.bt;

import java.util.LinkedList;
import java.util.Queue;

//Problem : Find the sum of left leaf nodes in a given binary tree.
public class LeftLeafSum {
	
    public static int leftLeafSum = 0; 
    public static int leftLeafSum1(BTNode root) {
    	return auxleftLeafSum1(root, ' ');
    }
    
    public static int auxleftLeafSum1(BTNode root, char label) {
    	
    	if ( root == null )
    		return 0; 
    	if ( root.left == null && root.right == null ) {
    	    // We are at the leaf node. 
    	    // Check if it is the left leaf. 
    		if ( label == 'L')
    			return root.data;
    		return 0; 
    	}
    	// Subdivide the problem into smaller problems.
    	int lSum = auxleftLeafSum1(root.left, 'L');
    	int rSum = auxleftLeafSum1(root.right, 'R');
    	// Combine the solution. 
    	return lSum + rSum; 
    	
    }
     
    public static int leftLeafSum2(BTNode root) {
    	if ( root == null )
    		return 0; 
    	if ( root.left != null ) {
    		if ( root.left.left == null )
    			leftLeafSum += root.left.data;
    	}
    	leftLeafSum2(root.left);
    	leftLeafSum2(root.right);
    	return leftLeafSum;
    }
    
    static class CustomBTNode {
    	private BTNode btNode; 
    	private char label; 

    	CustomBTNode(BTNode node, char label) {
    		btNode = node; 
    		this.label = label; 
    	}   
    	   
    	public BTNode getNode(BTNode node){
    	    return node; 
    	}

    	public void setNode(BTNode node){
    		btNode = node; 
    	}    
    }
    
    
    /* Use level order traversal to traverse the tree and store custom type in queue
     * If its a left leaf node,add its value in a variable. 
     */ 
    
    public static int leftLeafSum(CustomBTNode root){ 
    	  Queue<CustomBTNode> q = new LinkedList<>(); 
    	  q.add(root);
    	  int leftLeafSum = 0; 
    	  
    	  while( !q.isEmpty() ) {
    	     CustomBTNode customNode = q.remove();
    	     // if its a left leaf node add its value to sum ; 
    	     if ( customNode.btNode.left == null && customNode.btNode.left == null )
    	    	 if (customNode.label == 'L' )
    	    		 leftLeafSum = customNode.btNode.data;
    	     
    	     if( customNode.btNode.left != null ) {
    	       q.add(customNode); 
    		 } 
    	     
    	     if ( customNode.btNode.right != null ) {
    		   q.add(customNode); 
    	     } 	 
    	  } 
    	  return leftLeafSum; 
    } 
    
 
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		//BTNode root = BinaryTreeUtils.createOneSidedBinaryTree(n);
		BTNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree(root);
		System.out.println("leftLeafSum1 = " + leftLeafSum1(root));
		System.out.println("leftLeafSum2 = " + leftLeafSum2(root));
	}

}
