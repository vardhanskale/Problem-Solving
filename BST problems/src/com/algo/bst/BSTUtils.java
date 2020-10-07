package com.algo.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 *  In Binary tree, every node has at most two child nodes. 
 *  Since binary tree follows a random insertion process, removal takes O(n) time. 
 *  BST imposes additional restriction that the right child of the node is greater than the node and left child is smaller than the node. 
 *  While searching any element in BST we prune half of the tree each time which makes it faster.
 *  This allows faster search, insert, delete operations faster. One common application of BST is symbol tables.
 *  Only pain point in BST is that in worst case scenario it can grow as a one sided BST. So to overcome this issue...
 *  BBST imposes another restriction that the difference in height between left and right subtree should not be more than 1. 
 *  The whole idea of balancing is to make the performance of the tree as close as possible to log(N) 
 *  The terminology to balance a BT is called height balancing. 
 *  Height Balanced HB(0) implies that difference of height between left and right subtree is 0. 
 *  BBST = BST + At every node take |height(LST) - height(RST)| <=1
 *  Its a very restrictive condition to maintain perfect balancing. For a tree of 4 leaf nodes we will need to add 8 child nodes. 
 *  A little more relaxed condition is HB(1) tree. HB(1) implies that height between left and right subtree is 1.
 *  Thinking pattern to solve the BST problems is recursion and Divide and Prune.
 *  Start with the buildBST(int n) method below
 *
 */

class Result1 {
	TreeNode node;
	int ls_size;
	public Result1(TreeNode node, int ls_size) {
		super();
		this.node= node;
		this.ls_size = ls_size;
	}

}
public class BSTUtils {
	
	public static BSTNode buildBST( int n ) {
	    return auxBuildBST(1,n);  
	} 		

	private static  BSTNode auxBuildBST(int l , int r )  {
	     
		 /*
		  * Base case, terminating condition is when l crosses r.
	      * Thats when no keys are there. So we need to return.
	      */	   
	     if ( l > r ) return null;  
		 
		 // When l = r we can stop because there is a single node.
	     if ( l == r ) return  new BSTNode(1);  
		 
		 // To divide the BST, we need to create a mid 
	     int m = (l + r) / 2 ; 
		 
		 // Whatever mid element we selected, we need a node for it first.
		 BSTNode root = new BSTNode(m); 
		 
		 /* 
		  * Now we need to build the left subtree with keys l, to m-1. 
		  * We also attach the result to the roots left subtree.
		  */ 
		 root.left = auxBuildBST(l, m-1); 
		 
		 // Similarly build a right subtree and attach it to right node. 
		 root.right = auxBuildBST(m+l, r) ;

	  return root ; 	  
	} 		  

	/*
	public static TreeNode createBBST(int n) {
		int[] in = new int[n];
		for(int i = 0; i < n; ++i)
			in[i] = i+1;
		return auxBBST(in, 0, in.length-1);
	}
	private static TreeNode auxBBST(int[] in, int l, int r) {
		if(l > r) return null;
		int m = l + (r-l)/2;
		//TreeNode tmp = new TreeNode(in[m], m-l+1);
		//TreeNode tmp = new TreeNode(in[m], m-l+1);
		tmp.left = auxBBST(in, l, m-1);
		tmp.right = auxBBST(in, m+1, r);
		return tmp;
	}	
	*/
	
	/* 
	 * Insert a node in BST recursively. 
	 * If the value is less than root, add node to left subtree, else add it to right subtree.
	 * If we know the size of the left subtree, finding kth smallest element is easy.
	 * So increment a counter whenever the node is inserted in the left subtree during creation process. 
	 */
	public static BSTNode add(BSTNode root, int data) {
		if(root == null) return new BSTNode(data);
		if(data == root.data) return root;
		else if(data < root.data) {
			++root.lst_size;
			root.left = add(root.left, data);
		}
		else
			root.right = add(root.right, data);
		return root;		
	}
	
	/* 
	 * Insert a node into BST iteratively. 
	 */
	public static TreeNode addIteratively(TreeNode root, int data) {
		if(root == null) return new TreeNode(data);
		if(data == root.data) return root;
		
		TreeNode current = root;   // Current will become null as we go from root to leaf.
		TreeNode previous = root;  // previous will follow current and will reach till the leaf.  
		
		while (current != null ) {
			previous = current ; // Record leaf node before current becomes null. 
			if(data < root.data) {
				++root.lst_size;
				root = root.left;
			}
			else
				root = root.right;
		}
		if ( data < root.data ) {
			previous.left = new TreeNode(data);
		} else {
			previous.right = new TreeNode(data);
		}

		return root;		
	}
	
	public static BSTNode createUniqueBST(int n) {
		BSTNode root = null;
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= n; ++i)
			list.add(i);
		Collections.shuffle(list);
		System.out.println("content of shuffled list " + list);
		for(Integer x: list)
			root = add(root, x);	
		return root;
	}
	
	public static void displayTree(BSTNode root) {
		auxDisplay(root, 0, "Root");
	}
	
	private static void auxDisplay(BSTNode root, int nspaces, String annotation) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println(root.data + "(" + annotation + "," + root.lst_size + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}
	
	public static void main(String[] args) {
		// Build BST 
		int n = Integer.parseInt(args[0]);
		BSTNode bstRoot = buildBST(n); 
		displayTree(bstRoot);
		
		/*
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		for(int i = 1; i <= 10000; ++i) {
			TreeNode root = createUniqueBST(3);
			List<Integer> key = SerDe.serialize(root);
			System.out.println(key);
			if(hmap.get(key.toString()) == null)
				hmap.put(key.toString(), 1);
			else
				hmap.put(key.toString(), hmap.get(key.toString())+1);
		}
		System.out.println(hmap);
		*/
	}

}