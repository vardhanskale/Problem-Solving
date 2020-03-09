package com.algo.bt;

import java.util.Queue;

public class SerDe {
	/*
	 * If the node is null add a null marker to the list and return null. Else add
	 * the node value to the list and Call the left node by passing the list Call
	 * the right node by passing the list Recursion is a division and combining
	 * stratergy, but there is nothing to combine here. We are passing the same list
	 * reference in all our calls. At the end, the list is populated.
	 */
	private static void auxSer1(TreeNode root, Queue<Integer> list) {
		if (root == null) {
			// We are using -1 as a null marker.
			list.add(-1);
			return;
		}
		list.add(root.data);
		auxSer1(root.left, list);
		auxSer1(root.right, list);
	}

	/*
	 * Earlier : When we found a null sub child, we added a null marker in the list.
	 * Now : When we find a null marker in a queue, we pop it out and add a null
	 * link.
	 */
	public static TreeNode deser1(Queue<Integer> in) {
		int tmp = in.remove();
		if (tmp == -1)
			return null;
		TreeNode root = new TreeNode(tmp);
		root.left = deser1(in);
		root.right = deser1(in);
		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
