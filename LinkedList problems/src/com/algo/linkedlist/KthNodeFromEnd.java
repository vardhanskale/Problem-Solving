package com.algo.linkedlist;

public class KthNodeFromEnd {

	 /*  
	   Approach1 : Adhoc: Two scan approach. TC = O(n). SC=O(n).   
	   1) Make a full scan of the link list to get the length of the link list.
	   2) In second scan to get the actual node from start.  
	   3) In this solution we assume that list does not have loop.
	   4) Traverse N-K+1 to reach the Kth node from last. Total time = N+(N-K) +1. 
	   5) In worst case k =1. So TC = O(n). SC=O(n). 
     */ 
	
	public static ListNode KthNodeFromEnd1(ListNode head, int k) {
		int size = 0; 
	    //First, get the length of the list, then do the second scan. 
		for ( ListNode current = head; current != null ; current = current.next) {
			++size; 
		}
	    //Draw on a paper to check that we need to iterate 
		ListNode current = head; 
		for (int i = 1; i <= (size - k + 1 ) ; ++i ) {
			current = current.next;
		}
		return current;
	}
	
	/* 
	 * 1. Start with two pointer variables, forward and backward. 
	 * 2. Move forward pointer k times to create a window gap of k. 
	 * 3. Move both pointers one by one till forward reaches null. 
	 * 4. When 3 happens, the backward pointer points to kth element from last even if list has a loop. 
	 */ 
	public static ListNode KthNodeFromEnd2(ListNode head, int k) {
		ListNode forward, backward ; // Forward as well as backward pointer for the window. 
		forward = backward = head; 
		// create a k gap b/w forward and backward pointer.
		for (int i = 1; i <= k ; ++i ) {
			forward = forward.next;
		}
		// Now we can move both the pointers one by one till the forward reaches null.
		// Forward moves n nodes and backward moves n-k. 
		
		while( forward != null) {
			forward = forward.next;
			backward = backward.next;
     	}
		return backward;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
