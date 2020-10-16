package com.algo.priorityQueue.impl;

import java.util.ArrayList;


/*
 * Discussion of heap : How is it different from balanced BST 
 * -----------------------------------------------------------
 * BST = Binary tree + At every node x there is a condition 
 *           x 
 *		   /   \
 *		LST    RST 
 *		 < x   > x 
 *
 * In priority Queue we add an element and take out a high priority element. 
 * Heap = complete binary tree + At every node x, the element x is smaller than the children. 
 *           x        x < y
 *		    / \        and 
 *         y   z      x < z
 * In heap only childern matter. We are not interested in LST or RST. 
 * 
 */
public class HeapPQueue implements IPQueue {

	private ArrayList<Integer> heap; 
	
	public HeapPQueue() {
		heap = new ArrayList<Integer>(); 
	}
	
	
	// TC : Theta(log n) . As you keep replacing items till the top of the tree.
	public void add(Integer x) {
		// In a tree we want to add an element in level order. Last level next element.
		// But in sequential storage we are storing it in the last index. 
		// By adding at the last location, structure is perfect, but the order property gets violated. 
		// So you have got to go to the parent and adjust the order. 
		heap.add(x); 
		int current = heap.size() - 1; // This is the index of the last element. 
		while( current > 0 ) { // once current reaches to zeroth  index stop moving current to the top
		if ( heap.get(current) < heap.get(parent(current))) { // Swap last element with its parent if its smaller than parent.
			swap(current, parent(current)); 
		    current = parent(current); 
		} else {
			break; 
		}
	  }
	}

	private void swap(int current, int parent) {
		 int tmp = heap.get(current); 
		 heap.set(current, heap.get(parent));
		 heap.set(parent, tmp ); 
	}

	private int parent(int i) {
		// TODO Auto-generated method stub
		return  (i - 1)/2;   // This is how you get parent of a node in array.
	}
	
	private int left(int i) {
		return 2 * i + 1 ;   // This is how you get left child of a node in array
	}
	
	private int right (int i) {
		return 2 * i + 2; 
	}

	// TC : O(1)  
	public Integer findMin() {
		// If priority queue is empty there is nothing else to return.
		if ( heap.isEmpty() )
			return Integer.MIN_VALUE; 
		return heap.get(0);	
	}

	// TC : Theta (Logn)  
	@Override
	public Integer removeMin() {
		// First you have take out the last element & replace it with 0th element.
		if ( heap.isEmpty())
			return null; 
		int res = heap.get(0);
		// Now replace 0th element with last index element.
		heap.set(0, heap.remove(heap.size()-1)); 
		// Now the order property is violated. Which index property do we need to start to adjust the order.
		// From root, you have to go down to adjust the heap. In the add method whereever you added the element 
		// you need to go up to adjust the order. Now we need to move down to adjust the order. 
		int current = 0; 
		
		while (left(current)  < heap.size() ) {
		  int index_small = left(current); // I'm assuming that the left child of current is the smallest.
		  // Compare with right element to find if the right element is smaller or not. But right element may not exist. 
		  // If left child does not exist you should not enter there. 
		  if ( right(current) < heap.size() && heap.get(right(current)) < heap.get(left(current)) )
		  	 index_small = right(current); 
		
		  // if parent element is greaten than child element we need a swap.
		  if( heap.get(current) > heap.get(index_small)) {
		     swap(current, index_small); 
		     current = index_small; 
		  }
		  else // if parent value is smaller than the smallest children then order is perfectly maintained. Just break out.
		     break; 
		} 
		return res; 
	}

	public int size() {
		return heap.size();
	}

	public void display() {
		System.out.println(heap); 

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
