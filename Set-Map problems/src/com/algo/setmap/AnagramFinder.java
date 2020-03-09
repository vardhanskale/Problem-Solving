package com.algo.setmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

    /*
     * Write an efficient algorithm that determines whether a given Strings are anagram of each other or not. 
     * i,e Any permutations of characters of S1 equals to S2.
     */
public class AnagramFinder {
	
	/* 
	 * Attempt1 : Sort both the string and compare each element in both.
	 * Convert the string to char[]. Sort the charArray. 
	 * What will be printed and why ? 
	 * char[] charArray1 = s1.toCharArray();  
	 * System.out.println("Sorted String1 is :" + charArray1.toString()); 
	 * System.out.println("Sorted String1 is :" + Arrays.toString(charArray2)); 
	 * https://www.joelonsoftware.com/2003/10/08/the-absolute-minimum-every-software-developer-absolutely-positively-must-know-about-unicode-and-character-sets-no-excuses/
	 */
    public static boolean isAnagram1(String s1, String s2) {
    	boolean isAnagram = false; 
    	if ( s1.length() != s2.length() )
    		return false;
    	char[] charArray1 = s1.toCharArray();
    	char[] charArray2 = s2.toCharArray();
    	
    	Arrays.parallelSort(charArray1);
    	Arrays.parallelSort(charArray2);
    	System.out.println("Sorted String1 is :" + Arrays.toString(charArray1));
    	System.out.println("Sorted String2 is :" + Arrays.toString(charArray2));
    
    	for ( int i = 0 ; i < charArray1.length ; ++i )
    		if ( charArray1[i] != charArray2[i] )
    			return false;  
    	return true;
    }
    
    /* 
	 * Attempt2 : Create another array of 26 chars. 
	 * Scan first string S1. For each char found, increment array index value. 
	 * Scan second string S2. For each char found, decrement array index value. 
	 * While scanning string S2, if the array index value is 0, this means that
	 * the character in S1 is not found in S2.
	 */
    public static boolean isAnagram2(String s1, String s2) {
    	boolean isAnagram = false; 
    	byte[] count  = new byte[26];
       
    	if ( s1.length() != s2.length() )
    		return false;
       	
    	for( int i = 0 ; i < s1.length() ; i++ ) {
    		++count[s1.charAt(i) - 'a']; 
    	}
    	
    	// Take each char from second array and decrement count at array index location.
    	for ( int i = 0 ; i < s2.length(); i++ ) { 
    		if (count[s2.charAt(i) - 'a'] == 0)
    			return false; 
    		--count[s2.charAt(i) - 'a'];
    	}
    	return true;
    }
    
    /* 
   	 * Attempt3 : Use HashMap to store characters and their count.
   	 * While reading string1 : 
   	 *   -- Get the value of key from hashmap.
   	 *   -- If value is null, then key does not exist. Put Key and Value=1. This would overwrite old value.
   	 *   -- Otherwise increment value and store it back in hashmap. 
   	 * While reading string2 : 
   	 * What to do when the count becomes zero is the key here. 
   	 * Approach 1 : 
   	 *   1.  Get the value of key from hashmap.  
   	 *   2. If value is null, then key does not exist. return false. 
   	 *   3. If value is 0. It means S2 has same chars repeating which S1 does not have. Return false.
   	 *   4. Decrement value and overwrite the keys value in hashmap. 
   	 * Approach 2 : 
   	 *   1. Get the value of key from hashmap.  
   	 *   2. If value is null, then key does not exist. return false. 
   	 *   3. Decrement the value. 
   	 *   4. If value after decrementing is 0, remove it from hashmap. 
   	 *   5. If the value is not zero, override the older value. 
   	 */
    
    public static boolean isAnagram3(String s1, String s2) {
    	if ( s1.length() != s2.length() )
    		return false;
    	
    	//Scan through the first string and increment char count.
    	Map<Character,Integer> hmap = new HashMap<>(s1.length());
    	for( int i = 0 ; i < s1.length() ; ++i ) {
    		Integer val = hmap.get(s1.charAt(i)); 
    		if ( val == null )  // Key is not there or Keys Value is null.
    		   hmap.put(s1.charAt(i), 1); 
    		else 
    			hmap.put(s1.charAt(i),val+1); 
    	}
    	
        // Scan through the second string and decrement char count.
    	for( int i = 0 ; i < s2.length() ; ++i ) {
    		Integer val = hmap.get(s2.charAt(i)); 
    		if ( val == null ) 
    		    return false; //Key is not there.
    		else  { 
    			// Key exists.Decrement its value and put it in hashmap.  
    			if( val == 0)
    				return false;
    			//if ( --val == 0 )
    			//	hmap.remove(s2.charAt(i)); // This is an important step.
    			 hmap.put(s2.charAt(i),--val); 
    		}	
    	}
    	return true;
    }
    
	public static void main(String[] args) {
		String s1 = "abacb"; 
		String s2 = "cbbana";
		System.out.println("isAnagram1 = " + isAnagram1(s1,s2));
		System.out.println("isAnagram2 = " + isAnagram2(s1,s2));
		System.out.println("isAnagram3 = " + isAnagram3(s1,s2));
	}

}
