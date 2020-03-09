package com.algo.bt;

import java.util.Random;

public class BinaryTreeUtils {

	/*
	 * Randomly insert elements in a binary tree. At each node we toss a coin to
	 * decide if insertion should happen at left or right. If we need to go left,
	 * check if the current nodes left pointer is empty. If it is empty add a new
	 * node to the left node. If it is not empty move to the left node. i,e current
	 * = current.left. Keep doing this till current node is not null.
	 */

	// Note sure if we need a parent node.

	private static BTNode addBTNode(BTNode root, int data) {
		Random rand = new Random(); // To decide at each node if we need to go to left or right.
		if (root == null)
			return new BTNode(data);
		BTNode current = root;
		BTNode parent = null; // Not sure if this is needed.
		while (current != null) {
			parent = current; // -> Not sure why this is added.
			// Decide if we need to go to left or right.
			if (rand.nextInt(2) == 0) {
				// Go left. If the left side is empty, add a new node to the left,
				// else move down to left.
				if (current.left == null) {
					current.left = new BTNode(data);
					break;
				}
				current = current.left;
			} else {
				// Go right. If the right side of current node is empty, add a new node to the
				// right,
				// else move down to right.
				if (current.right == null) {
					current.right = new BTNode(data);
					break;
				}
				current = current.right;
			}
		}
		return root;
	}

	private static BTNode add1(BTNode root, int data) {
		if (root == null)
			return new BTNode(data);
		BTNode current = root, parent = null;
		Random r = new Random();
		while (current != null) {
			parent = current;
			if (r.nextInt(2) == 0) {
				if (current.left == null) {
					parent.left = new BTNode(data);
					break;
				}
				current = current.left;
			} else {
				if (current.right == null) {
					parent.right = new BTNode(data);
					break;
				}
				current = current.right;
			}
		}
		return root;
	}

	/*
	 * Creates a binary tree by adding n nodes to it. Each node contains test data
	 * between 1 and n.
	 * 
	 */
	// it is generating biased binary trees not the random binary tree
	public static BTNode createBinaryTree(int n) {
		Random r = new Random();
		BTNode root = null;
		for (int i = 0; i < n; ++i) {
			int data = r.nextInt(n) + 1;
			root = add1(root, data);
		}
		return root;
	}

	public static BTNode createUniqueBinaryTree(int n) {
		BTNode root = null;
		for (int i = 0; i < n; ++i)
			root = addBTNode(root, i + 1);
		return root;
	}

	public static BTNode createOneSidedBinaryTree(int n) {
		Random r = new Random();
		BTNode root = null;
		for (int i = 0; i < n; ++i) {
			int data = r.nextInt(n) + 1;
			BTNode tmp = new BTNode(data);
			if (root != null) {
				tmp.left = root;
			}
			root = tmp;
		}
		return root;
	}

	public static void displayTree(BTNode root) {
		auxDisplay(root, 0, "Root");
	}

	private static void auxDisplay(BTNode root, int nspaces, String type) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println(root.data + "(" + type + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}

	// Specify the size of Binary Tree in command line.
	public static void main(String[] args) {
		int nodeCount = Integer.parseInt(args[0]);
		// Create Binary Tree of Size n.
		BTNode root = BinaryTreeUtils.createBinaryTree(nodeCount);
		// Display Binary Tree.
		BinaryTreeUtils.displayTree(root);
	}

}
