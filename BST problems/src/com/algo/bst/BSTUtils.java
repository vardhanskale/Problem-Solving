package com.algo.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
	public static TreeNode add(TreeNode root, int data) {
		if(root == null) return new TreeNode(data);
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
	
	public static TreeNode createUniqueBST(int n) {
		TreeNode root = null;
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= n; ++i)
			list.add(i);
		Collections.shuffle(list);
		System.out.println("content of shuffled list " + list);
		for(Integer x: list)
			root = add(root, x);	
		return root;
	}
	
	public static void displayTree(TreeNode root) {
		auxDisplay(root, 0, "Root");
	}
	private static void auxDisplay(TreeNode root, int nspaces, String type) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println(root.data + "(" + type + "," + root.lst_size + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}
	
	public static void main(String[] args) {
		//displayTree(root);
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
	}

}