package com.algo.bst;

public class BSTNode {
	
	BSTNode left;
	BSTNode right;
	int data;
	public int lst_size;

	public BSTNode() {

	}

	public BSTNode(int data) {
		this.data = data;
	}
	
	// For creating a random BST
	public BSTNode(Integer data, int lst_size) {
		this.data = data;
		this.lst_size = lst_size;
	}



}
