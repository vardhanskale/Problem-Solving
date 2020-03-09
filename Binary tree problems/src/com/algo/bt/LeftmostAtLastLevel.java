package com.algo.bt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// 513. Find Bottom Left Tree Value

public class LeftmostAtLastLevel {

	/*
	 * Attempt 1 : Use BFS. Insert right node first in queue and then left. The last
	 * node to remain in queue is the bottom left node.
	 */
	public static int findBottomLeftValue1(BTNode root) {
		int value = 0;
		BTNode node = null;
		Queue<BTNode> q = new LinkedList<BTNode>();
		q.add(root);
		while (!q.isEmpty()) {
			node = q.poll(); // Remove the node from the queue.
			if (node.right != null)
				q.add(node.right);
			if (node.left != null)
				q.add(node.left);
		}
		return node.data;
	}

	/*
	 * Attempt2 : BFS. Add NULL at the end of each level. The value after null is
	 * the leftmost leaf value in BFS. [10] NULL [20,30] NULL [40,50,60,70] NULL
	 * Store the value that comes after null in a variable. The second last value in
	 * the variable will be 40 and last one Null. We need to remember the state
	 * before the value became Null in the variable.
	 */

	public static int findBottomLeftValue2(BTNode root) {
		Queue<BTNode> q = new LinkedList<BTNode>();
		q.add(root);
		q.add(null);
		BTNode node = null;
		int value = 0;
		boolean isLastValueNull = false;
		while (!q.isEmpty()) {
			node = q.remove(); // Remove the node from the queue.
			if (node == null) {
				// Remember that the last node became null.
				isLastValueNull = true;
				// When last null is removed, the queue is empty. Don't add null.
				if (!q.isEmpty())
					q.add(null);
			} else {
				// if the last value is null, store it in a variable.
				if (isLastValueNull == true) {
					value = node.data;
					isLastValueNull = false;
				}
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
		}
		return value;
	}

	/*
	 * Attempt3 : Recursive Approach. Pass the level information and a hashmap
	 * instance to the function. Each node adds the level number as key and nodes
	 * value in the hashmap. Since the number of values in each level can change at
	 * each level, lets add a linked list as a value which can hold variable number
	 * of values.
	 */
	public static int findBottomLeftValue3(BTNode root) {
		HashMap<Integer, LinkedList<Integer>> hmap = new HashMap<>();
		auxFindBottomLeftValue3(root, 1, hmap);
		System.out.println(hmap);
		return hmap.get(hmap.size()).get(0)  ;
	}

	public static void auxFindBottomLeftValue3(BTNode root, int level, HashMap<Integer, LinkedList<Integer>> hmap) {

		if (root == null)
			return;

		LinkedList<Integer> nodeValues = hmap.get(level);
		// Create a new instance of link list only if it does not exist.
		if (nodeValues == null) {
			nodeValues = new LinkedList<Integer>();
			nodeValues.add(root.data);
			hmap.put(level, nodeValues);
		} else 
			nodeValues.add(root.data);
		    // hmap.put(level, nodeValues); -> Not need to add the link list back to hashMap.
		    // We are adding in a link list whose address is already stored in the hashMap.   
		auxFindBottomLeftValue3(root.left, level + 1, hmap);
		auxFindBottomLeftValue3(root.right, level + 1, hmap);
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		// BTNode root = BinaryTreeUtils.createOneSidedBinaryTree(n);
		BTNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree(root);
		// System.out.println("findBottomLeftValue1 = " + findBottomLeftValue1(root));
		// System.out.println("findBottomLeftValue2 = " + findBottomLeftValue2(root));
		System.out.println("findBottomLeftValue3 = " + findBottomLeftValue3(root));
	}

}
