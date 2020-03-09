package com.algo.linkedlist;

import java.util.HashSet;

public class LoopNode {

	/* 
	 * Approch1 : Use hashSet. This is a problem of finding duplicates in link list.
	 * If node exists in hashset return true, else insert node into hashSet.
	 * If duplicate node is found, return the node, else return null.     
	 */
	 public static ListNode loopNode1(ListNode head) {
		HashSet<ListNode> hset = new HashSet<ListNode>(); 
		ListNode current = head.next; 
		while ( current != null ) {
			current = current.next; 
			if (hset.contains(current))
				return current;
			hset.add(current);
		}
		return null; // If there is no loop at all return null; 
	 }
	
	 /*
	  * Approach 2 : Negation the address trick. 
	  * Pointer arithmatic is not supported in java. So this wont work.

	   public static ListNode loopNode2(ListNode head) {
		ListNode tmp;
		ListNode current = head.next;
		while (current != null) {
		   if (current.next < 0)
	            return current;
		   tmp = current.next;
		   current. next *= -1;
		   current = tmp;
		}
	     return null;
	   }
	 */
	
	
	/* 
	 * Approach3 : Use slow fast pointers. We have a loop if they meet. 
	 * Otherwise the fast pointer will become null before the slow does. 
	 * TC : When Slow covers one circle, Fast covers two circles. 
	 * Thats when they meet. If there are N nodes in the list then in all 
	 * 2N + N traversals will happen = 3N. TC = O(N)    
	 */
	public static ListNode loopNode3(ListNode head) {
		ListNode slow, fast; 
		slow = fast = head.next; 
		// Keep running till there is null or you meet each other.
		do {
			if ( fast == null || fast.next == null )
				return null;    // If no loop exists, return null. 
			slow = slow.next; 
			fast = fast.next.next; 
		}while ( slow != fast );
		// Now that we have a meeting point, lets move the another pointer from head 
		// and the common node pointer till they meet at the loopNode.
		ListNode slow2 = head; 
		while(slow != slow2) {
			slow = slow.next;
			slow2 = slow2.next; 
		}
		// Distance from here to loop node is equal to distance from here to start node.
		return slow; 
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
		// System.out.println(detectCycle1(head));
		//System.out.println(detectCycle3(head));
		System.out.println(detectCycle4(head));
	}

}
