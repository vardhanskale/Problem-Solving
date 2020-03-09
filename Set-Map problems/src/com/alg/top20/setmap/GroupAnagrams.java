package com.alg.top20.setmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class GroupAnagrams {

	/*
	 * Program to print Anagram set. 
	 * Input : cat, dog, tac, god, act. 
	 * Output : {cat,tac, act} , {dog, god } 
	 */

	/**
	 * Approach 3 : Use HashMap. 
	 * 1. Get each string from the array. 
	 * 2. Sort it. 
	 * 3. If hashMap contains the sorted string
	 * 4. Get its value ( the list ) and add the original string in it. No need to add list back.
	 * 5. If hashMap does not contain sorted string...
	 *    5.1 Create a new list, add the original string it.
	 *    5.2 Add the sorted string as key and the list in hashMap.  
	 * 6. Print the hashmaps contents. 
	 */
	private static void groupAnagram1(String[] words) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		char[] charArray;
		String sortedString;
		ArrayList<String> values = null;
        //1. Get each string from the array
		for (String s : words) {
		    //2. Sort the string.
			charArray = s.toCharArray();
			Arrays.parallelSort(charArray);
			sortedString = String.valueOf(charArray);
			//3. If sorted string is present in hashMap. Get the list and add original string in it.
			if (map.containsKey(sortedString)) {
				map.get(sortedString).add(s);
			} else {
			// 4. If sorted string is not present, create a list, add string in it, add list and key in map.
				values = new ArrayList();
				values.add(s);
				map.put(sortedString, values);
			}
		}
		// Print the values in arrayList for each value of Key.
		for (String key : map.keySet())
			System.out.println("Key : " + map.get(key));
	}

	// lets create n strings of k size each.
	public static String[] testCase1(int n, int k) {
		String[] stringArray = new String[n];
		Random rand = new Random(0);

		for (int i = 0; i < n; ++i) {
			StringBuffer sb = new StringBuffer();
			// Generate a random character b/w : 'a' - 'z' of length k
			for (int j = 0; j < k; j++) {
				sb.append((char) ('a' + rand.nextInt(26)));
			}
			stringArray[i] = sb.toString();
		}
		return stringArray;
	}

	public static void main(String[] args) {
		// Number of Strings
		// int n = Integer.parseInt(args[0]);
		// Size of each String
		// int k = Integer.parseInt(args[1]);
		// String[] words = testCase1(n, k);
		String words[] = { "cat" , "dog", "tac", "god", "act" } ;
		for (String word : words)
			System.out.println(word + ",");
		System.out.println(" ");
		groupAnagram1(words);
	}
}
