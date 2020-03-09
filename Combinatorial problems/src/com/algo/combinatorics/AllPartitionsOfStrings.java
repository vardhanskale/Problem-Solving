package com.algo.combinatorics;

/*
 * Problem: Find an efficient algorithm that enumerates all partitions of a given string. 
 * Refer to the associated tree diagram in the folder to understand the underlying thought.
 */
public class AllPartitionsOfStrings {
	
	public static void allPartitionsOfStrings(String recievedString) {
		String result = ""; 
		auxAllPartitionsOfString(recievedString,result);
	}
	
	private static void auxAllPartitionsOfString(String recievedString,String result) {
		if (recievedString.length() == 0) {
		   // print the result and return. 
		    System.out.println(result); 
		    return; 
		} 
		// Make recursive calls equal to the size of the string to reach the leaf node 
		for (int i=0; i< recievedString.length(); ++i) {     
			auxAllPartitionsOfString(recievedString.substring(i+1), result + "+" + recievedString.substring(0, i+1));		
		}
	}	

   public static void main(String[] args) {
		allPartitionsOfStrings(args[0]);
   }

}
