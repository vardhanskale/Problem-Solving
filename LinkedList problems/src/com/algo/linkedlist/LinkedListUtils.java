package com.algo.linkedlist;


public class LinkedListUtils {

		/*
		 * 1. Creates a singly linked list of size n.
		 * 2. Create a head node; Default value of head's next pointer is null. 
		 * 3. Keep creating new nodes & add them at the start of the list. 
		*/ 
	    public static ListNode createList(int n) {
			ListNode head = new ListNode(); 
			ListNode tmp; 
			for ( int i = 1 ; i <= n ; ++i ) {
				tmp = new ListNode(i);
				tmp.next = head.next; 
				head.next = tmp; 
			}
			return head;
	    }
	    
	    /* Create a link list. Then make last node point to first */ 
	    public static ListNode createListWithLoop(int n){
	    	ListNode head = new ListNode(); 
			ListNode tmp = null; 
			/* Create a list */ 
			for ( int i = 1 ; i <= n ; ++i ) {
				tmp = new ListNode(i);
				tmp.next = head.next; 
				head.next = tmp; 
			}
			/* Go to the end of the list and make a loop */ 
			tmp = head;  
			while ( tmp.next != null ) 
				tmp = tmp.next;
			tmp.next = head.next; 
			return head;
	    }
	    
	    /* Start with tmp = head. Keep traversing till tmp is not null 
	     * 1. Continue till current != null is correct.
	     * 2. Continue till curerent.next != null is incorrect.
	     * With 2 you will not print the value of the last node.  
	     */ 
		public static void displayList(ListNode head) {  
			ListNode current = head.next; 
			Integer s; 
			for ( ; current != null ; current = current.next) {
				//s = current.data; 
				System.out.print(current.data);
				//System.out.print(s.split("@")[1] + "->");
			}
			System.out.println();
	    }
		
		/* Start from head.next, end with head.next */ 
		public static void displayWithLoop(ListNode head) {
			ListNode current = head.next; 
			do {
				System.out.print(current.data + "->");
				current = current.next; 
			}while ( current != head.next);
			System.out.println();
		}
	  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
