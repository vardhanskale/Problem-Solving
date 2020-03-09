package com.algo.linkedlist;

import java.util.HashSet;

public class DetectCyle {

	/* 
	 * Approach1 : Use hashSet. This is a problem of finding duplicates in link list.
	 * If node exists in hashset return true, else insert node into hashSet.
	 * TC : O(N) [Contains + Get = 2 x O(1)] x N elements = 2 x O(1) x N = O(N)      
	 */
	 public static boolean detectCycle1(ListNode head) {
		HashSet<ListNode> hset = new HashSet<ListNode>(); 
		ListNode tmp = head.next; 
		while ( tmp != null ) {
			tmp = tmp.next; 
			if (hset.contains(tmp))
				return true;
			hset.add(tmp);
		}
		return false; 
	 }
	
	 /*
	  * Approach 2 : Negation the address trick. 
	  * Pointer arithmatic is not supported in java. So this wont work.

	   public static boolean detectCycle2(ListNode head) {
		ListNode tmp;
		ListNode current = head.next;
		while (current != null) {
		   if (current.next < 0)
	            return true;
		   tmp = current.next;
		   current. next *= -1;
		   current = tmp;
		}
	     return false;
	   }
	 */
	
	
	/* 
	 * Approach3 : Use slow fast pointers. We have a loop if they meet. 
	 * Otherwise the fast pointer will become null before the slow does. 
	 * TC : When Slow covers one circle, Fast covers two circles. 
	 * Thats when they meet. If there are N nodes in the list then in all 
	 * 2N + N traversals will happen = 3N. TC = O(N)    
	 */
	public static boolean detectCycle3(ListNode head) {
		ListNode slow, fast; 
		slow = fast = head.next; 
		// Keep running till there is null or you meet each other.
		do {
			if ( fast == null || fast.next == null )
				return false;	
			slow = slow.next; 
			fast = fast.next.next; 
		}while ( slow != fast );
		return true; 
	}
	
	public static boolean detectCycle4(ListNode head) {
		ListNode slow, fast;
		slow = fast = head;
		do {
			slow = slow.next;
			if (fast == null || fast.next == null || fast.next.next == null)
				return false;
			fast = fast.next.next.next;
		} while (slow != fast);
		return true;
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		ListNode head = LinkedListUtils.createListWithLoop(n);
		LinkedListUtils.displayWithLoop(head);
		System.out.println(detectCycle1(head));
		System.out.println(detectCycle3(head));
		System.out.println(detectCycle4(head));
	}

}
