package com.algo.sortedmap.impl;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		ISortedMap tmap = new TreeMap();

		Random r = new Random();
		for (int i = 0; i < n; ++i) {
			int tmp = r.nextInt(n) + 1;
			System.out.println(tmp);
			tmap.put(tmp, "val" + i);
			tmap.display();
		}

		System.out.println(tmap.minKey());
		int x = r.nextInt(n) + 1;
		System.out.println(x);
		System.out.println(tmap.ceilKey(x));

	}

}
