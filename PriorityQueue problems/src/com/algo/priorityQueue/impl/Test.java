package com.algo.priorityQueue.impl;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		IPQueue pq = new HeapPQueue(); 
		Random r = new Random(); 
		for (int i = 0 ; i < n ; ++i) {
			pq.add(r.nextInt(n) + 1 );
			pq.display(); 
		}
		System.out.println(pq.removeMin()); 
        pq.display();
	}

}
