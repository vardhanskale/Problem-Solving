package com.algo.sortedset.impl;

import java.util.List;

public interface ISortedSet {

	// Later you can change it to generic.
	
	boolean add(Integer x);

	boolean contains(Integer x);

	boolean remove(Integer x);

	int size();

	void display();

	int min();

	int ceil(Integer x);

	int select(int k);
	
	List<Integer> rangeSearch(int k1 , int k2);
}
