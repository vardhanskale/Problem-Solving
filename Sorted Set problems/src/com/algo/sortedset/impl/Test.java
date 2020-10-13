package com.algo.sortedset.impl;

import java.util.Random;

public class Test {
	
  public static void main(String[] args) {
	  int n = Integer.parseInt(args[0]); 
	  ISortedSet tset = new TreeSet(); 
	  
	  Random r = new Random(); 
	  for( int i = 0;  i < n ; ++i) {
		  // Lets display the element being addded
		  int tmp =  r.nextInt(n) + 1; 
		  System.out.println(tmp);
		  tset.add(tmp); 
          tset.display(); 
	  }
	 System.out.println("min = " + tset.min()); 
	 int x = r.nextInt(n) + 1; 
	 System.out.println("Random element = " + x); 
	 System.out.println("ceil = " + tset.ceil(x)); 
  }
}
