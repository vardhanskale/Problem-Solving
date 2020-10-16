package com.algo.priorityQueue.impl;

public interface IPQueue {

	void add(Integer x);

	Integer findMin();

	Integer removeMin();

	int size();

	void display();
}
