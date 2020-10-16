package com.algo.sortedmap.impl;

import java.util.List;


public interface ISortedMap {

	boolean put(int key, String val);
	
	String get(int key);

	boolean containsKey(int key);

	boolean remove(int key);

	int size();

	void display();

	int minKey();

	int ceilKey(int key);

	int selectKey(int k);
	
	List<Integer> rangeSearch(int k1, int k2);

}
