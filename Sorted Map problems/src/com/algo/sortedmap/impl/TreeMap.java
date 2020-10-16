package com.algo.sortedmap.impl;

import java.util.List;

public class TreeMap implements ISortedMap {
	private BSTNode root;
	private int size;

	private int height(BSTNode current) {
		return current == null ? 0 : current.height;
	}

	private BSTNode singleRotateRight(BSTNode p) {
		BSTNode q = p.left;
		p.left = q.right;
		q.right = p;
		p.height = Math.max(height(p.left), height(p.right)) + 1;
		q.height = Math.max(height(q.left), height(q.right)) + 1;
		return q;

	}

	private BSTNode singleRotateLeft(BSTNode p) {
		BSTNode q = p.right;
		p.right = q.left;
		q.left = p;
		p.height = Math.max(height(p.left), height(p.right)) + 1;
		q.height = Math.max(height(q.left), height(q.right)) + 1;
		return q;
	}

	private BSTNode doubleRotateLeftRight(BSTNode p) {
		p.left = singleRotateLeft(p.left);
		return singleRotateRight(p);
	}

	private BSTNode doubleRotateRightLeft(BSTNode p) {
		p.right = singleRotateRight(p.right);
		return singleRotateLeft(p);
	}

	private BSTNode auxAdd(BSTNode current, int key, String val) {
		if (current == null) {
			return new BSTNode(key, val);
		}
		if (key < current.key) {
			current.left = auxAdd(current.left, key, val);
			if (Math.abs(height(current.left) - height(current.right)) > 1) {
				if (key < current.left.key)
					return singleRotateRight(current);
				else
					return doubleRotateLeftRight(current);
			}
		} else {
			current.right = auxAdd(current.right, key, val);
			if (Math.abs(height(current.left) - height(current.right)) > 1) {
				if (key > current.right.key)
					return singleRotateLeft(current);
				else
					return doubleRotateRightLeft(current);
			}
		}
		current.height = Math.max(height(current.left), height(current.right)) + 1;
		return current;
	}

	@Override
	public boolean put(int key, String val) {
		if (containsKey(key))
			return false;
		root = auxAdd(root, key, val);
		++size;
		return true;
	}

	@Override
	public boolean containsKey(int key) {
		BSTNode current = root;
		while (current != null) {
			if (key == current.key)
				return true;
			if (key < current.key)
				current = current.left;
			else
				current = current.right;
		}
		return false;
	}

	@Override
	public boolean remove(int key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	private static void auxDisplay(BSTNode root, int nspaces, String annotation) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(" ");
		System.out.println(root.key + "," + root.val + "(" + annotation + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}

	@Override
	public void display() {
		auxDisplay(root, 0, "root");
	}

	@Override
	public int minKey() {
		if (root == null)
			return Integer.MIN_VALUE;
		BSTNode current = root;
		while (current.left != null)
			current = current.left;
		return current.key;
	}

	@Override
	public int ceilKey(int key) {
		int res = Integer.MIN_VALUE;
		BSTNode current = root;
		while (current != null) {
			if (key == current.key)
				return key;
			if (key < current.key) {
				res = current.key;
				current = current.left;
			} else
				current = current.right;
		}
		return res;
	}

	@Override
	public int selectKey(int k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> rangeSearch(int k1, int k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(int key) {
		BSTNode current = root;
		while (current != null) {
			if (key == current.key)
				return current.val;
			if (key < current.key)
				current = current.left;
			else
				current = current.right;
		}
		return null;
	}

}
