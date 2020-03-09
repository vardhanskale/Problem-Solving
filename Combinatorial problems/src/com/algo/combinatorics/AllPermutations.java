package com.algo.combinatorics;

public class AllPermutations {

	
	public static void allPermutations(String str) {
		int len = str.length();
		auxAllPermutations1(0, str, ""); 
	} 


	public  static void auxAllPermutations1(int d, String in, String res) {
		if(d == in.length()) {
			if ( isValid1(res)){
				System.out.println(res);
			}
		 return;
		}
		for(int i = 0; i < in.length() ; ++i){
			auxAllPermutations1(d+1, in, res + in.charAt(i));
		}   
	}
	
	/* check if a duplicate characters are available or not */ 
	private static boolean isValid1(String res) {
		for ( int i = 0 ;   i < res.length() ; ++i ) 
			for ( int j = i+1 ; j < res.length() ; j++)
				if (res.charAt(i) == res.charAt(j))
					 return false; 
		return true; 
	}
	
	/*
	 * Solution 2 : Conditional Branching: Branch (make recursive call) only if needed. 
	 * Before making a recursive call, check if the subtree has the same edge which the parent tree has. 
	 * When you reach a leaf node, it automatically becomes a valid permutation.  
	 * Now the total number of leafs at leaf level = (n!). So TC = n! SC = n.   
	 * We copy the above code and make changes in red. We pass the current character in the isValid() method. 
	 * Against whom should I cross check the current character? We will compare it against the variable “res” 
	 * which holds whatever the parent has shared with the child till this point. 
	 * If “res” holds this character, then its invalid. This is called conditional branching. 
	*/
	public static void allPermutations2(String str) {
	    int len = str.length();
	    auxAllPermutations1(0, str, ""); 
	}

	public static void auxAllPermutations2(int d, String in, String res) {
	    if (d == in.length()) {
		   System.out.println(res);
	       return;
	    }
	    for(int i = 0; i < in.length(); ++i){
	    	if (isValid2(in.charAt(i), res)) {  
	    		auxAllPermutations2(d+1, in, res + in.charAt(i));
	    	}
	    }   
	}
	private static boolean isValid2(char c, String res) {
	   for (int i = 0; i < res.length(); ++i)
	     if (res.charAt(i) == c) // “contains” check by char by char checking. 
		 return false;
	   return true;
	}
    
	public static void allPermutations3(String in) {
		auxAllPermutations3(in, ""); 
	} 
	private static void auxAllPermutations3(String in, String result) {
			
			if (0 == in.length()) {
				System.out.println(result); 
			    return; 
			}    
		      // Input Logic : Take all characters before i and after i .  
			for (int i = 0; i < in.length() ; ++i )  
			    auxAllPermutations3(in.substring(0, i)+in.substring(i + 1), result + in.charAt(i)) ; 
	} 

	public static void main(String[] args) {
		allPermutations(args[0]); 
		System.out.println("----------");
		allPermutations2(args[0]);
		System.out.println("----------");
		allPermutations3(args[0]);
		
	}

}
