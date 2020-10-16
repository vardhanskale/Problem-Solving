package com.algo.sortedmap.impl;

public class BSTNode {
	
	BSTNode left;
	BSTNode right;
	int key;
	String val; 
	int height; 

	public BSTNode() {

	}

	public BSTNode(int key, String val) {
		this.key = key;
        this.val = val; 
        this.height = 1; 
	}
	
}
