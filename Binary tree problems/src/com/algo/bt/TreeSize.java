package com.algo.bt;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeSize {

	/*
	 * Approach 1 : Recursion. Idea - Tree Size = Size of left subtree + Size of
	 * right subtree + 1 1. Terminating condition : When root is null return 0; 
	 * 2. Reduction : lSize = treeSize1(root.left); rSize = treeSize1(root.right);
	 * 3. Solution Building : return lSize + rSize + 1
	 * 
	 */

	public static int treeSize1(BTNode root) {
		int lSize, rSize;
		if (root == null)
			return 0;
		lSize = treeSize1(root.left);
		rSize = treeSize1(root.right);
		return (lSize + rSize + 1);
	}

	/*
	 * Idea 2 : Level By Level 
	 * 1. Add the root node in queue. 
	 * 2. While stack is not empty, do the following : 
	 * 2.1 Pop the node from the stack 
	 * 2.2 if the left child of node is not null, add the left child.
	 * 2.3 if the right child of the node is not empty, add the right child.
	 * 2.4 Increment node counter.
	 */

	public static int treeSize2(BTNode root) {
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);
		int nodeCount = 0;

		while (!queue.isEmpty()) {
			BTNode node = queue.remove();
			nodeCount++;
			if (node.left != null)
				queue.add(node);
			if (node.right != null)
				queue.add(node);
		}
		return nodeCount;
	}

	/*
	 * Approach 3 : Leverage Inorder predecessor to store the address of parent.
	 * 
	 */
	public static int treeSize3(BTNode root) {

		int nodeCount = 0;
		// If the root.left link is null, no point in searching for inorder predecessor
		// inorder predecessor is the first left and then the rightmost node.
		while (root != null) {
			if (root.left == null) {
				root = root.right;
				++nodeCount;
			} else {
				// Get inorder predecessor and set its right node to root.
				BTNode inorderPredecessor = getInorderPredecessor(root);
				// if the right side address of inorder predecessor is null set it to root.
				if (inorderPredecessor.right == null) {
					inorderPredecessor.right = root;
					// Now start coming down.
					root = root.left;
					++nodeCount;
				} else {
					// if it is not null, it means we have visited this node earlier.
					// so there is no need to increment the count.
					// we should reset the right side pointer to null and
					// modify root so that we move to the right of the root.
					inorderPredecessor.right = null;
					root = root.right;
				} // end of else
			}
		}

		return nodeCount;
	}
	
	
	public static BTNode getInorderPredecessor(BTNode root) {
		BTNode current = root;
		// Go first left and then keep going right. 
		
		if ( current != null && current.left != null )
			current = root.left; 
		while ( current.right != null && current.right != root )
			current = current.right; 
		return current;
	}

	/* Inorder traversal using stack */ 
	public static int treeSize4(BTNode root) {
		if ( root == null )
			return 0; 
		Stack<BTNode> stack = new Stack<BTNode>();
		BTNode node;
		stack.push(root);
		int nodeCount = 0;

		while (!stack.isEmpty()) {
			node = stack.pop();
			nodeCount++;
			// Add right first, then add left.
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		return nodeCount;
	}

	/* Postorder traversal using stack */ 
	public static int treeSize5(BTNode root) {
		if ( root == null )
			return 0; 
		Stack<BTNode> stack = new Stack<BTNode>();
		BTNode node;
		stack.push(root);
		int nodeCount = 0;

		while (!stack.isEmpty()) {
			node = stack.pop();
			nodeCount++;
			if (node.left != null)
				stack.push(node.left);
			if (node.right != null)
				stack.push(node.right);
			
		}
		return nodeCount;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		//BTNode root = BinaryTreeUtils.createOneSidedBinaryTree(n);
		BTNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree(root);
		//System.out.println(treeSize1(root));
		//System.out.println(treeSize3(root));
		//System.out.println(treeSize4(root));
		System.out.println(treeSize5(root));
	}
}
