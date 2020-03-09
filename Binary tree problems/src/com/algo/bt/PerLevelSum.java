package com.algo.bt;

import java.util.LinkedList;
import java.util.Queue;

public class PerLevelSum {

	
	/* AdHoc : Use BFS to traverse tree. Add a null marker to seperate the levels */
	public static int perLevelSum1(BTNode root) {
		int levelSum = 0;
		Queue<BTNode> q = new LinkedList<>(); 
		q.add(root);
		q.add(null); // Marker Node. 
		while ( !q.isEmpty() ) {
			BTNode node = q.remove();
			if ( node == null ) {
				levelSum = 0; 
				if ( !q.isEmpty())
			     	q.add(null);
			}else {
				levelSum = levelSum + node.data; 
				System.out.println("Level Sum : " + levelSum );
				if ( node.left != null ) 
					q.add(node.left);
				if ( node.right != null)
					q.add(node.right);
			}
		}
		return levelSum; 
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		BTNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree(root);
		System.out.println(perLevelSum1(root));
	}

}
