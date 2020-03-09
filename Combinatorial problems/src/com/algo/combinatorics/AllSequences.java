package com.algo.combinatorics;

/*
 * Problem2: Write an efficient algorithm that enumerates all possible n-length sequences that can be 
 * formed using n characters. 
 */
	
/*
 * Solution : Make a tree diagram.This is essentially root to leaf path problem. 
 * As we traverse each root to leaf path, we append each edge in the variable "res".
 * The depth of the recursion equals N, which in this case equals to the length of the string. 
 * When the depth of recursion equals the depth of the tree, recursion terminates.
 */
public class AllSequences {
	
		public static void allSequences(String str) {
			int len = str.length();
			auxAllSeq1(0, str, ""); 
		} 
	

		public  static void auxAllSeq1(int d, String in, String res) {
			if(d == in.length()) {
				System.out.println(res);
				return;
			}
			for(int i = 0; i < in.length() ; ++i){
				auxAllSeq1(d+1, in, res + in.charAt(i));
			}   
		}
		
		
		public static void allStringSequence(String givenString) 
		{
		   String result =""; 
		   auxAllStringSequence(0, givenString, result);
		} 
			
		public static void auxAllStringSequence(int depthOfRecursion, String givenString, String result)
		{
			if ( depthOfRecursion == givenString.length() ){
			    System.out.println(result);
		        return;   		   
			} 
			auxAllStringSequence(depthOfRecursion + 1, givenString , result + givenString.charAt(0));
			auxAllStringSequence(depthOfRecursion + 1, givenString , result + givenString.charAt(1));
			auxAllStringSequence(depthOfRecursion + 1, givenString , result + givenString.charAt(2));	
		 }		
			    	
		 public static void main(String[] args) {
			 //allSequences(args[0]); 
			 allStringSequence(args[0]);
		 }

}
